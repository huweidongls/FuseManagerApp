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

public class TickerSlistAdapter extends RecyclerView.Adapter<TickerSlistAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public TickerSlistAdapter(List<String> data) {
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
