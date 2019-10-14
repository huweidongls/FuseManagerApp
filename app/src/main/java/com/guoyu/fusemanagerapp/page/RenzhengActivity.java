package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RenzhengActivity extends BaseActivity {

    private Context context = RenzhengActivity.this;

    @BindView(R.id.iv_del)
    ImageView ivDel;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private int REQUEST_CODE = 101;
    private String pic1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renzheng);

        ButterKnife.bind(RenzhengActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.rl_add, R.id.iv_del})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(RenzhengActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.iv_del:
                pic1 = "";
                Glide.with(context).load("#ffffff").into(ivImg);//@mipmap/img0203x
                ivDel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (images.size() > 0) {
                Glide.with(context).load(images.get(0)).into(ivImg);
                pic1 = images.get(0);
                ivDel.setVisibility(View.VISIBLE);
            }
        }
    }

}
