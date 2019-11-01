package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.MenuBean;
import com.guoyu.fusemanagerapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/10/9.
 */

public class IndexGongnengAdapter extends RecyclerView.Adapter<IndexGongnengAdapter.ViewHolder> {

    private Context context;
    private List<MenuBean.DataBean> data;

    public IndexGongnengAdapter(List<MenuBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index_gongneng, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getLogoPic()).into(holder.iv);
        holder.tv.setText(data.get(position).getFunName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(data.get(position).getUrl() == null){
                    try {
                        intent.setClass(context, Class.forName(data.get(position).getAndroidUrl()));
                        context.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        intent.setClass(context, Class.forName(data.get(position).getAndroidUrl()));
                        intent.putExtra("url", data.get(position).getUrl());
                        context.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
