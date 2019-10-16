package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealAuditActivity extends BaseActivity {

    private Context context = RealAuditActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_audit);

        ButterKnife.bind(RealAuditActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
