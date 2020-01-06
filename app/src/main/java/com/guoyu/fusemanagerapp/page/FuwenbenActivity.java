package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ImageUtil;
import com.guoyu.fusemanagerapp.util.Logger;
import com.guoyu.fusemanagerapp.util.StringUtil;
import com.guoyu.fusemanagerapp.util.StringUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.sendtion.xrichtext.RichTextEditor;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FuwenbenActivity extends BaseActivity {

    private Context context = FuwenbenActivity.this;

    @BindView(R.id.et_new_content)
    RichTextEditor et_new_content;

    private int REQUEST_CODE = 101;

    private String content = "";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwenben);

        content = getIntent().getStringExtra("content");
        ButterKnife.bind(FuwenbenActivity.this);
        initData();

    }

    private void initData() {

        if(!StringUtils.isEmpty(content)){
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待");
            showDataSync(content);
        }

    }

    /**
     * 异步方式显示数据
     */
    private void showDataSync(final String html){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) {
                showEditData(emitter, html);
            }
        })
                //.onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {
                        WeiboDialogUtils.closeDialog(dialog);
                        if (et_new_content != null) {
                            //在图片全部插入完毕后，再插入一个EditText，防止最后一张图片后无法插入文字
                            et_new_content.addEditTextAtIndex(et_new_content.getLastIndex(), "");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        WeiboDialogUtils.closeDialog(dialog);
                        ToastUtil.showShort(context, "解析错误：图片不存在或已损坏");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
//                        subsLoading = d;
                    }

                    @Override
                    public void onNext(String text) {
                        try {
                            if (et_new_content != null) {
                                if (text.contains("<img") && text.contains("src=")) {
                                    //imagePath可能是本地路径，也可能是网络地址
                                    String imagePath = StringUtil.getImgSrc(text);
                                    //Log.e("---", "###imagePath=" + imagePath);
                                    //插入空的EditText，以便在图片前后插入文字
                                    et_new_content.addEditTextAtIndex(et_new_content.getLastIndex(), "");
                                    et_new_content.addImageViewAtIndex(et_new_content.getLastIndex(), imagePath);
                                } else {
                                    et_new_content.addEditTextAtIndex(et_new_content.getLastIndex(), text);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 显示数据
     */
    protected void showEditData(ObservableEmitter<String> emitter, String html) {
        try{
            List<String> textList = StringUtil.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                emitter.onNext(text);
            }
            emitter.onComplete();
        }catch (Exception e){
            e.printStackTrace();
            emitter.onError(e);
        }
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    private String getEditData() {
        StringBuilder content = new StringBuilder();
        try {
            List<RichTextEditor.EditData> editList = et_new_content.buildEditData();
            for (RichTextEditor.EditData itemData : editList) {
                if (itemData.inputStr != null) {
                    content.append(itemData.inputStr);
                } else if (itemData.imagePath != null) {
                    content.append("<img src=\"").append(itemData.imagePath).append("\"/>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @OnClick({R.id.rl_back, R.id.rl_pic, R.id.tv_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_pic:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(FuwenbenActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.tv_save:
                String content = getEditData();
                if(content.length()<=2000){
                    Logger.e("123123", content);
                    Intent intent = new Intent();
                    intent.putExtra("content", content);
                    setResult(1002, intent);
                    finish();
                }else{
                    ToastUtil.showShort(context, "内容超出2000字， 请删减之后保存");
                }
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
                for (String pic : images){
                    File file = new File(pic);
                    ViseHttp.UPLOAD(NetUrl.UploadImgappPicture)
                            .addFile("file0", file)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optString("status").equals("200")){
                                            et_new_content.insertImage(NetUrl.BASE_URL+jsonObject.optString("data"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });
                }
            }
        }
    }

}
