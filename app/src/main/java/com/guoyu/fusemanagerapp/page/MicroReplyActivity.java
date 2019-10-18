package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.MicroAuditAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.WeiguanListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MicroReplyActivity extends BaseActivity {

    private Context context = MicroReplyActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MicroAuditAdapter adapter;
    private List<WeiguanListBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_reply);

        ButterKnife.bind(MicroReplyActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("type", "1");
        ViseUtil.Get(context, NetUrl.AppMiniCityInfofindStatusid, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                WeiguanListBean bean = gson.fromJson(s, WeiguanListBean.class);
                mList = bean.getData();
                adapter = new MicroAuditAdapter(mList, 1);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
