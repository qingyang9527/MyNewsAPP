package com.hm.News;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hm.perfectgirl.R;
import com.hm.News.adapter.ContactAdapter;
import com.hm.News.bean.Total;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.talk_recylerview)
    RecyclerView talkRecylerview;
    @BindView(R.id.talk_swipeRefresh)
    SwipeRefreshLayout talkSwipeRefresh;
    @BindView(R.id.talk_floatActionBtn)
    FloatingActionButton talkFloatActionBtn;
    private List<Total> totalList = new ArrayList<>();
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk_recyler_layout);
        ButterKnife.bind(this);
        initTest_Data();
        talkRecylerview.setLayoutManager(new LinearLayoutManager(TestActivity.this));
        contactAdapter=new ContactAdapter(TestActivity.this,totalList);
        talkRecylerview.setAdapter(contactAdapter);
    }

    public void initTest_Data() {
        for (int i = 0; i < 10; i++) {
            totalList.add(new Total("吴彦祖" + i, "this is my first comment" + i, "title" + i,R.drawable.header, "wo shi wu yan zu" + i, "30", "40"));
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
}
