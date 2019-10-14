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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.LoginBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.Logger;
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

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private boolean isShowPwd = false;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl, R.id.btn, R.id.tv_register, R.id.tv_forgot})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.icon0063x).into(iv);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.login_icon033x).into(iv);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.btn:
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(name)|| StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "账号或密码不能为空");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("phone", name);
                    map.put("psd", pwd);
                    ViseUtil.Post(context, NetUrl.AppUserappAdmuinLogin, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Gson gson = new Gson();
                            LoginBean bean = gson.fromJson(s, LoginBean.class);
                            ToastUtil.showShort(context, bean.getErrorMsg());
                            SpUtils.setToken(context, bean.getUserNameFromToken());
                            SpUtils.setUserId(context, bean.getData().getId()+"");
                            SpUtils.setPhoneNum(context, bean.getData().getUsername());
                            Map<String, String> map = new LinkedHashMap<>();
                            map.put("jnkjToken", bean.getUserNameFromToken());
                            ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                                    .globalHeaders(map);



                            Intent intent1 = new Intent();
                            intent1.setClass(context, MainActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    });
                }
                break;
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forgot:
                intent.setClass(context, RegisterActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                finish();
                break;
        }
    }

}
