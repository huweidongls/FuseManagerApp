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
    private ClickListener listener;
    public GovernmentServiceTypeAdapter(List<GovernmentServiceTypeBean.DataBean> data,ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }
    @Override
    public GovernmentServiceTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_government_type_list, parent, false);
        GovernmentServiceTypeAdapter.ViewHolder holder = new GovernmentServiceTypeAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GovernmentServiceTypeAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(data.get(position).getSubName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickType(position);
            }
        });
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
    public interface ClickListener{
        void onClickType(int pos);
    }
}
