package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/10/15.
 */

public class MicroAuditAdapter extends RecyclerView.Adapter<MicroAuditAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public MicroAuditAdapter(List<String> data) {
        this.data = data;
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
