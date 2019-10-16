package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.IndexAdapter;
import com.guoyu.fusemanagerapp.adapter.IndexGongnengAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.MenuBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_gongneng)
    RecyclerView rvGongneng;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private IndexGongnengAdapter gongnengAdapter;
    private List<MenuBean.DataBean> mList;
    private IndexAdapter indexAdapter;
    private List<String> mIndexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        ViseUtil.Get(context, NetUrl.AppUseradminGetMuen, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                MenuBean bean = gson.fromJson(s, MenuBean.class);
                mList = bean.getData();
                gongnengAdapter = new IndexGongnengAdapter(mList);
                GridLayoutManager manager = new GridLayoutManager(context, 4){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rvGongneng.setLayoutManager(manager);
                rvGongneng.setAdapter(gongnengAdapter);
            }
        });

        mIndexList = new ArrayList<>();
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        mIndexList.add("");
        indexAdapter = new IndexAdapter(mIndexList);
        LinearLayoutManager manager1 = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(indexAdapter);

    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3, R.id.rl_person})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl1:
                tv1.setTextColor(getResources().getColor(R.color.theme));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(Color.parseColor("#000000"));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                break;
            case R.id.rl2:
                tv1.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(getResources().getColor(R.color.theme));
                tv3.setTextColor(Color.parseColor("#000000"));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                break;
            case R.id.rl3:
                tv1.setTextColor(Color.parseColor("#000000"));
                tv2.setTextColor(Color.parseColor("#000000"));
                tv3.setTextColor(getResources().getColor(R.color.theme));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_person:
                intent.setClass(context, PersonActivity.class);
                startActivity(intent);
                break;
        }
    }

}
