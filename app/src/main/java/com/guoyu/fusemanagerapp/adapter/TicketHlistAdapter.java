package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.TicketServiceListBean;
import com.guoyu.fusemanagerapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class TicketHlistAdapter extends RecyclerView.Adapter<TicketHlistAdapter.ViewHolder>{
    private Context context;
    private List<TicketServiceListBean.DataBean> data;

    public TicketHlistAdapter(List<TicketServiceListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public TicketHlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_h_ticket, parent, false);
        TicketHlistAdapter.ViewHolder holder = new TicketHlistAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketHlistAdapter.ViewHolder holder, int position) {
        String b = data.get(position).getMainPic();
        b=b.substring(0, b.lastIndexOf(","));
        Glide.with(context).load(NetUrl.BASE_URL+b).into(holder.iv_imgs);
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_price.setText("¥"+data.get(position).getTicketMoney()+"元起");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_imgs;
        private TextView tv_title;
        private TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_imgs = itemView.findViewById(R.id.iv_imgs);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
