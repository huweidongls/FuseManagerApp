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

public class TickerSlistAdapter extends RecyclerView.Adapter<TickerSlistAdapter.ViewHolder>{
    private Context context;
    private List<TicketServiceListBean.DataBean> data;

    public TickerSlistAdapter(List<TicketServiceListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public TickerSlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_s_ticket, parent, false);
        TickerSlistAdapter.ViewHolder holder = new TickerSlistAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TickerSlistAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getMainPic()).into(holder.iv_img);
        holder.tv_title.setText(data.get(position).getTitle());
        String str=data.get(position).getContent();
        //str=str.substring(0,15);
        holder.tv_desc.setText(str+"...");
        holder.tv_price.setText("¥"+data.get(position).getTicketMoney()+"元");
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_desc;
        private TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_title= itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
