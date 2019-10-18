package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.WeiguanListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

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

    private WeiguanListBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_weiguan);

        bean = (WeiguanListBean.DataBean) getIntent().getSerializableExtra("bean");
        ButterKnife.bind(AuditWeiguanActivity.this);
        initData();

    }

    private void initData() {

        Glide.with(context).load(NetUrl.BASE_URL+bean.getNikePic()).into(ivHead);
        tvName.setText(bean.getNickName());
        tvAddTime.setText("发布时间："+bean.getPublishDate());
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



    }

    /**
     * 审核通过
     */
    private void onSure() {



    }

}
