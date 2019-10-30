package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.IndexGongnengAdapter;
import com.guoyu.fusemanagerapp.adapter.MyServiceAdapter;
import com.guoyu.fusemanagerapp.app.MyApplication;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.MenuBean;
import com.guoyu.fusemanagerapp.bean.PersonBean;
import com.guoyu.fusemanagerapp.bean.VersionBean;
import com.guoyu.fusemanagerapp.dialog.DialogCustom;
import com.guoyu.fusemanagerapp.dialog.DialogVersion;
import com.guoyu.fusemanagerapp.dialog.ProgressDialog;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.VersionUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    private Context context = PersonActivity.this;
    private int REQUEST_CODE = 101;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rv)
    RecyclerView rv;

    private String pic="";
    private String b="";
    private Dialog dialog;

    private MyServiceAdapter adapter;
    private List<MenuBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ButterKnife.bind(PersonActivity.this);
        initService();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    /**
     * 初始化权限
     */
    private void initService() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppUseradminGetMuen, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MenuBean bean = gson.fromJson(s, MenuBean.class);
                mList = bean.getData();
                adapter = new MyServiceAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rv.setLayoutManager(manager);
                rv.setAdapter(adapter);
            }
        });

    }

    private void initData() {

        tvVersion.setText("v "+ VersionUtils.packageName(context));

        Logger.e("123123", SpUtils.getToken(context));
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));

        ViseUtil.Post(context, NetUrl.AppUseradmingetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                PersonBean bean = gson.fromJson(s, PersonBean.class);
                tvName.setText(bean.getData().getRealName());
                tvPhone.setText(bean.getData().getUsername());
                b = bean.getData().getUSER_PIC();
                b=b.substring(0, b.lastIndexOf(","));
                Glide.with(context).load(NetUrl.BASE_URL+b).into(ivHead);
            }
        });

    }

    @OnClick({R.id.rl_login,R.id.iv_head, R.id.ll_version
    , R.id.ll_about, R.id.rl_back, R.id.btn_exit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_login:
                intent.setClass(context, UserInfoSetActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_head:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(PersonActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.ll_version:
                final int versionCode = VersionUtils.packageCode(context);
                ViseUtil.Get(context, NetUrl.AppVersionInfonewVersionManage, null, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        final VersionBean bean = gson.fromJson(s, VersionBean.class);
                        if(bean.getData().getVersioncode()>versionCode){
                            DialogVersion dialogVersion = new DialogVersion(context, bean.getData().getVersionname(), bean.getData().getVerDesc()
                                    , new DialogVersion.ClickListener() {
                                @Override
                                public void onSure() {
                                    final ProgressDialog progressDialog = new ProgressDialog(context);
                                    progressDialog.setCancelable(false);
                                    progressDialog.setCanceledOnTouchOutside(false);
                                    progressDialog.show();
                                    String downloadUrl = bean.getData().getDownloadAdd();
                                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                                    ViseHttp.DOWNLOAD(downloadUrl)
                                            .setRootName(path)
                                            .setDirName("fusemanager")
                                            .setFileName("FuseManager.apk")
                                            .request(new ACallback<DownProgress>() {
                                                @Override
                                                public void onSuccess(DownProgress data) {
                                                    progressDialog.setInfo(data.getFormatStatusString(), data.getPercent());
                                                    if (data.isDownComplete()){
                                                        progressDialog.dismiss();
                                                        String appFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fusemanager/"+"FuseManager.apk";
                                                        openAPK(appFile);
                                                    }
                                                }

                                                @Override
                                                public void onFail(int errCode, String errMsg) {

                                                }
                                            });
                                }

                                @Override
                                public void onCancel() {
                                    if(bean.getData().getIsAutoupdate() == 1){
                                        MyApplication.getInstance().exit();
                                    }
                                }
                            });
                            dialogVersion.setCancelable(false);
                            dialogVersion.setCanceledOnTouchOutside(false);
                            dialogVersion.show();
                        }
                    }
                });
                break;
            case R.id.ll_about:
                intent.setClass(context, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_exit:
                DialogCustom dialogCustom = new DialogCustom(context, "是否退出登录", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        SpUtils.clear(context);
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("jnkjToken", "");
                        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                                .globalHeaders(map);
                        finish();
                    }
                });
                dialogCustom.show();
                break;
        }
    }

    /**
     * 安装apk
     * @param fileSavePath
     */
    private void openAPK(String fileSavePath){
        File file=new File(Uri.parse(fileSavePath).getPath());
        String filePath = file.getAbsolutePath();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断版本大于等于7.0
            // 生成文件的uri，，
            // 注意 下面参数com.ausee.fileprovider 为apk的包名加上.fileprovider，
            data = FileProvider.getUriForFile(context, "com.guoyu.fusemanagerapp.fileprovider", new File(filePath));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);// 给目标应用一个临时授权
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void upload_head(){//上传头像
        //提交接口
        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        File file = new File(pic);
        ViseHttp.UPLOAD(NetUrl.apiupdateheadPortrait)
                .addParam("id", SpUtils.getUserId(context))
                .addFile("file0", file)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("status").equals("200")){
                                ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                            }
                            WeiboDialogUtils.closeDialog(dialog);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        WeiboDialogUtils.closeDialog(dialog);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (images.size() > 0) {
               // Glide.with(context).load(images.get(0)).into(iv1);
                pic = images.get(0);
                upload_head();
            }
        }
    }

}
