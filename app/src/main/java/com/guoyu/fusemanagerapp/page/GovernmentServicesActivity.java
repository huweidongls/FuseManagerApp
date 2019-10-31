package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceListAdapter;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceTypeAdapter;
import com.guoyu.fusemanagerapp.adapter.TicketHlistAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceListBean;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceTypeBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.SpUtils;
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

public class GovernmentServicesActivity extends BaseActivity {

    private Context context = GovernmentServicesActivity.this;
    private GovernmentServiceListAdapter adapter;
    private GovernmentServiceTypeAdapter adapters;
    private List<GovernmentServiceListBean.DataBean> mList;
    private List<GovernmentServiceTypeBean.DataBean> mLists;
    private int radio = 0;
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
    @BindView(R.id.empty_order_bloacks)
    RelativeLayout empty_order_bloacks;
    @BindView(R.id.refreshs)
    SmartRefreshLayout refreshs;
    private int page = 1;
    @BindView(R.id.et_titles)
    EditText et_titles;
    private int typeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_services);
        ButterKnife.bind(GovernmentServicesActivity.this);
        Log.e("787878787878", SpUtils.getToken(context));
        //initData();
        initRefreshs();
        init_type();
    }

    private void initRefreshs() {
        refreshs.setRefreshHeader(new MaterialHeader(GovernmentServicesActivity.this
        ));
        refreshs.setRefreshFooter(new ClassicsFooter(GovernmentServicesActivity.this));
        refreshs.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageSize", "10");
                map.put("pageNum", "1");
                if (typeId != 0) {
                    map.put("typeId", typeId + "");
                }
                ViseUtil.Get(context, NetUrl.AppGovernmentInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GovernmentServiceListBean bean = gson.fromJson(s, GovernmentServiceListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = 2;
                        refreshLayout.finishRefresh(500);
                    }
                });
            }
        });
        refreshs.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageNum", page + "");
                map.put("pageSize", "10");
                if (typeId != 0) {
                    map.put("typeId", typeId + "");
                }
                ViseUtil.Get(context, NetUrl.AppGovernmentInfoqueryList, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GovernmentServiceListBean bean = gson.fromJson(s, GovernmentServiceListBean.class);
                        // mList.clear();
                        mList.addAll(bean.getData());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
                        refreshLayout.finishLoadMore(500);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageSize", "10");
        map.put("pageNum", "1");
        ViseUtil.Get(context, NetUrl.AppGovernmentInfoqueryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GovernmentServiceListBean bean = gson.fromJson(s, GovernmentServiceListBean.class);
                mList = bean.getData();
                if (mList.size() > 0) {
                    adapter = new GovernmentServiceListAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(context) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    };
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recycler_view.setLayoutManager(manager);
                    recycler_view.setAdapter(adapter);
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

    private void init_type() {
        ViseUtil.Get(context, NetUrl.AppGovernmentInfofindType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GovernmentServiceTypeBean bean = gson.fromJson(s, GovernmentServiceTypeBean.class);
                mLists = bean.getData();
                adapters = new GovernmentServiceTypeAdapter(mLists, new GovernmentServiceTypeAdapter.ClickListener() {
                    @Override
                    public void onClickType(int pos) {
                        typeId = mLists.get(pos).getId();
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("pageNum", "1");
                        map.put("pageSize", "10");
                        map.put("typeId", mLists.get(pos).getId() + "");
                        ViseUtil.Get(context, NetUrl.AppGovernmentInfoqueryList, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Gson gson = new Gson();
                                GovernmentServiceListBean bean = gson.fromJson(s, GovernmentServiceListBean.class);
                                mList = bean.getData();
                                if (mList.size() > 0) {
                                    adapter = new GovernmentServiceListAdapter(mList);
                                    LinearLayoutManager manager = new LinearLayoutManager(context) {
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recycler_view.setLayoutManager(manager);
                                    recycler_view.setAdapter(adapter);
                                    empty_order_bloacks.setVisibility(View.GONE);
                                    refreshs.setVisibility(View.VISIBLE);
                                } else {
                                    empty_order_bloacks.setVisibility(View.VISIBLE);
                                    refreshs.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                });
                GridLayoutManager managers = new GridLayoutManager(context, 4);
                recycler_viewtype.setLayoutManager(managers);
                recycler_viewtype.setAdapter(adapters);
            }
        });
    }

    private void show_search() {
        String title = et_titles.getText().toString();//标题
        if (TextUtils.isEmpty(title)) {
            ToastUtil.showShort(GovernmentServicesActivity.this, "请填写要搜索的内容!");
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("pageNum", "1");
            map.put("pageSize", "10");
            map.put("title", title);
            ViseUtil.Get(context, NetUrl.AppGovernmentInfoqueryList, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Gson gson = new Gson();
                    GovernmentServiceListBean bean = gson.fromJson(s, GovernmentServiceListBean.class);
                    mList = bean.getData();
                    if (mList.size() > 0) {
                        adapter = new GovernmentServiceListAdapter(mList);
                        LinearLayoutManager manager = new LinearLayoutManager(context) {
                            @Override
                            public boolean canScrollVertically() {
                                return false;
                            }
                        };
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recycler_view.setLayoutManager(manager);
                        recycler_view.setAdapter(adapter);
                        empty_order_bloacks.setVisibility(View.GONE);
                        refreshs.setVisibility(View.VISIBLE);
                        //page=2;
                    } else {
                        empty_order_bloacks.setVisibility(View.VISIBLE);
                        refreshs.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @OnClick({R.id.iv_black, R.id.rr_add, R.id.iv_type, R.id.iv_btn})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_black:
                finish();
                break;
            case R.id.rr_add:
                intent.setClass(context, GovernmentServiceInsertActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_type:
                if (radio == 0) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    rl_type.setVisibility(View.VISIBLE);
                    radio = 1;
                } else {
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    rl_type.setVisibility(View.GONE);
                    radio = 0;
                }
                break;
            case R.id.iv_btn:
                show_search();
                break;
        }
    }
}
