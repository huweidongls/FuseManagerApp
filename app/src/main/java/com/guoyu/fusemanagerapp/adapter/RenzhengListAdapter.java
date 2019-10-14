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
import com.guoyu.fusemanagerapp.bean.APPFuncInfoApplyFoorListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/14.
 */

public class RenzhengListAdapter extends RecyclerView.Adapter<RenzhengListAdapter.ViewHolder> {

    private Context context;
    private List<APPFuncInfoApplyFoorListBean.DataBean> data;

    public RenzhengListAdapter(List<APPFuncInfoApplyFoorListBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_renzheng_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(data.get(position).getIsSelect() == 1){
            Glide.with(context).load(R.mipmap.address_on).into(holder.iv);
        }else {
            Glide.with(context).load(R.mipmap.address_off).into(holder.iv);
        }
        holder.tv.setText(data.get(position).getFunName());
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getIsSelect() == 0){
                    data.get(position).setIsSelect(1);
                    Glide.with(context).load(R.mipmap.address_on).into(holder.iv);
                }else {
                    data.get(position).setIsSelect(0);
                    Glide.with(context).load(R.mipmap.address_off).into(holder.iv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
