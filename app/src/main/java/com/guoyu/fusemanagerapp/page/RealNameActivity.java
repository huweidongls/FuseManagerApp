package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.UserGetoneBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.GlideUtils;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameActivity extends BaseActivity {

    private Context context = RealNameActivity.this;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private String id = "";
    private String status = "";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);

        status = getIntent().getStringExtra("status");
        id = getIntent().getStringExtra("id");
        ButterKnife.bind(RealNameActivity.this);
        initData();

    }

    private void initData() {

        if(!status.equals("4")){
            llBottom.setVisibility(View.GONE);
        }

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.CitizenUsergetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                UserGetoneBean bean = gson.fromJson(s, UserGetoneBean.class);
                tvName.setText(bean.getData().getRealName());
                tvCard.setText(bean.getData().getAppuserId());
                tvPhone.setText(bean.getData().getPhone());
                String[] ss = bean.getData().getAppuserPics().split(",");
                GlideUtils.into(context, NetUrl.BASE_URL+ss[0], iv1);
                GlideUtils.into(context, NetUrl.BASE_URL+ss[1], iv2);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_sure, R.id.tv_cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_sure:
                onSure();
                break;
            case R.id.tv_cancel:
                onCancel();
                break;
        }
    }

    /**
     * 审核驳回
     */
    private void onCancel() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("status", "3");
        ViseUtil.Get(context, NetUrl.AppUsercitizenUserAudit, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "审核驳回");
                finish();
            }
        });

    }

    /**
     * 审核通过
     */
    private void onSure() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("status", "2");
        ViseUtil.Get(context, NetUrl.AppUsercitizenUserAudit, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "审核通过");
                finish();
            }
        });

    }

}
