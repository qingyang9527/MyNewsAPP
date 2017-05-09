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
import com.hm.perfectgirl.bean.SportNewsRoot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 清扬 on 2017/5/6.
 */

public class SportNewsAdapter extends RecyclerView.Adapter<SportNewsAdapter.SportNewsViewHolder>{

    public List<SportNewsRoot> sportNewsRootList=new ArrayList<>();
    public Context context;

    public SportNewsAdapter(List<SportNewsRoot> sportNewsList, Context context) {
        this.sportNewsRootList = sportNewsList;
        this.context = context;
    }

    @Override
    public SportNewsAdapter.SportNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        SportNewsViewHolder holder=new SportNewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SportNewsAdapter.SportNewsViewHolder holder, int position) {
//            SportNewsRoot sportNews=sportNewsRootList.get(position);
////        holder.news_title.setText(sportNews.getShowapi_res_body().getNewslist());
////        holder.news_desc.setText();
////        Glide.with(context).load();
    }

    @Override
    public int getItemCount() {
        return sportNewsRootList.size();
    }


    public class SportNewsViewHolder extends RecyclerView.ViewHolder{

        private CardView mCardView;
        private ImageView news_phote;
        private TextView news_title;
        private TextView news_desc;
        private Button shape;
        private Button readMore;

        public SportNewsViewHolder(View itemView) {
            /**
             * 构造函数初始化控件
             */
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
