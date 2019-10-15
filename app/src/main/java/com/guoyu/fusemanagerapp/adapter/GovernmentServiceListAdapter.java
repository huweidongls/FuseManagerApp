package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guoyu.fusemanagerapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/10/15.
 */

public class GovernmentServiceListAdapter extends RecyclerView.Adapter<GovernmentServiceListAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public GovernmentServiceListAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public GovernmentServiceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_government_list, parent, false);
        GovernmentServiceListAdapter.ViewHolder holder = new GovernmentServiceListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GovernmentServiceListAdapter.ViewHolder holder, int position) {

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
