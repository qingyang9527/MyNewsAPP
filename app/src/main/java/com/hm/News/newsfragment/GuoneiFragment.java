package com.hm.News.newsfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hm.News.bean.TianNews;
import com.hm.News.bean.Title;
import com.hm.News.util.CommonUtil;
import com.hm.News.util.MyApplication;
import com.hm.News.util.Retrofit2Util;
import com.hm.News.util.ToastUtil;
import com.hm.perfectgirl.R;
import com.hm.News.adapter.NewsAdapter;
import com.hm.News.api.TianAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by 清扬 on 2017/5/23.
 */

public class GuoneiFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter mNewsAdapter;
    private List<TianNews.NewslistBean> newsListes=new ArrayList<>();
    private List<Title> titleList=new ArrayList<>();
    private String tianApi="https://api.tianapi.com/";
    public boolean isVisible;
    private int page=1;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            showData();
        }else {
            isVisible=false;
            onVisible();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_item_main, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
        //下拉刷新
        final SwipeRefreshLayout swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                titleList.clear();
                showData_random();
                ToastUtil.getInstance().shortShowToast(MyApplication.getContext(),"刷新成功");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        mNewsAdapter=new NewsAdapter(getActivity(),titleList);
        recyclerView.setAdapter(mNewsAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()){
            showData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onVisible() {
        showData();
    }

    public void showData(){
        TianAPI tianAPI= Retrofit2Util.getInstance().create(TianAPI.class);
        Observable<TianNews> observable=tianAPI.getGUONEINews();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TianNews>() {
                    @Override
                    public void accept(@NonNull TianNews tianNews) throws Exception {
                        newsListes=tianNews.getNewslist();
                        for (TianNews.NewslistBean n:newsListes
                                ) {
                            Title title=new Title(n.getTitle(),n.getDescription(),n.getPicUrl(),n.getUrl(),n.getCtime());
                            titleList.add(title);
                        }
                        mNewsAdapter.notifyDataSetChanged();//通知适配器数据已经改变
                    }
                });
    }

    /**
     * 下拉刷新随机获取数据
     */
    public void showData_random(){
        TianAPI tianAPI= Retrofit2Util.getInstance().create(TianAPI.class);
        Observable<TianNews> observable=tianAPI.getAllNews("guonei", CommonUtil.KEY,40,0,page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TianNews>() {
                    @Override
                    public void accept(@NonNull TianNews tianNews) throws Exception {
                        newsListes=tianNews.getNewslist();
                        for (TianNews.NewslistBean n:newsListes
                                ) {
                            Title title=new Title(n.getTitle(),n.getDescription(),n.getPicUrl(),n.getUrl(),n.getCtime());
                            titleList.add(title);
                        }
                        mNewsAdapter.notifyDataSetChanged();//通知适配器数据已经改变
                    }
                });
    }
}
