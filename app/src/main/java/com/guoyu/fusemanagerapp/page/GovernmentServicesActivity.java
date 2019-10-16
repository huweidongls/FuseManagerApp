package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceListAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GovernmentServicesActivity extends BaseActivity {
    private Context context = GovernmentServicesActivity.this;
    private GovernmentServiceListAdapter adapter;
    private List<String> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
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
    }
    @OnClick({R.id.iv_black,R.id.rr_add})
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
        }
    }
}
