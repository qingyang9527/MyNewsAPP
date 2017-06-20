package com.hm.News.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hm.News.adapter.FragmentAdapter;
import com.hm.perfectgirl.R;
import com.hm.News.viewpager.DepthPageTransformer;

import java.util.Arrays;

/**
 * Created by 清扬 on 2017/5/21.
 */

public class TestFragment1 extends Fragment {
    private View viewContent;
    private TabLayout tab_essence;
    private ViewPager vp_essence;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewContent = inflater.inflate(R.layout.tab_viewpager_main,container,false);
        initConentView(viewContent);
        initData();

        return viewContent;
    }

    public void initConentView(View viewContent) {
        this.tab_essence = (TabLayout) viewContent.findViewById(R.id.tab_layout);
        this.vp_essence = (ViewPager) viewContent.findViewById(R.id.main_viewPager);
    }

    public void initData() {
        //获取标签数据
        String[] titles = getResources().getStringArray(R.array.home_video_tab);

        //创建一个viewpager的adapter
        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager(), Arrays.asList(titles));
        this.vp_essence.setAdapter(adapter);
        this.vp_essence.setPageTransformer(true,new DepthPageTransformer());

        //将TabLayout和ViewPager关联起来
        this.tab_essence.setupWithViewPager(this.vp_essence);
    }
}
