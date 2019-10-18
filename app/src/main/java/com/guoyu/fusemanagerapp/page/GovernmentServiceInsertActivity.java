package com.guoyu.fusemanagerapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GovernmentServiceInsertActivity extends AppCompatActivity {
    @BindView(R.id.spinner1)
    Spinner spinnertext ;
    private ArrayAdapter<String> adapters;
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_service_insert);
        ButterKnife.bind(GovernmentServiceInsertActivity.this);
        initData();
    }
    private void initData(){
       /* ViseUtil.Get(GovernmentServiceInsertActivity.this, NetUrl.AppGovernmentInfofindType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                CommunityServiceFenleiBean bean = gson.fromJson(s,CommunityServiceFenleiBean.class);
                for (CommunityServiceFenleiBean.DataBean bean2 : bean.getData()){
                    list2.add(bean2.getSubName());
                }
                adapter4 = new ArrayAdapter<String>(CommunityServiceActivity.this, android.R.layout.simple_spinner_item, list2);
                //第三步：设置下拉列表下拉时的菜单样式
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //第四步：将适配器添加到下拉列表上
                spinner2.setAdapter(adapter4);
            }
        });*/
        list.add("测试");
        list.add("测试");
        list.add("测试");
        list.add("测试");
        adapters = new ArrayAdapter<String>(GovernmentServiceInsertActivity.this, android.R.layout.simple_spinner_item, list);
        //第三步：设置下拉列表下拉时的菜单样式
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        spinnertext.setAdapter(adapters);
    }
}
