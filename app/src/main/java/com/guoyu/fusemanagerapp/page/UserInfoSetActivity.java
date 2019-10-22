package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.UserInfoSetOpeningAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.PersonBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoSetActivity extends BaseActivity {

    private Context context = UserInfoSetActivity.this;

    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.tv_username)
    EditText tv_username;
    @BindView(R.id.tv_sex)
    EditText tv_sex;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private UserInfoSetOpeningAdapter adapter;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_set);
        ButterKnife.bind(UserInfoSetActivity.this);
        initData();
    }

    private void initData() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(UserInfoSetActivity.this));

        ViseUtil.Post(UserInfoSetActivity.this, NetUrl.AppUseradmingetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                PersonBean bean = gson.fromJson(s, PersonBean.class);
                tv_tel.setText(bean.getData().getUsername());
                tv_username.setText(bean.getData().getRealName());
                tv_sex.setText(bean.getData().getUserSex());
                switch (bean.getData().getStatus()) {
                    case 1:
                        tv_status.setText("已注册");
                        break;
                    case 2:
                        tv_status.setText("已认证");
                        break;
                    case 3:
                        tv_status.setText("审批拒绝");
                        break;
                    case 4:
                        tv_status.setText("审批中");
                        break;
                }
                String str = bean.getData().getFuncame();//根据逗号分隔到List数组中
                String str2 = str.replace(" ", "");//去掉所用空格
                List<String> list = Arrays.asList(str2.split(","));
                adapter = new UserInfoSetOpeningAdapter(list);
                LinearLayoutManager manager = new LinearLayoutManager(UserInfoSetActivity.this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recycler_view.setLayoutManager(manager);
                recycler_view.setAdapter(adapter);
            }
        });
    }

    private void SaveInfo() {
        String s = tv_username.getText().toString();
        String v = tv_sex.getText().toString();
        if (v.isEmpty() || s.isEmpty()) {
            ToastUtil.showShort(UserInfoSetActivity.this, "请完善信息在提交!");
        } else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("id", SpUtils.getUserId(UserInfoSetActivity.this));
            map.put("realName", s);
            map.put("sex", v);
            ViseUtil.Post(UserInfoSetActivity.this, NetUrl.AppUseradminUpdate, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.optString("status").equals("200")) {
                            ToastUtil.showShort(UserInfoSetActivity.this, jsonObject.optString("errorMsg"));
                            finish();
                        } else {
                            ToastUtil.showShort(UserInfoSetActivity.this, jsonObject.optString("errorMsg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   /* if(s.optString("status").equals("200")){
                        ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                    }else {
                        ToastUtil.showShort(context, jsonObject.optString("errorMsg"));
                    }*/
                }
            });
        }
    }

    @OnClick({R.id.btn_submits, R.id.iv_black})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_submits:
                SaveInfo();
                break;
            case R.id.iv_black:
                finish();
                break;
        }
    }
}
