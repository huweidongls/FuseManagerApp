package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.LoginBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.PwdCheckUtil;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.StringUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationThreeActivity extends BaseActivity {

    private Context context = RegistrationThreeActivity.this;

    @BindView(R.id.et_pwd1)
    EditText etPwd1;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private boolean isShowPwd = false;
    private boolean isShowRegisterPwd = false;

    private String phone = "";
    private String type = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_three);

        phone = getIntent().getStringExtra("phone");
        type = getIntent().getStringExtra("type");
        ButterKnife.bind(RegistrationThreeActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("0")){
            tvTitle.setText("注册");
        }else {
            tvTitle.setText("忘记密码");
        }

    }

    @OnClick({R.id.rl_back, R.id.rl1, R.id.rl2, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl1:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.icon0063x).into(iv1);
                    etPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd1.setSelection(etPwd1.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.login_icon033x).into(iv1);
                    etPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd1.setSelection(etPwd1.getText().length());
                }
                break;
            case R.id.rl2:
                if(isShowRegisterPwd){
                    isShowRegisterPwd = false;
                    Glide.with(context).load(R.mipmap.icon0063x).into(iv2);
                    etPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd2.setSelection(etPwd2.getText().length());
                }else {
                    isShowRegisterPwd = true;
                    Glide.with(context).load(R.mipmap.login_icon033x).into(iv2);
                    etPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd2.setSelection(etPwd2.getText().length());
                }
                break;
            case R.id.btn_sure:
                final String pwd1 = etPwd1.getText().toString();
                String pwd2 = etPwd2.getText().toString();
                if(StringUtils.isEmpty(pwd1)||StringUtils.isEmpty(pwd2)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else if(!pwd1.equals(pwd2)){
                    ToastUtil.showShort(context, "密码不一致");
                }else if(pwd1.length()<8||pwd1.length()>32){
                    ToastUtil.showShort(context, "密码长度需为8-32位");
                }else if(pwd2.length()<8||pwd2.length()>32){
                    ToastUtil.showShort(context, "密码长度需为8-32位");
                }else if(!PwdCheckUtil.isLetterDigit(pwd1)||!PwdCheckUtil.isLetterDigit(pwd2)){
                    ToastUtil.showShort(context, "密码必须包括数字、大小写字母至少2种或者两种以上元素");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    if(type.equals("0")){
                        final Map<String, String> map = new LinkedHashMap<>();
                        map.put("phone", phone);
                        map.put("psd", pwd1);
                        ViseUtil.Get(context, NetUrl.AppUserregiste, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                ViseUtil.Post(context, NetUrl.AppUserappAdmuinLogin, map, dialog, new ViseUtil.ViseListener() {
                                    @Override
                                    public void onReturn(String s) {
                                        Gson gson = new Gson();
                                        LoginBean bean = gson.fromJson(s, LoginBean.class);
                                        SpUtils.setToken(context, bean.getUserNameFromToken());
                                        SpUtils.setUserId(context, bean.getData().getId()+"");
                                        SpUtils.setPhoneNum(context, bean.getData().getUsername());
                                        Map<String, String> map2 = new LinkedHashMap<>();
                                        map2.put("jnkjToken", bean.getUserNameFromToken());
                                        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                                                .globalHeaders(map2);
                                        Intent intent = new Intent();
                                        if(bean.getData().getUserType() == 2&&bean.getData().getAppuserType() == 2&&bean.getData().getStatus()!=2){
                                            intent.setClass(context, RenzhengActivity.class);
                                        }else {
                                            intent.setClass(context, MainActivity.class);
                                        }
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        });
                    }else {
                        //修改密码
//                        Map<String, String> map = new LinkedHashMap<>();
//                        map.put("phone", phone);
//                        map.put("psd", pwd1);
//                        ViseUtil.Post(context, NetUrl.CitizenUserforgetThePassword, map, new ViseUtil.ViseListener() {
//                            @Override
//                            public void onReturn(String s) {
//                                Map<String, String> map1 = new LinkedHashMap<>();
//                                map1.put("phone", phone);
//                                map1.put("psd", pwd1);
//                                ViseUtil.Get(context, NetUrl.loginApp, map1, dialog, new ViseUtil.ViseListener() {
//                                    @Override
//                                    public void onReturn(String s) {
//                                        try {
//                                            JSONObject jsonObject = new JSONObject(s);
//                                            ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
//                                            Gson gson = new Gson();
//                                            LoginBean bean = gson.fromJson(s, LoginBean.class);
//                                            SpUtils.setToken(context, bean.getAppToken());
//                                            SpUtils.setUserId(context, bean.getData().getId()+"");
//                                            SpUtils.setPhoneNum(context, bean.getData().getPhone());
//                                            Map<String, String> map2 = new LinkedHashMap<>();
//                                            map2.put("jnkjToken", bean.getAppToken());
//                                            ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
//                                                    .globalHeaders(map2);
//                                            finish();
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                });
//                            }
//                        });
                    }
                }
                break;
        }
    }

}
