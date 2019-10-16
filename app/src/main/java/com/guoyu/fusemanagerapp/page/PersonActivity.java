package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.PersonBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    private Context context = PersonActivity.this;

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ButterKnife.bind(PersonActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", SpUtils.getUserId(context));
        Logger.e("123123", SpUtils.getUserId(context)+"id"+"token"+SpUtils.getToken(context));
        ViseUtil.Post(context, NetUrl.AppUseradmingetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                PersonBean bean = gson.fromJson(s, PersonBean.class);
                tvName.setText(bean.getData().getRealName());
                tvPhone.setText(bean.getData().getUsername());
            }
        });

    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3, R.id.ll4, R.id.ll5, R.id.ll6, R.id.ll7})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll1:
                intent.setClass(context, MicroAuditActivity.class);
                startActivity(intent);
                break;
            case R.id.ll2:
                intent.setClass(context, MicroReplyActivity.class);
                startActivity(intent);
                break;
            case R.id.ll3:
                intent.setClass(context, RealAuditActivity.class);
                startActivity(intent);
                break;
            case R.id.ll4:
                intent.setClass(context, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.ll5:
                intent.setClass(context, GovernmentServicesActivity.class);
                startActivity(intent);
                break;
            case R.id.ll6:
                intent.setClass(context, AcademicResourcesActivity.class);
                startActivity(intent);
                break;
            case R.id.ll7:
                intent.setClass(context, TicketServiceActivity.class);
                startActivity(intent);
                break;
        }
    }

}
