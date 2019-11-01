package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.DisciplineInfoAdapter;
import com.guoyu.fusemanagerapp.adapter.DisciplineInformationAdapter;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceListAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.AcademicResourcesListBean;
import com.guoyu.fusemanagerapp.bean.AcademicResourcesTypeBean;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
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

public class AcademicResourcesActivity extends BaseActivity {

    private Context context = AcademicResourcesActivity.this;
    private DisciplineInformationAdapter adapter;
    private DisciplineInfoAdapter adapters;
    private List<AcademicResourcesTypeBean.DataBean> mList;
    private List<AcademicResourcesListBean.DataBean> mList2;
    private int page = 1;
    @BindView(R.id.refreshs)
    SmartRefreshLayout refreshs;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;
    @BindView(R.id.empty_order_bloacks)
    RelativeLayout empty_order_bloacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_resources);
        ButterKnife.bind(AcademicResourcesActivity.this);
        initType();
        initList();
        initRefreshs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initList();
    }

    private void initRefreshs() {
        refreshs.setRefreshHeader(new MaterialHeader(AcademicResourcesActivity.this
        ));
        refreshs.setRefreshFooter(new ClassicsFooter(AcademicResourcesActivity.this));
        refreshs.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageSize", "10");
                map.put("pageNum", "1");
                ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
                        mList2.clear();
                        mList2.addAll(bean.getData());
                        adapters.notifyDataSetChanged();
                        page = 2;
                    }
                });
                refreshLayout.finishRefresh(1000);
            }
        });
        refreshs.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageNum", page + "");
                map.put("pageSize", "10");
                ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
                        // mList.clear();
                        mList2.addAll(bean.getData());
                        adapters.notifyDataSetChanged();
                        page = page + 1;
                    }
                });
                refreshLayout.finishLoadMore(1000);
            }
        });
    }

    private void initType() {
        ViseUtil.Get(context, NetUrl.AppEducationInfofindType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AcademicResourcesTypeBean bean = gson.fromJson(s, AcademicResourcesTypeBean.class);
                mList = bean.getData();
                adapter = new DisciplineInformationAdapter(mList);
                GridLayoutManager manager = new GridLayoutManager(context, 3);
                recycler_view.setLayoutManager(manager);
                recycler_view.setAdapter(adapter);
            }
        });
    }

    private void initList() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "10");
        map.put("pageNum", "1");
        ViseUtil.Get(context, NetUrl.AppEducationInfoqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                AcademicResourcesListBean bean = gson.fromJson(s, AcademicResourcesListBean.class);
                mList2 = bean.getData();
                if (mList2.size() > 0) {
                    adapters = new DisciplineInfoAdapter(mList2);
                    LinearLayoutManager manager = new LinearLayoutManager(context) {
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
                    page = 2;
                } else {
                    empty_order_bloacks.setVisibility(View.VISIBLE);
                    refreshs.setVisibility(View.GONE);
                }

            }
        });
    }

    @OnClick({R.id.iv_black, R.id.rr_add})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context, AcademicResourcesInsertActivity.class);
                context.startActivity(intent);
                break;
        }
    }
}
