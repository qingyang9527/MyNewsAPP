package com.hm.perfectgirl.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.perfectgirl.R;
import com.hm.perfectgirl.bean.News;

import java.util.List;

/**
 * Created by 清扬 on 2017/5/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHoler> {

    private List<News> newsList;
    private Context mContext;

    public NewsAdapter(List<News> newsList, Context mContext) {
        this.newsList = newsList;
        this.mContext = mContext;
    }

    @Override
    public NewsViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
        NewsViewHoler holer=new NewsViewHoler(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(NewsViewHoler holder, final int position) {

        final int j=position;
         holder.news_phote.setImageResource(newsList.get(position).getPhotoId());
        holder.news_title.setText(newsList.get(position).getTitle());
        holder.news_desc.setText(newsList.get(position).getDesc());

//        //CardView的点击监听
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent();
//                intent.putExtra("NEWS",newsList.get(j));
//                mContext.startActivity(intent);
//            }
//        });

        //分享按钮的监听
        holder.shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //阅读更多按钮的点击监听器
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHoler extends RecyclerView.ViewHolder{
        private CardView mCardView;
        private ImageView news_phote;
        private TextView news_title;
        private TextView news_desc;
        private Button shape;
        private Button readMore;

        public NewsViewHoler(View itemView) {
            //在构造函数中初始化控件
            super(itemView);
            mCardView=(CardView)itemView.findViewById(R.id.card_view);
            news_phote=(ImageView)itemView.findViewById(R.id.news_photo);
            news_title=(TextView)itemView.findViewById(R.id.news_title);
            news_desc=(TextView)itemView.findViewById(R.id.news_desc);
            shape=(Button) itemView.findViewById(R.id.btn_share);
            readMore=(Button)itemView.findViewById(R.id.btn_more);
        }
    }
}
