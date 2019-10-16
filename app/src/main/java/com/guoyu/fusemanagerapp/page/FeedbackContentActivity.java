package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackContentActivity extends BaseActivity {

    private Context context = FeedbackContentActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_content);

        ButterKnife.bind(FeedbackContentActivity.this);

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
