package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/17.
 */

public class GovernmentServiceTypeAdapter extends RecyclerView.Adapter<GovernmentServiceTypeAdapter.ViewHolder>{
    private Context context;
    private List<GovernmentServiceTypeBean.DataBean> data;

    public GovernmentServiceTypeAdapter(List<GovernmentServiceTypeBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public GovernmentServiceTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_government_type_list, parent, false);
        GovernmentServiceTypeAdapter.ViewHolder holder = new GovernmentServiceTypeAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GovernmentServiceTypeAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getSubName());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
