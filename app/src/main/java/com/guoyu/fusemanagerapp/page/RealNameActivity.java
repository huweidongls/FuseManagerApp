package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameActivity extends BaseActivity {

    private Context context = RealNameActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);

        ButterKnife.bind(RealNameActivity.this);

    }

    @OnClick(R.id.rl_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
