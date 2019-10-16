package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;
import com.guoyu.fusemanagerapp.page.AuditWeiguanActivity;
import com.guoyu.fusemanagerapp.page.ReplyWeiguanActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/10/15.
 */

public class MicroAuditAdapter extends RecyclerView.Adapter<MicroAuditAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private int type;

    public MicroAuditAdapter(List<String> data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_microaudit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<String> list = new ArrayList<>();
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        list.add("http://b-ssl.duitang.com/uploads/item/201410/20/20141020224133_Ur54c.jpeg");
        holder.nine.setUrlList(list);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(type == 0){
                    intent.setClass(context, AuditWeiguanActivity.class);
                }else {
                    intent.setClass(context, ReplyWeiguanActivity.class);
                }
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private NineGridTestLayout nine;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            nine = itemView.findViewById(R.id.nine);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
