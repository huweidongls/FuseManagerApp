package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.base.BaseActivity;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplyWeiguanActivity extends BaseActivity {

    private Context context = ReplyWeiguanActivity.this;

    @BindView(R.id.nine)
    NineGridTestLayout nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_weiguan);

        ButterKnife.bind(ReplyWeiguanActivity.this);
        initData();

    }

    private void initData() {

        List<String> list = new ArrayList<>();
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        nine.setUrlList(list);

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
