package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.AcademicResourcesTypeBean;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceTypeBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.vise.utils.assist.Network;
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

public class AcademicResourcesInsertActivity extends AppCompatActivity {
    private Context context = AcademicResourcesInsertActivity.this;
    @BindView(R.id.spinner1)
    Spinner spinnertext ;
    private ArrayAdapter<String> adapters;
    private List<String> list = new ArrayList<String>();
    private List<AcademicResourcesTypeBean.DataBean> mList;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    private int REQUEST_CODES=102;
    private String pic="";
    private int typeId=0;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_resources_insert);
        ButterKnife.bind(AcademicResourcesInsertActivity.this);
        initData();//btn_canl
        spinnertext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeId = mList.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void initData(){
        ViseUtil.Get(context, NetUrl.AppConsultationInfofindDepartment, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AcademicResourcesTypeBean bean = gson.fromJson(s,AcademicResourcesTypeBean.class);
                mList = bean.getData();
                for (AcademicResourcesTypeBean.DataBean bean2 : bean.getData()){
                    list.add(bean2.getDepName());
                }
                adapters = new ArrayAdapter<String>(AcademicResourcesInsertActivity.this, android.R.layout.simple_spinner_item, list);
                //第三步：设置下拉列表下拉时的菜单样式
                adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //第四步：将适配器添加到下拉列表上
                spinnertext.setAdapter(adapters);
            }
        });
    }
    private void SaveInfo(){
        String s = et_title.getText().toString();
        String b = et_content.getText().toString();
        if(s.isEmpty() || b.isEmpty() || typeId==0 ||pic.isEmpty()){
            ToastUtil.showShort(context,"请把信息填写完整!");
        }else{
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
           /* Map<String,String> map = new LinkedHashMap<>();
            map.put("title",s);
            map.put("content",b);
            map.put("publishDepartment",typeId+"");*/
            File file = new File(pic);
            ViseHttp.UPLOAD(NetUrl.AppEducationInfotoUpdate)
                    .addParam("title",s)
                    .addParam("content",b)
                    .addParam("publishDepartment",typeId+"")
                    .addFile("file0", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            //JSONObject jsonObject = null;
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optString("status").equals("200")){
                                    ToastUtil.showShort(context, "发布成功");
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

                        }
                    });

            /*ViseUtil.Post(context, NetUrl.AppEducationInfotoUpdate, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if(jsonObject.optString("status").equals("200")){
                            ToastUtil.showShort(context,"发布成功!");
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });*/
        }
    }
    @OnClick({R.id.iv_black,R.id.btn_canl,R.id.btn_save,R.id.add_img})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv_black:
                finish();
                break;
            case R.id.btn_canl:
                finish();
                break;
            case R.id.btn_save:
                SaveInfo();
                break;
            case R.id.add_img:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(AcademicResourcesInsertActivity.this, REQUEST_CODES); // 打开相册
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODES && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (images.size() > 0) {
                Glide.with(AcademicResourcesInsertActivity.this).load(images.get(0)).into(iv_img);
                //iv_del1.setVisibility(View.VISIBLE);
                pic = images.get(0);
            }
        }
    }
}
