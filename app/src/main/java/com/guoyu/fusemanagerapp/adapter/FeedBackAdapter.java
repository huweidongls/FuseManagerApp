package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.FeedbackListBean;
import com.guoyu.fusemanagerapp.page.FeedbackDetailsActivity;
import com.guoyu.fusemanagerapp.util.ToastUtil;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackAdapter.ViewHolder> {

    private Context context;
    private List<FeedbackListBean.DataBean> data;

    public FeedBackAdapter(List<FeedbackListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_feedback, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvTitle.setText(data.get(position).getTitle());
        final int status = data.get(position).getStatusid();
        if(status == 1){
            Glide.with(context).load(R.mipmap.weifankui).into(holder.ivType);
        }else if(status == 3){
            Glide.with(context).load(R.mipmap.yifankui).into(holder.ivType);
            holder.tvFeed.setText(data.get(position).getFeeMemo());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 1){
                    Intent intent = new Intent();
                    intent.setClass(context, FeedbackDetailsActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }else {
                    ToastUtil.showShort(context, "已反馈");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivType;
        private TextView tvTitle;
        private TextView tvFeed;

        public ViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_type);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvFeed = itemView.findViewById(R.id.tv_feed);
        }
    }

}
