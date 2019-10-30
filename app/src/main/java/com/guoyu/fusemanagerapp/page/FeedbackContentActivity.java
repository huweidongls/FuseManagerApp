package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackContentActivity extends BaseActivity {

    private Context context = FeedbackContentActivity.this;

    private String cid = "";
    @BindView(R.id.et_feemo)
    EditText et_feemo;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_content);
        cid = getIntent().getStringExtra("cid");
        ButterKnife.bind(FeedbackContentActivity.this);

    }
    private void save(){
        String s = et_feemo.getText().toString();
        if(s.isEmpty()){
            ToastUtil.showShort(context,"请填写反馈信息!");
        }else{
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String,String> map = new LinkedHashMap<>();
            map.put("id",cid);
            map.put("feeMemo",s);
            ViseUtil.Get(context, NetUrl.AppConsultationInfoinsertFeedback, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if(jsonObject.optString("status").equals("200")){
                            ToastUtil.showShort(context,"反馈成功!");
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @OnClick({R.id.rl_back,R.id.btn_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_save:
                save();
                break;
        }
    }

}
