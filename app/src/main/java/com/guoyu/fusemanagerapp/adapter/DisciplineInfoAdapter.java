package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guoyu.fusemanagerapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class DisciplineInfoAdapter extends RecyclerView.Adapter<DisciplineInfoAdapter.ViewHolder> {
    private Context context;
    private List<String> data;

    public DisciplineInfoAdapter(List<String> data) {
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

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
