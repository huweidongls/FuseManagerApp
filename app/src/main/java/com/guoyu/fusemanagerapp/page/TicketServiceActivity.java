package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.DisciplineInfoAdapter;
import com.guoyu.fusemanagerapp.adapter.DisciplineInformationAdapter;
import com.guoyu.fusemanagerapp.adapter.TickerSlistAdapter;
import com.guoyu.fusemanagerapp.adapter.TicketHlistAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicketServiceActivity extends BaseActivity {
    private Context context = TicketServiceActivity.this;
    private TicketHlistAdapter adapter;
    private TickerSlistAdapter adapters;
    private List<String> mList;
    private List<String> mList2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_viewj)
    RecyclerView recycler_view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_service);
        ButterKnife.bind(TicketServiceActivity.this);
        initHlist();
        initSList();
    }
    private void initHlist(){
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new TicketHlistAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context,3);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(adapter);
    }
    private void initSList(){
        mList2 = new ArrayList<>();
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        mList2.add("");
        adapters = new TickerSlistAdapter(mList2);
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view2.setLayoutManager(manager);
        recycler_view2.setAdapter(adapters);
    }
    @OnClick({R.id.iv_black,R.id.rr_add})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context,TicketInserActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
