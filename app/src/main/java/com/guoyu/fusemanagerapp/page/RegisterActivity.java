package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.net.NetUrl;
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

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private Dialog dialog;

    private String type = "";//0为注册1为忘记密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        type = getIntent().getStringExtra("type");
        ButterKnife.bind(RegisterActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("0")){
            tvTitle.setText("注册");
        }else {
            tvTitle.setText("忘记密码");
        }

    }

    @OnClick({R.id.rl_back, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                final String phone = etPhone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtil.showShort(context, "手机号不能为空");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确格式的手机号码");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    if(type.equals("0")){
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("phone", phone);
                        ViseUtil.Get(context, NetUrl.AppUserregisterPhone, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                    Intent intent = new Intent();
                                    intent.setClass(context, RegistrationTwoActivity.class);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("type", type);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }else {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("phone", phone);
                        ViseUtil.Get(context, NetUrl.AppUseradminrPhoneWjmm, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                    Intent intent = new Intent();
                                    intent.setClass(context, RegistrationTwoActivity.class);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("type", type);
                                    startActivity(intent);
                                    finish();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
                break;
        }
    }

}
