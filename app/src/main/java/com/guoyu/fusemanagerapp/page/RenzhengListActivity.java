package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.RenzhengListAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.APPFuncInfoApplyFoorListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.StringUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenzhengListActivity extends BaseActivity {

    private Context context = RenzhengListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private RenzhengListAdapter adapter;
    private List<APPFuncInfoApplyFoorListBean.DataBean> mList;

    private String funName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzheng_list);

        funName = getIntent().getStringExtra("funName");
        ButterKnife.bind(RenzhengListActivity.this);
        initData();

    }

    private void initData() {

        ViseUtil.Get(context, NetUrl.APPFuncInfoApplyFoorList, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                APPFuncInfoApplyFoorListBean bean = gson.fromJson(s, APPFuncInfoApplyFoorListBean.class);
                mList = bean.getData();
                if(StringUtils.isEmpty(funName)){
                    adapter = new RenzhengListAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }else {
                    String[] ss = funName.split(",");
                    for (String sss : ss){
                        for (int i = 0; i<mList.size(); i++){
                            if(mList.get(i).getFunName().equals(sss)){
                                mList.get(i).setIsSelect(1);
                            }
                        }
                    }
                    adapter = new RenzhengListAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_commit})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_commit:
                intent.putExtra("mList", (Serializable) mList);
                setResult(1002, intent);
                finish();
                break;
        }
    }

}
