package com.guoyu.fusemanagerapp.app;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.GlideUtils;
import com.guoyu.fusemanagerapp.util.RegisterTimeCount;
import com.guoyu.fusemanagerapp.util.SpUtils;
import com.sendtion.xrichtext.IImageLoader;
import com.sendtion.xrichtext.XRichText;
import com.vise.xsnow.http.ViseHttp;
import com.zzhoujay.richtext.RichText;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/12.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    // 修改密码获取验证码倒计时
    public static RegisterTimeCount registerTimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("jnkjToken", SpUtils.getToken(getApplicationContext()));
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                .globalHeaders(map);
        registerTimeCount = new RegisterTimeCount(60000, 1000);
        RichText.initCacheDir(this);
        XRichText.getInstance().setImageLoader(new IImageLoader() {
            @Override
            public void loadImage(final String imagePath, final ImageView imageView, final int imageHeight) {
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//固定图片高度，记得设置裁剪剧中
                lp.bottomMargin = 10;//图片的底边距
                imageView.setAdjustViewBounds(true);
                imageView.setLayoutParams(lp);
                GlideUtils.into(getApplicationContext(), imagePath, imageView);
            }
        });

    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
