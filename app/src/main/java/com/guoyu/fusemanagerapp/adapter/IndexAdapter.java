package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.HomeNewsBean;
import com.guoyu.fusemanagerapp.page.ConvenienceNoticeDetailsActivity;
import com.guoyu.fusemanagerapp.page.GobernmentContentActivity;
import com.guoyu.fusemanagerapp.page.ModuleWebViewActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/10/9.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<HomeNewsBean.DataBean> data;
    private int type;

    public IndexAdapter(List<HomeNewsBean.DataBean> data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_time.setText(data.get(position).getCreateDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(type == 0){
                    intent.setClass(context, ConvenienceNoticeDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    intent.putExtra("title", "便民通知");
                    context.startActivity(intent);
                }else if(type == 1){
                    intent.setClass(context, ModuleWebViewActivity.class);
                    intent.putExtra("url", "1");
                    context.startActivity(intent);
                }else if(type == 2){
                    intent.setClass(context, ModuleWebViewActivity.class);
                    intent.putExtra("url", "1");
                    context.startActivity(intent);
                }else if(type == 3){
                    intent.setClass(context, GobernmentContentActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private TextView tv_time;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

}
