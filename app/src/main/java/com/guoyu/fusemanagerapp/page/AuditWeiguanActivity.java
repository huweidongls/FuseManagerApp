package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.WeiguanListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;
import com.guoyu.fusemanagerapp.util.GlideUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuditWeiguanActivity extends BaseActivity {

    private Context context = AuditWeiguanActivity.this;

    @BindView(R.id.nine)
    NineGridTestLayout nine;
    @BindView(R.id.iv_header)
    ImageView ivHead;
    @BindView(R.id.tv_user)
    TextView tvName;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_addtime)
    TextView tvAddTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;

    private WeiguanListBean.DataBean bean;
    private String type = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_weiguan);

        type = getIntent().getStringExtra("type");
        bean = (WeiguanListBean.DataBean) getIntent().getSerializableExtra("bean");
        ButterKnife.bind(AuditWeiguanActivity.this);
        initData();

    }

    private void initData() {

        if(type.equals("0")){
            llBottom.setVisibility(View.VISIBLE);
        }else {
            llBottom.setVisibility(View.GONE);
        }

        if(bean.getNikePic()!=null){
            String[] pics = bean.getNikePic().split(",");
            if(pics.length>0){
                GlideUtils.into(context, NetUrl.BASE_URL+pics[0], ivHead);
            }
        }
        tvName.setText(bean.getNickName());
        tvAddTime.setText("发布时间："+bean.getCreateDate());
        tvTitle.setText(bean.getTitle());
        tvSubTitle.setText(bean.getContentTop());
        tvContent.setText(bean.getContent());
        String[] s = bean.getContentPic().split(",");
        List<String> list = new ArrayList<>();
        for (String ss : s){
            list.add(NetUrl.BASE_URL+ss);
        }
        nine.setUrlList(list);

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
        map.put("id", bean.getId()+"");
        map.put("state", "4");
        ViseUtil.Get(context, NetUrl.AppMiniCityInfoupdateStatusid, map, dialog, new ViseUtil.ViseListener() {
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
        map.put("id", bean.getId()+"");
        map.put("state", "2");
        ViseUtil.Get(context, NetUrl.AppMiniCityInfoupdateStatusid, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "审核通过");
                finish();
            }
        });

    }

}
