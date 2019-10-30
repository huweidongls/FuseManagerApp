package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.SpUtils;

public class WelcomeActivity extends BaseActivity {

    private Context context = WelcomeActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initData();

    }

    private void initData() {

        Intent intent = new Intent();
        String uid = SpUtils.getUserId(context);
        if(uid.equals("0")){
            intent.setClass(context, LoginActivity.class);
        }else {
            intent.setClass(context, MainActivity.class);
        }
        startActivity(intent);
        finish();

    }
}
