package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.AcademicResourcesListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class DisciplineInfoAdapter extends RecyclerView.Adapter<DisciplineInfoAdapter.ViewHolder> {
    private Context context;
    private List<AcademicResourcesListBean.DataBean> data;

    public DisciplineInfoAdapter(List<AcademicResourcesListBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public DisciplineInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_discipline_list, parent, false);
        DisciplineInfoAdapter.ViewHolder holder = new DisciplineInfoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DisciplineInfoAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(data.get(position).getTitle());
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
