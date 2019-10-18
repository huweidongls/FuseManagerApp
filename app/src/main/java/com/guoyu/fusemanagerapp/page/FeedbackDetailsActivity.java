package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackDetailsActivity extends BaseActivity {

    private Context context = FeedbackDetailsActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(FeedbackDetailsActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                intent.setClass(context, FeedbackContentActivity.class);
                startActivity(intent);
                break;
        }
    }

}
