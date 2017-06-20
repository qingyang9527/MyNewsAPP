package com.hm.News.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hm.News.bean.Total;
import com.hm.perfectgirl.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 清扬 on 2017/5/29.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHoler> {

    private Context mContext;
    private List<Total> list;

    public ContactAdapter(Context mContext, List<Total> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public ContactViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.talk_layout, parent, false);
        ContactViewHoler holder = new ContactViewHoler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactViewHoler holder, int position) {
        holder.name.setText(list.get(position).getUsername());
        holder.title.setText(list.get(position).getTitle());
        holder.detail.setText(list.get(position).getContent());
        holder.tvLike.setText(list.get(position).getLikes());
        holder.tvComment.setText(list.get(position).getComment_num());
        Glide.with(mContext).load(list.get(position).getHearder_image()).placeholder(R.drawable.lock).into(holder.imageViewHead);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ContactViewHoler extends RecyclerView.ViewHolder{
        @BindView(R.id.imageViewHead)
        ImageView imageViewHead;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.detail)
        TextView detail;
        @BindView(R.id.tableLayout)
        TableLayout tableLayout;
        @BindView(R.id.tv_like)
        TextView tvLike;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        public ContactViewHoler(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
