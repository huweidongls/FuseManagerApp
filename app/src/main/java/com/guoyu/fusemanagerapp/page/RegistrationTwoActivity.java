package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.app.MyApplication;
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

public class RegistrationTwoActivity extends BaseActivity {

    private Context context = RegistrationTwoActivity.this;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_send_code)
    TextView tvGetCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String phone = "";
    private Dialog dialog;
    private String type = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);

        phone = getIntent().getStringExtra("phone");
        type = getIntent().getStringExtra("type");
        MyApplication.registerTimeCount.setActivity(RegistrationTwoActivity.this);
        ButterKnife.bind(RegistrationTwoActivity.this);
        initData();

    }

    private void initData() {

        MyApplication.registerTimeCount.start();
        tv.setText("验证码已发送至手机"+phone);
        if(type.equals("0")){
            tvTitle.setText("注册");
        }else {
            tvTitle.setText("忘记密码");
        }

    }

    @OnClick({R.id.rl_back, R.id.tv_send_code, R.id.btn_commit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_send_code:
                MyApplication.registerTimeCount.start();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("phone", phone);
                ViseUtil.Get(context, NetUrl.AppUserregisterPhone, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.btn_commit:
                String eCode = etCode.getText().toString();
                if(StringUtils.isEmpty(eCode)){
                    ToastUtil.showShort(context, "验证码不能为空");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map1 = new LinkedHashMap<>();
                    map1.put("phone", phone);
                    map1.put("code", eCode);
                    ViseUtil.Get(context, NetUrl.AppUseryzmCode, map1, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                                Intent intent = new Intent();
                                intent.setClass(context, RegistrationThreeActivity.class);
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
                break;
        }
    }

}
