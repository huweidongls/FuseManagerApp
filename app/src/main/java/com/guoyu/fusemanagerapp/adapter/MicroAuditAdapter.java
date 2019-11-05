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

import com.bumptech.glide.Glide;
import com.guoyu.fusemanagerapp.R;
import com.guoyu.fusemanagerapp.bean.WeiguanListBean;
import com.guoyu.fusemanagerapp.imagepreview.Consts;
import com.guoyu.fusemanagerapp.imagepreview.ImagePreviewActivity;
import com.guoyu.fusemanagerapp.net.NetUrl;
import com.guoyu.fusemanagerapp.nine.NineGridTestLayout;
import com.guoyu.fusemanagerapp.page.AuditWeiguanActivity;
import com.guoyu.fusemanagerapp.page.ReplyWeiguanActivity;
import com.guoyu.fusemanagerapp.util.GlideUtils;
import com.guoyu.fusemanagerapp.util.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/10/15.
 */

public class MicroAuditAdapter extends RecyclerView.Adapter<MicroAuditAdapter.ViewHolder> {

    private Context context;
    private List<WeiguanListBean.DataBean> data;
    private int type;

    public MicroAuditAdapter(List<WeiguanListBean.DataBean> data, int type) {
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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final int status = data.get(position).getStatusid();
        if(type == 0){
            holder.llShenhe.setVisibility(View.VISIBLE);
            holder.llText.setVisibility(View.GONE);
            if(status == 1){
                holder.llShenhe.setVisibility(View.GONE);
                holder.tvStatus.setText("待审核");
                holder.tvStatus.setBackgroundResource(R.drawable.bg_ffbc1a_9dp);
            }else if(status == 2){
                holder.llShenhe.setVisibility(View.VISIBLE);
                holder.tvStatus.setText("已审核");
                holder.tvStatus.setBackgroundResource(R.drawable.bg_bbbbbb_9dp);
                holder.tvAuditName.setText(data.get(position).getApprUserName());
                holder.tvAuditTime.setText(data.get(position).getApprDate());
            }
        }else if(type == 1){
            holder.llShenhe.setVisibility(View.GONE);
            holder.llText.setVisibility(View.VISIBLE);
            if(status == 2){
                holder.llText.setVisibility(View.GONE);
                holder.tvStatus.setText("待反馈");
                holder.tvStatus.setBackgroundResource(R.drawable.bg_ffbc1a_9dp);
            }else if(status == 3){
                holder.llText.setVisibility(View.VISIBLE);
                holder.tvStatus.setText("已反馈");
                holder.tvStatus.setBackgroundResource(R.drawable.bg_bbbbbb_9dp);
                holder.tvFankui.setText("【反馈意见】"+data.get(position).getFeeMemo());
            }
        }
        if(data.get(position).getNikePic()!=null){
            String[] pics = data.get(position).getNikePic().split(",");
            if(pics.length>0){
                GlideUtils.into(context, NetUrl.BASE_URL+pics[0], holder.ivHead);
            }
        }
        holder.tvName.setText(data.get(position).getNickName());

        holder.tvAddTime.setText("创建时间："+data.get(position).getCreateDate());
        holder.tvContent.setText("【"+data.get(position).getTitle()+"】"+data.get(position).getContent());
        String[] s = data.get(position).getContentPic().split(",");
        final List<String> list = new ArrayList<>();
        for (String ss : s){
            list.add(NetUrl.BASE_URL+ss);
        }
        if(list.size()>0&&list.size()<2){
            holder.iv.setVisibility(View.VISIBLE);
            holder.nine.setVisibility(View.GONE);
            GlideUtils.into(context, list.get(0), holder.iv);
        }else if(list.size()>1){
            holder.iv.setVisibility(View.GONE);
            holder.nine.setVisibility(View.VISIBLE);
            holder.nine.setUrlList(list);
        }

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra("imageList", (Serializable) list);
                intent.putExtra(Consts.START_ITEM_POSITION, 0);
                intent.putExtra(Consts.START_IAMGE_POSITION, 0);
                context.startActivity(intent);
            }
        });

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(type == 0){
                    if(status == 1){
                        intent.setClass(context, AuditWeiguanActivity.class);
                        intent.putExtra("bean", data.get(position));
                        intent.putExtra("type", "0");
                        context.startActivity(intent);
                    }else {
                        intent.setClass(context, AuditWeiguanActivity.class);
                        intent.putExtra("bean", data.get(position));
                        intent.putExtra("type", "1");
                        context.startActivity(intent);
                    }
                }else {
                    if(status == 2){
                        intent.setClass(context, ReplyWeiguanActivity.class);
                        intent.putExtra("bean", data.get(position));
                        context.startActivity(intent);
                    }else {
                        ToastUtil.showShort(context, "已反馈");
                    }
                }
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
        private ImageView ivHead;
        private TextView tvName;
        private TextView tvStatus;
        private TextView tvAddTime;
        private TextView tvContent;
        private LinearLayout llText;
        private TextView tvFankui;
        private LinearLayout llShenhe;
        private TextView tvAuditName;
        private TextView tvAuditTime;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            nine = itemView.findViewById(R.id.nine);
            ll = itemView.findViewById(R.id.ll);
            ivHead = itemView.findViewById(R.id.iv_header);
            tvName = itemView.findViewById(R.id.tv_user);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvAddTime = itemView.findViewById(R.id.tv_addtime);
            tvContent = itemView.findViewById(R.id.tv_content);
            llText = itemView.findViewById(R.id.ll_text);
            tvFankui = itemView.findViewById(R.id.tv_fff);
            llShenhe = itemView.findViewById(R.id.ll_shenhe);
            tvAuditName = itemView.findViewById(R.id.tv_audit_name);
            tvAuditTime = itemView.findViewById(R.id.tv_audit_time);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
