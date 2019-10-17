package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceListAdapter;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceTypeAdapter;
import com.guoyu.fusemanagerapp.adapter.TicketHlistAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceTypeBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GovernmentServicesActivity extends BaseActivity {
    private Context context = GovernmentServicesActivity.this;
    private GovernmentServiceListAdapter adapter;
    private GovernmentServiceTypeAdapter adapters;
    private List<String> mList;
    private List<GovernmentServiceTypeBean.DataBean> mLists;
    private int radio=0;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_viewtype)
    RecyclerView recycler_viewtype;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.rl_type)
    RelativeLayout rl_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_services);
        ButterKnife.bind(GovernmentServicesActivity.this);
        initData();
    }
    private void initData(){
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new GovernmentServiceListAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(adapter);

        ViseUtil.Get(context, NetUrl.AppGovernmentInfofindType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GovernmentServiceTypeBean bean = gson.fromJson(s,GovernmentServiceTypeBean.class);
                mLists = bean.getData();
                adapters = new GovernmentServiceTypeAdapter(mLists);
                GridLayoutManager managers = new GridLayoutManager(context,4);
                recycler_viewtype.setLayoutManager(managers);
                recycler_viewtype.setAdapter(adapters);
            }
        });
        /*mLists = new ArrayList<>();
        mLists.add("");
        mLists.add("");
        mLists.add("");
        mLists.add("");
        mLists.add("");
        mLists.add("");
        mLists.add("");
        mLists.add("");*/

    }
    @OnClick({R.id.iv_black,R.id.rr_add,R.id.iv_type})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context,GovernmentServiceInsertActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_type:
                if(radio==0){
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    rl_type.setVisibility(View.VISIBLE);
                    radio=1;
                }else{
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    rl_type.setVisibility(View.GONE);
                    radio=0;
                }
                break;
        }
    }
}
