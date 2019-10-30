package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.ComplaintInsertPicAdapter;
import com.guoyu.fusemanagerapp.adapter.FeedbackDetailsImgAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.FeedbackDetailsBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackDetailsActivity extends BaseActivity {

    private Context context = FeedbackDetailsActivity.this;

    private String id = "";
    private FeedbackDetailsImgAdapter adapter;
    private List<String> mList;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_ftitle)
    TextView et_ftitle;
    @BindView(R.id.tv_bumen)
    TextView tv_bumen;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.rv_pic)
    RecyclerView rv_pic;
    @BindView(R.id.tv_leixing)
    TextView tv_leixing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);
        id = getIntent().getStringExtra("id");
        ButterKnife.bind(FeedbackDetailsActivity.this);
        initData();
        //initImgList();
    }
    private void initData(){//FeedbackDetailsBean
        Map<String,String> map = new LinkedHashMap<>();
        map.put("id",id);
        ViseUtil.Get(context, NetUrl.AppConsultationInfogetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                FeedbackDetailsBean bean = gson.fromJson(s,FeedbackDetailsBean.class);
                tv_title.setText(bean.getData().getTitle());
                et_ftitle.setText(bean.getData().getContentTop());
                tv_bumen.setText(bean.getData().getDeptName());
                tv_content.setText(bean.getData().getContent());
                switch (bean.getData().getConsType()){
                    case 5:
                        tv_leixing.setText("投诉");
                        break;
                    case 4:
                        tv_leixing.setText("咨询");
                        break;
                    case 19:
                        tv_leixing.setText("其他");
                        break;
                }
                   String str2=bean.getData().getContentPic().replace(" ", "");
                List<String> list= Arrays.asList(str2.split(","));
                adapter = new FeedbackDetailsImgAdapter(list);
                GridLayoutManager manager = new GridLayoutManager(FeedbackDetailsActivity.this, 3){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rv_pic.setLayoutManager(manager);
                rv_pic.setAdapter(adapter);
            }
        });
    }
    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                intent.setClass(context, FeedbackContentActivity.class);
                intent.putExtra("cid",id);
                startActivity(intent);
                finish();
                break;
        }
    }

}
