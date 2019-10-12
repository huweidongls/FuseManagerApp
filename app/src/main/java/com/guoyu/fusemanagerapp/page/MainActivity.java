package com.guoyu.fusemanagerapp.page;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guoyu.fusemanagerapp.R;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
