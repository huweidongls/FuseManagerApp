package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConvenienceNoticeDetailsActivity extends BaseActivity {

    private Context context = ConvenienceNoticeDetailsActivity.this;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private String id = "";
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenience_notice_details);

        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
        ButterKnife.bind(ConvenienceNoticeDetailsActivity.this);
        initData();

    }

    private void initData() {

        tvTitle.setText(title+"详情");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("ID", id);
        ViseUtil.Get(context, NetUrl.AppConvenienceNoticegetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }
        });

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
