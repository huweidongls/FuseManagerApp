package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/10/19.
 */

public class FeedbackDetailsImgAdapter extends RecyclerView.Adapter<FeedbackDetailsImgAdapter.ViewHolder>{
    private Context context;
    private List<String> data;
    public FeedbackDetailsImgAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public FeedbackDetailsImgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_feedback_details_img, parent, false);
        FeedbackDetailsImgAdapter.ViewHolder holder = new FeedbackDetailsImgAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FeedbackDetailsImgAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position)).into(holder.iv_pic);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }
}
