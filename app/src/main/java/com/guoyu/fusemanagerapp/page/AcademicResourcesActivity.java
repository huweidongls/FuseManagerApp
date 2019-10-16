package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.DisciplineInfoAdapter;
import com.guoyu.fusemanagerapp.adapter.DisciplineInformationAdapter;
import com.guoyu.fusemanagerapp.adapter.GovernmentServiceListAdapter;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcademicResourcesActivity extends BaseActivity {
    private Context context = AcademicResourcesActivity.this;
    private DisciplineInformationAdapter adapter;
    private DisciplineInfoAdapter adapters;
    private List<String> mList;
    private List<String> mList2;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_view2)
    RecyclerView recycler_view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_resources);
        ButterKnife.bind(AcademicResourcesActivity.this);
        initType();
        initList();
    }
    private void initType(){
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new DisciplineInformationAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context,3);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(adapter);
    }
    private void initList(){
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
        adapters = new DisciplineInfoAdapter(mList2);
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
}
