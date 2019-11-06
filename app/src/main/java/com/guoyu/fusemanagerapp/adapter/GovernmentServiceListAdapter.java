package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.GovernmentServiceListBean;
import com.guoyu.fusemanagerapp.page.GobernmentContentActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/10/15.
 */

public class GovernmentServiceListAdapter extends RecyclerView.Adapter<GovernmentServiceListAdapter.ViewHolder> {

    private Context context;
    private List<GovernmentServiceListBean.DataBean> data;

    public GovernmentServiceListAdapter(List<GovernmentServiceListBean.DataBean> data) {
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
    public void onBindViewHolder(GovernmentServiceListAdapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_tag.setText("【" + data.get(position).getGovTypeName() + "】");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, GobernmentContentActivity.class);
                intent.putExtra("id", data.get(position).getId() + "");
                intent.putExtra("title", "政务信息");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_tag;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_tag = itemView.findViewById(R.id.tv_tag);
        }
    }
}
