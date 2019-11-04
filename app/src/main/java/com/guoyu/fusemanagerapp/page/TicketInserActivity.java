package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.adapter.ComplaintInsertPicAdapter;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class TicketInserActivity extends AppCompatActivity {
    private Context context = TicketInserActivity.this;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.iv_del1)
    ImageView iv_del1;
    @BindView(R.id.rv_pic)
    RecyclerView rv_pic;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_content)
    EditText et_content;
    /*@BindView(R.id.et_jdxx)
    EditText et_jdxx;*/
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.et_sheshi)
    EditText et_sheshi;
    @BindView(R.id.et_jq_desc)
    EditText et_jq_desc;
    @BindView(R.id.et_menpiao)
    EditText et_menpiao;
    @BindView(R.id.et_jq_desc_zi)
    EditText et_jq_desc_zi;
    private int REQUEST_CODE = 101;
    private int REQUEST_CODES=102;
    private Dialog weiboDialog;
    private String pic="";
    private ComplaintInsertPicAdapter adapter;
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_inser);
        ButterKnife.bind(TicketInserActivity.this);
        initData();
    }
    private void initData(){
        mList = new ArrayList<>();
        adapter = new ComplaintInsertPicAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(TicketInserActivity.this, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_pic.setLayoutManager(manager);
        rv_pic.setAdapter(adapter);
        adapter.setListener(new ComplaintInsertPicAdapter.ClickListener() {
            @Override
            public void addImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9-mList.size()) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(TicketInserActivity.this, REQUEST_CODES); // 打开相册
            }

            @Override
            public void delimg(int pos) {
                mList.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
    }
    @OnClick({R.id.iv_black, R.id.identitycard,R.id.iv_del1,R.id.btn_add})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv_black:
                finish();
                break;
            case R.id.identitycard:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(TicketInserActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.iv_del1:
                pic="";
                Glide.with(TicketInserActivity.this).load("").into(iv_img);
                iv_del1.setVisibility(View.GONE);
                break;
            case R.id.btn_add:
                String title = et_title.getText().toString();//标题
                String content = et_content.getText().toString();//景区简介
                String menpiaozi = et_jq_desc_zi.getText().toString();//子门票信息
                String jqxq = et_jq_desc.getText().toString();//景区详情
                String menpiaos = et_menpiao.getText().toString();//门票价格
                /*String jdxx = et_jdxx.getText().toString();//景點信息*/
                String jddz = et_address.getText().toString();//景點地址
                String et_sheshi = et_menpiao.getText().toString();//景點設施
                if(title.isEmpty()||content.isEmpty()||menpiaozi.isEmpty()||jqxq.isEmpty()||menpiaos.isEmpty()||pic.isEmpty()||jddz.isEmpty()||et_sheshi.isEmpty()){
                    ToastUtil.showShort(context,"请把信息填写完整!");
                }else{
                    SaveInfo();
                }
                break;
        }
    }
    private void SaveInfo(){
        weiboDialog = WeiboDialogUtils.createLoadingDialog(context,"请等待...");

        Observable<Map<String, File>> observable = Observable.create(new ObservableOnSubscribe<Map<String, File>>() {
            @Override
            public void subscribe(final ObservableEmitter<Map<String, File>> e) throws Exception {
                final Map<String, File> fileMap = new LinkedHashMap<>();
                final List<File> fileList = new ArrayList<>();
                Luban.with(context)
                        .load(mList)
                        .ignoreBy(100)
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                fileList.add(file);
                                if(fileList.size() == mList.size()){
                                    for (int i = 0; i<fileList.size(); i++){
                                        fileMap.put("img"+i, fileList.get(i));//[" + i + "]
                                    }
                                    e.onNext(fileMap);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
            }
        });
        Observer<Map<String, File>> observer = new Observer<Map<String, File>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Map<String, File> value) {
                File file = new File(pic);
                String title = et_title.getText().toString();//标题
                String content = et_content.getText().toString();//景区简介
                String menpiaozi = et_jq_desc_zi.getText().toString();//子门票信息
                String jqxq = et_jq_desc.getText().toString();//景区详情
                String menpiaos = et_menpiao.getText().toString();//门票价格
                //String jdxx = et_jdxx.getText().toString();//景點信息
                String jddz = et_address.getText().toString();//景點地址
                String jdss = et_sheshi.getText().toString();//景點設施
                ViseHttp.UPLOAD(NetUrl.AppEntranceTicketInfoinsertEntranceTicketInfo)
                        .addParam("title", title)
                        .addParam("contentShort", content)
                        .addParam("content", jqxq)
                        .addParam("ticketMoney",menpiaos)
                        .addParam("facilities",jdss)
                        .addParam(" address",jddz)
                        .addParam("ticketMoneyMore",menpiaozi)
                        .addFile("file0", file)
                        .addFiles(value)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optString("status").equals("200")){
                                        ToastUtil.showShort(context,"发布成功");
                                        finish();
                                    }else {
                                        ToastUtil.showShort(context,jsonObject.optString("errorMsg"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                WeiboDialogUtils.closeDialog(weiboDialog);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                WeiboDialogUtils.closeDialog(weiboDialog);
                            }
                        });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);
            if (images.size() > 0) {
                 Glide.with(TicketInserActivity.this).load(images.get(0)).into(iv_img);
                iv_del1.setVisibility(View.VISIBLE);
                pic = images.get(0);
            }
        }else if(requestCode == REQUEST_CODES && data != null){
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            rv_pic.setVisibility(View.VISIBLE);
            mList.addAll(images);
            adapter.notifyDataSetChanged();
        }
    }
}
