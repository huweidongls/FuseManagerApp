package com.guoyu.fusemanagerapp.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guoyu.fusemanagerapp.R;

import butterknife.ButterKnife;

public class TicketInserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_inser);
        ButterKnife.bind(TicketInserActivity.this);
        initData();
    }
    private void initData(){

    }
}
