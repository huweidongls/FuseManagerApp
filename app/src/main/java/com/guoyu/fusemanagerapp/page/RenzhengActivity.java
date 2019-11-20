package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.RenzhengAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.APPFuncInfoApplyFoorListBean;
import com.guoyu.fusemanagerapp.bean.PersonBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.StringUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

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

public class RenzhengActivity extends BaseActivity {

    private Context context = RenzhengActivity.this;

    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private int REQUEST_CODE = 101;
    private String pic1 = "";

    private String id = "";
    private String funName = "";

    private List<APPFuncInfoApplyFoorListBean.DataBean> mList;

    private RenzhengAdapter adapter;
    private List<String> list;

    private Dialog dialog;

    private boolean isFirst = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzheng);

        id = getIntent().getStringExtra("id");
        list = new ArrayList<>();
        ButterKnife.bind(RenzhengActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Post(context, NetUrl.AppUseradmingetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                PersonBean bean = gson.fromJson(s, PersonBean.class);
                String apppic = bean.getData().getAppuserPics();
                if(!StringUtils.isEmpty(apppic)){
                    Glide.with(context).load(NetUrl.BASE_URL+apppic.split(",")[0]).into(ivImg);
                    ivDel.setVisibility(View.VISIBLE);
                }
                funName = bean.getData().getFuncame();
                if(StringUtils.isEmpty(apppic)&&StringUtils.isEmpty(funName)){
                    isFirst = true;
                }
                if(!StringUtils.isEmpty(funName)){
                    String[] ss = funName.split(",");
//                    for (int i = 0; i<ss.length; i++){
//                        list.add(ss[i]);
//                    }
                    for (String sss : ss){
                        list.add(sss);
                    }
                    adapter = new RenzhengAdapter(list);
                    LinearLayoutManager manager = new LinearLayoutManager(context){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }else {
                    adapter = new RenzhengAdapter(list);
                    LinearLayoutManager manager = new LinearLayoutManager(context){
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_add, R.id.iv_del, R.id.rl_list, R.id.btn_commit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(RenzhengActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.iv_del:
                pic1 = "";
                Glide.with(context).load("#ffffff").into(ivImg);//@mipmap/img0203x
                ivDel.setVisibility(View.GONE);
                break;
            case R.id.rl_list:
                intent.setClass(context, RenzhengListActivity.class);
                intent.putExtra("funName", funName);
                startActivityForResult(intent, 1001);
                break;
            case R.id.btn_commit:
                if((StringUtils.isEmpty(pic1)||mList == null)&&isFirst){
                    ToastUtil.showShort(context, "资质图片或开通服务不能为空");
                }else if(StringUtils.isEmpty(pic1)&&mList == null&&!isFirst){
                    ToastUtil.showShort(context, "提交成功");
                    Intent intent1 = new Intent();
                    intent1.setClass(context, LoginActivity.class);
                    startActivity(intent1);
                    finish();
                }else if(StringUtils.isEmpty(pic1)&&mList!=null){
                    WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    String funs = "";
                    for (APPFuncInfoApplyFoorListBean.DataBean bean : mList){
                        if(bean.getIsSelect() == 1){
                            funs = funs + bean.getId() + ",";
                        }
                    }
                    ViseHttp.POST(NetUrl.AppUsercommercialAudit)
                            .addParam("id", id)
                            .addParam("funcInfos", funs)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
                                            ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                            Intent intent1 = new Intent();
                                            intent1.setClass(context, LoginActivity.class);
                                            startActivity(intent1);
                                            finish();
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
                }else if(!StringUtils.isEmpty(pic1)&&mList==null){
                    WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    File file = new File(pic1);
                    ViseHttp.UPLOAD(NetUrl.AppUsercommercialAudit)
                            .addParam("id", id)
                            .addFile("file0", file)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
                                            ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                            Intent intent1 = new Intent();
                                            intent1.setClass(context, LoginActivity.class);
                                            startActivity(intent1);
                                            finish();
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
                }else if(!StringUtils.isEmpty(pic1)&&mList!=null){
                    WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    File file = new File(pic1);
                    String funs = "";
                    for (APPFuncInfoApplyFoorListBean.DataBean bean : mList){
                        if(bean.getIsSelect() == 1){
                            funs = funs + bean.getId() + ",";
                        }
                    }
                    ViseHttp.UPLOAD(NetUrl.AppUsercommercialAudit)
                            .addParam("id", id)
                            .addParam("funcInfos", funs)
                            .addFile("file0", file)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
                                            ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                            Intent intent1 = new Intent();
                                            intent1.setClass(context, LoginActivity.class);
                                            startActivity(intent1);
                                            finish();
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
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (images.size() > 0) {
                Glide.with(context).load(images.get(0)).into(ivImg);
                pic1 = images.get(0);
                ivDel.setVisibility(View.VISIBLE);
            }
        }
        if(requestCode == 1001&&resultCode == 1002&&data != null){
            mList = (List<APPFuncInfoApplyFoorListBean.DataBean>) data.getSerializableExtra("mList");
            list.clear();
            for (APPFuncInfoApplyFoorListBean.DataBean bean : mList){
                if(bean.getIsSelect() == 1){
                    list.add(bean.getFunName());
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

}