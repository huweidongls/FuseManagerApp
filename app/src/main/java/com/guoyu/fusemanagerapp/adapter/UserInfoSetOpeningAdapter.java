package com.guoyu.fusemanagerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guoyu.fusemanagerapp.R;

import java.util.List;

/**
 * Created by Administrator on 2019/10/17.
 */

public class UserInfoSetOpeningAdapter extends RecyclerView.Adapter<UserInfoSetOpeningAdapter.ViewHolder>{
    private Context context;
    private List<String> data;

    public UserInfoSetOpeningAdapter(List<String> data) {
        this.data = data;
    }
    @Override
    public UserInfoSetOpeningAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_userinfo_set_open_service, parent, false);
        UserInfoSetOpeningAdapter.ViewHolder holder = new UserInfoSetOpeningAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserInfoSetOpeningAdapter.ViewHolder holder, int position) {
        holder.tv_tvname.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tvname;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_tvname = itemView.findViewById(R.id.tv_tvname);
        }
    }
}
