package com.hm.News.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hm.News.adapter.ContactAdapter;
import com.hm.News.bean.Total;
import com.hm.perfectgirl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 清扬 on 2017/5/14.
 */

public class TalkFragment extends Fragment {
    @BindView(R.id.talk_recylerview)
    RecyclerView talkRecylerview;
    @BindView(R.id.talk_swipeRefresh)
    SwipeRefreshLayout talkSwipeRefresh;
    @BindView(R.id.talk_floatActionBtn)
    FloatingActionButton talkFloatActionBtn;
    Unbinder unbinder;
    private ContactAdapter mContactAdapter;
    private List<Total> totalList=new ArrayList<>();
    public boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.talk_recyler_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        talkRecylerview.setLayoutManager(linearLayoutManager);
        mContactAdapter=new ContactAdapter(getActivity(),totalList);
        talkRecylerview.setAdapter(mContactAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            isVisible=true;
            initTest_Data();
        }else {
            isVisible=false;
            onVisible();
        }
    }
    public void onVisible() {
        initTest_Data();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()){
            initTest_Data();
        }
    }


    @OnClick({R.id.talk_swipeRefresh, R.id.talk_floatActionBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.talk_swipeRefresh:
                break;
            case R.id.talk_floatActionBtn:
                break;
        }
    }

    public void initTest_Data() {
        for (int i = 0; i < 10; i++) {
            totalList.add(new Total("测试名字" + i, "this is my first comment" + i, "title" + i, R.drawable.header, "this is " + i, "30", "40"));
        }
        mContactAdapter.notifyDataSetChanged();
    }
}
