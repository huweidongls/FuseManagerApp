package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.UserGetoneBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealNameActivity extends BaseActivity {

    private Context context = RealNameActivity.this;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card)
    TextView tvCard;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(RealNameActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.CitizenUsergetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                UserGetoneBean bean = gson.fromJson(s, UserGetoneBean.class);
                tvName.setText(bean.getData().getRealName());
                tvCard.setText(bean.getData().getAppuserId());
                tvPhone.setText(bean.getData().getPhone());
                String[] ss = bean.getData().getAppuserPics().split(",");
                Glide.with(context).load(NetUrl.BASE_URL+ss[0]).into(iv1);
                Glide.with(context).load(NetUrl.BASE_URL+ss[1]).into(iv2);
                Logger.e("123123", ss[0]+"----"+ss[1]);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_sure, R.id.tv_cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_sure:

                break;
            case R.id.tv_cancel:

                break;
        }
    }

}
