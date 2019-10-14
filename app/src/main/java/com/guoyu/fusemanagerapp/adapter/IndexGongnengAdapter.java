package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/10/9.
 */

public class IndexGongnengAdapter extends RecyclerView.Adapter<IndexGongnengAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private int max;

    public IndexGongnengAdapter(List<String> data) {
        this.data = data;
        max = data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_index_gongneng, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == max){
            holder.ll.setVisibility(View.GONE);
            holder.llMore.setVisibility(View.VISIBLE);
        }else {
            holder.ll.setVisibility(View.VISIBLE);
            holder.llMore.setVisibility(View.GONE);
        }
//        holder.ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, ModuleWebViewActivity.class);
//                context.startActivity(intent);
//            }
//        });
//        holder.llMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, MoreActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size()+1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;
        private LinearLayout ll;
        private LinearLayout llMore;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
            llMore = itemView.findViewById(R.id.ll_more);
        }
    }

}
