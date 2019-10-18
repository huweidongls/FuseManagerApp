package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.AcademicResourcesTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class DisciplineInformationAdapter extends RecyclerView.Adapter<DisciplineInformationAdapter.ViewHolder>{
    private Context context;
    private List<AcademicResourcesTypeBean.DataBean> data;

    public DisciplineInformationAdapter(List<AcademicResourcesTypeBean.DataBean> data) {
        this.data = data;
    }
    @Override
    public DisciplineInformationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_disciplinformation, parent, false);
        DisciplineInformationAdapter.ViewHolder holder = new DisciplineInformationAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DisciplineInformationAdapter.ViewHolder holder, int position) {
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
