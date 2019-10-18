package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.DisciplineInfoAdapter;
import com.guoyu.fusemanagerapp.adapter.DisciplineInformationAdapter;
import com.guoyu.fusemanagerapp.adapter.TickerSlistAdapter;
import com.guoyu.fusemanagerapp.adapter.TicketHlistAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceListBean;
import com.guoyu.fusemanagerapp.bean.TicketServiceListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TicketServiceActivity extends BaseActivity {
    private Context context = TicketServiceActivity.this;
    private TicketHlistAdapter adapter;
    private TickerSlistAdapter adapters;
    private List<TicketServiceListBean.DataBean> mList;
    private List<TicketServiceListBean.DataBean> mList2;
    @BindView(R.id.empty_order_bloacks)
    RelativeLayout empty_order_bloacks;
    @BindView(R.id.empty_order_bloackss)
    RelativeLayout empty_order_bloackss;
    @BindView(R.id.refreshs)
    SmartRefreshLayout refreshs;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_viewj)
    RecyclerView recycler_view2;
    @BindView(R.id.et_titles)
    EditText et_titles;
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_service);
        ButterKnife.bind(TicketServiceActivity.this);
        initHlist();
        initSList();
        initRefreshs();
    }
    private void initRefreshs() {
        refreshs.setRefreshHeader(new MaterialHeader(TicketServiceActivity.this
        ));
        refreshs.setRefreshFooter(new ClassicsFooter(TicketServiceActivity.this));
        refreshs.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String,String> map = new LinkedHashMap<>();
                map.put("pageSize","10");
                map.put("pageNum","1");
                ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        TicketServiceListBean bean = gson.fromJson(s,TicketServiceListBean.class);
                        mList2.clear();
                        mList2.addAll(bean.getData());
                        adapters.notifyDataSetChanged();
                        page = 2;
                        refreshLayout.finishRefresh(500);
                    }
                });
            }
        });
        refreshs.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Map<String,String> map = new LinkedHashMap<>();
                map.put("pageNum",page+"");
                map.put("pageSize","10");
                ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        TicketServiceListBean bean = gson.fromJson(s,TicketServiceListBean.class);
                        mList2.addAll(bean.getData());
                        adapters.notifyDataSetChanged();
                        page = page+1;
                        refreshLayout.finishLoadMore(500);
                    }
                });
            }
        });
    }
    private void initHlist(){
        ViseUtil.Get(context, NetUrl.AppEntranceTicketInfofindAllNew, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                TicketServiceListBean bean = gson.fromJson(s,TicketServiceListBean.class);
                mList = bean.getData();
                if(mList.size()>0){//empty_order_bloackss
                    adapter = new TicketHlistAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recycler_view.setLayoutManager(manager);
                    recycler_view.setAdapter(adapter);
                    empty_order_bloackss.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                }else{
                    empty_order_bloackss.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }

            }
        });
    }
    private void initSList(){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("pageSize","10");
        map.put("pageNum","1");
        ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                TicketServiceListBean bean = gson.fromJson(s,TicketServiceListBean.class);
                mList2 = bean.getData();
                if(mList2.size()>0){
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
                    empty_order_bloacks.setVisibility(View.GONE);
                    refreshs.setVisibility(View.VISIBLE);
                    page=2;
                }else{
                    empty_order_bloacks.setVisibility(View.VISIBLE);
                    refreshs.setVisibility(View.GONE);
                }

            }
        });

    }
    private void show_search(){
        String s = et_titles.getText().toString();
        if (s.isEmpty()){
            ToastUtil.showShort(TicketServiceActivity.this,"请填写要搜索的内容!");
        }else{
            Map<String,String> map = new LinkedHashMap<>();
            map.put("pageSize","10");
            map.put("pageNum","1");
            map.put("title",s);
            ViseUtil.Get(context, NetUrl.AppEntranceTicketInfoqueryList, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    TicketServiceListBean bean = gson.fromJson(s,TicketServiceListBean.class);
                    mList2 = bean.getData();
                    if(mList2.size()>0){
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
                        empty_order_bloacks.setVisibility(View.GONE);
                        refreshs.setVisibility(View.VISIBLE);
                        page=2;
                    }else{
                        empty_order_bloacks.setVisibility(View.VISIBLE);
                        refreshs.setVisibility(View.GONE);
                    }

                }
            });
        }

    }
    @OnClick({R.id.iv_black,R.id.rr_add,R.id.iv_btn})
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
            case R.id.iv_btn:
                show_search();
                break;
        }
    }
}
