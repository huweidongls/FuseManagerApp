package com.guoyu.fusemanagerapp.page;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.IndexAdapter;
import com.guoyu.fusemanagerapp.adapter.IndexGongnengAdapter;
import com.guoyu.fusemanagerapp.app.MyApplication;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.BannerBean;
import com.guoyu.fusemanagerapp.bean.HomeNewsBean;
import com.guoyu.fusemanagerapp.bean.MenuBean;
import com.guoyu.fusemanagerapp.bean.VersionBean;
import com.guoyu.fusemanagerapp.dialog.DialogVersion;
import com.guoyu.fusemanagerapp.dialog.ProgressDialog;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.VersionUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;
import com.youth.banner.Banner;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_gongneng)
    RecyclerView rvGongneng;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private int type=0;
    private IndexGongnengAdapter gongnengAdapter;
    private List<MenuBean.DataBean> mList;
    private IndexAdapter indexAdapter;
    private List<HomeNewsBean.DataBean> mIndexList;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.instance().request(this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_allow) + "\n" + permissionName);
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_refuse) + "\n" + permissionName);
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_noAsk) + "\n" + permissionName);
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE);
        MyApplication.getInstance().addActivity(MainActivity.this);
        ButterKnife.bind(MainActivity.this);
        initBanner();
        initData();
        initNews();
        Log.e("02020202020",SpUtils.getToken(context));
        initVersion();

    }

    /**
     * 轮播图
     */
    private void initBanner() {

        ViseUtil.Post(context, NetUrl.AppBannerInfoqueryList, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                BannerBean bean = gson.fromJson(s, BannerBean.class);
                List<String> list = new ArrayList<>();
                for (BannerBean.DataBean bean1 : bean.getData()){
                    list.add(NetUrl.BASE_URL+bean1.getBannerPic());
                }
                init(banner, list);
            }
        });

    }

    private void initVersion() {

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

    private void initData() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppUseradminGetMuen, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MenuBean bean = gson.fromJson(s, MenuBean.class);
                mList = bean.getData();
                gongnengAdapter = new IndexGongnengAdapter(mList);
                GridLayoutManager manager = new GridLayoutManager(context, 4){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvGongneng.setLayoutManager(manager);
                rvGongneng.setAdapter(gongnengAdapter);
            }
        });

       /* mIndexList = new ArrayList<>();
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        indexAdapter = new IndexAdapter(mIndexList);
        LinearLayoutManager manager1 = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(indexAdapter);*/

    }
    private void initNews(){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("type",type+"");
        ViseUtil.Get(context, NetUrl.AppHomePageLatestNews, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                HomeNewsBean bean = gson.fromJson(s,HomeNewsBean.class);
                mIndexList = bean.getData();
                indexAdapter = new IndexAdapter(mIndexList);
                LinearLayoutManager manager1 = new LinearLayoutManager(context){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager1);
                recyclerView.setAdapter(indexAdapter);
            }
        });
    }
    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl_person,R.id.rl4})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl1:
                tv1.setTextColor(getResources().getColor(R.color.theme));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#000000"));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                type=0;
                initNews();
                break;
            case R.id.rl2:
                tv1.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(getResources().getColor(R.color.theme));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#000000"));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.GONE);
                type=1;
                initNews();
                break;
            case R.id.rl3:
                tv1.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv4.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(getResources().getColor(R.color.theme));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                view4.setVisibility(View.GONE);
                type=2;
                initNews();
                break;
            case R.id.rl4:
                tv1.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#000000"));
                tv4.setTextColor(getResources().getColor(R.color.theme));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                view4.setVisibility(View.VISIBLE);
                type=3;
                initNews();
                break;
            case R.id.rl_person:
                intent.setClass(context, PersonActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backtrack();
    }

    /**
     * 退出销毁所有activity
     */
    private void backtrack() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.showShort(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
            exitTime = 0;
        }
    }

}
