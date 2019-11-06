package com.guoyu.fusemanagerapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceTypeBean;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.util.HtmlFromUtils;
import com.guoyu.fusemanagerapp.util.StringUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;
import com.guoyu.fusemanagerapp.util.ViseUtil;
import com.guoyu.fusemanagerapp.util.WeiboDialogUtils;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GovernmentServiceInsertActivity extends AppCompatActivity {

    private GovernmentServiceInsertActivity context = GovernmentServiceInsertActivity.this;

    @BindView(R.id.spinner1)
    Spinner spinnertext;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private ArrayAdapter<String> adapters;
    private List<String> list = new ArrayList<String>();
    private List<GovernmentServiceTypeBean.DataBean> mList;
    private int typeId = 0;
    private Dialog dialog;
    private String content = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_service_insert);
        ButterKnife.bind(GovernmentServiceInsertActivity.this);
        initData();
        spinnertext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeId = mList.get(position).getId();
                //ToastUtil.showShort(GovernmentServiceInsertActivity.this,"你点击的是:"+typeId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initData() {
        ViseUtil.Get(GovernmentServiceInsertActivity.this, NetUrl.AppGovernmentInfofindType, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GovernmentServiceTypeBean bean = gson.fromJson(s, GovernmentServiceTypeBean.class);
                mList = bean.getData();
                for (GovernmentServiceTypeBean.DataBean bean2 : bean.getData()) {
                    list.add(bean2.getSubName());
                }
                adapters = new ArrayAdapter<String>(GovernmentServiceInsertActivity.this, android.R.layout.simple_spinner_item, list);
                //第三步：设置下拉列表下拉时的菜单样式
                adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //第四步：将适配器添加到下拉列表上
                spinnertext.setAdapter(adapters);
            }
        });
    }

    @OnClick({R.id.tv_add, R.id.iv_black, R.id.btn_cancel, R.id.btn_add})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_add:
                intent.setClass(context, FuwenbenActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.iv_black:
                finish();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_add:
                SaveAdd();
                break;
        }
    }

    private void SaveAdd() {
        String s = et_title.getText().toString();
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(content) || typeId == 0) {
            ToastUtil.showShort(GovernmentServiceInsertActivity.this, "请将信息补充完整!");
        } else {
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("title", s);
            map.put("content", content);
            map.put("govType", typeId + "");
            ViseUtil.Post(context, NetUrl.AppGovernmentInfotoUpdate, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.optString("status").equals("200")) {
                            WeiboDialogUtils.closeDialog(dialog);
                            ToastUtil.showShort(context, "发布成功!");
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001 && data != null){
            content = data.getStringExtra("content");
//            HtmlFromUtils.setTextFromHtml(GovernmentServiceInsertActivity.this, tvContent, content);
            RichText.from(content).bind(this)
                    .showBorder(false)
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                    .into(tvContent);
        }
    }

}
