package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.RealAuditBean;
import com.guoyu.fusemanagerapp.page.RealNameActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class RealAdapter extends RecyclerView.Adapter<RealAdapter.ViewHolder> {

    private Context context;
    private List<RealAuditBean.DataBean> data;

    public RealAdapter(List<RealAuditBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_real, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getRealName());
        holder.tvCard.setText(data.get(position).getAppuserId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, RealNameActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCard = itemView.findViewById(R.id.tv_card);
        }
    }

}
