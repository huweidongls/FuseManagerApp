package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.bean.NoticegetOneBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.HtmlFromUtils;
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
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_pic)
    LinearLayout llPic;
    private String id = "";
    private String title = "";
    private ImageView imageView;
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
        map.put("id", id);
        ViseUtil.Get(context, NetUrl.AppConvenienceNoticegetOne, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                NoticegetOneBean bean = gson.fromJson(s, NoticegetOneBean.class);
                HtmlFromUtils.setTextFromHtml(ConvenienceNoticeDetailsActivity.this, tvContent, bean.getData().getContent());
                if(bean.getData().getContentPic() != null){
                    String[] pics = bean.getData().getContentPic().split(",");
                    if(pics.length>0){
                        for (int i = 0; i<pics.length; i++){
                            imageView = new ImageView(context);
                            Glide.with(context).load(NetUrl.BASE_URL+pics[i]).into(imageView);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            imageView.setAdjustViewBounds(true);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.topMargin = 8;
                            llPic.addView(imageView, layoutParams);
                        }
                    }
                }else {
                    llPic.setVisibility(View.GONE);
                }
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
