package com.hm.News.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hm.News.bean.Title;
import com.hm.News.NewsDetailsActivity;
import com.hm.perfectgirl.R;

import java.util.List;

/**
 * Created by 清扬 on 2017/5/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHoler> {

    private Context mContext;
    private List<Title> titleList;

    public NewsAdapter(Context mContext, List<Title> titleList) {
        this.mContext = mContext;
        this.titleList = titleList;
    }

    @Override
    public NewsViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
        NewsViewHoler holer=new NewsViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(NewsViewHoler holder, final int position) {
        holder.news_title.setText(titleList.get(position).getTitle());
        holder.news_ctime.setText(titleList.get(position).getCtime());
        holder.news_desc.setText(titleList.get(position).getDescr());
        Glide.with(mContext).load(titleList.get(position).getImageUrl()).placeholder(R.mipmap.pla).into(holder.news_phote);

        //CardView的点击监听
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,NewsDetailsActivity.class);
                intent.putExtra("title",titleList.get(position).getTitle());
                intent.putExtra("NewsUrl",titleList.get(position).getUri());
                intent.putExtra("imagerUrl",titleList.get(position).getImageUrl());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    static class NewsViewHoler extends RecyclerView.ViewHolder{
        private CardView mCardView;
        private ImageView news_phote;
        private TextView news_title;
        private TextView news_desc;
        private TextView news_ctime;

        public NewsViewHoler(View itemView) {
            //在构造函数中初始化控件
            super(itemView);
            mCardView=(CardView)itemView.findViewById(R.id.card_view);
            news_phote=(ImageView)itemView.findViewById(R.id.news_photo);
            news_title=(TextView)itemView.findViewById(R.id.news_title);
            news_ctime=(TextView)itemView.findViewById(R.id.news_ctime);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);
        }
    }
}
