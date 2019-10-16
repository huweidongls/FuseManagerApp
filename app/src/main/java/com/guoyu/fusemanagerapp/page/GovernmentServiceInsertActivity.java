package com.guoyu.fusemanagerapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.guoyu.fusemanagerapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GovernmentServiceInsertActivity extends AppCompatActivity {
    @BindView(R.id.spinner1)
    Spinner spinnertext ;
    private ArrayAdapter<String> adapters;
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_service_insert);
        initData();
    }
    private void initData(){
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
