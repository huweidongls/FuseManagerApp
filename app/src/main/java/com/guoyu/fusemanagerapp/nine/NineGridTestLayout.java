package com.guoyu.fusemanagerapp.nine;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.imagepreview.Consts;
import com.guoyu.fusemanagerapp.imagepreview.ImagePreviewActivity;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：
 * 时间：2016/5/12
 */
public class NineGridTestLayout extends NineGridLayout {

    protected static final int MAX_W_H_RATIO = 3;

    public NineGridTestLayout(Context context) {
        super(context);
    }

    public NineGridTestLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean displayOneImage(final RatioImageView imageView, String url, final int parentWidth) {
        Glide.with(mContext).load(url).into(imageView);
        return false;
    }

    @Override
    protected void displayImage(RatioImageView imageView, String url) {
//        ImageLoaderUtil.getImageLoader(mContext).displayImage(url, imageView, ImageLoaderUtil.getPhotoImageOption());
        Glide.with(mContext).load(url).into(imageView);
    }

    @Override
    protected void onClickImage(int i, String url, List<String> urlList) {
//        Toast.makeText(mContext, "点击了图片" + url, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(mContext, ImagePreviewActivity.class);
        intent.putExtra("imageList", (Serializable) urlList);
        intent.putExtra(Consts.START_ITEM_POSITION, i);
        intent.putExtra(Consts.START_IAMGE_POSITION, i);
//                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(getActivity(), imageView, imageView.getTransitionName());
        mContext.startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.photoview_open, 0);
    }
}
