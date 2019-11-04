package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.WeiguanListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;
import com.guoyu.fusemanagerapp.util.GlideUtils;
import com.guoyu.fusemanagerapp.util.StringUtils;
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

public class ReplyWeiguanActivity extends BaseActivity {

    private Context context = ReplyWeiguanActivity.this;

    @BindView(R.id.nine)
    NineGridTestLayout nine;
    @BindView(R.id.iv_header)
    ImageView ivHead;
    @BindView(R.id.tv_user)
    TextView tvName;
    @BindView(R.id.tv_addtime)
    TextView tvAddTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.et_content)
    EditText etContent;

    private WeiguanListBean.DataBean bean;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_weiguan);

        bean = (WeiguanListBean.DataBean) getIntent().getSerializableExtra("bean");
        ButterKnife.bind(ReplyWeiguanActivity.this);
        initData();

    }

    private void initData() {

        if(bean.getNikePic()!=null){
            String[] pics = bean.getNikePic().split(",");
            if(pics.length>0){
                GlideUtils.into(context, NetUrl.BASE_URL+pics[0], ivHead);
            }
        }
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
                finish();
                break;
        }
    }

    private void onSure() {

        String content = etContent.getText().toString();
        if(StringUtils.isEmpty(content)){
            ToastUtil.showShort(context, "反馈意见不能为空");
        }else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("id", bean.getId()+"");
            map.put("feeMemo", content);
            ViseUtil.Get(context, NetUrl.AppMiniCityInfoinsertFeedback, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    ToastUtil.showShort(context, "反馈成功");
                    finish();
                }
            });
        }

    }

}
