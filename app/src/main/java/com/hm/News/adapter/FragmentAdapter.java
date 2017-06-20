package com.hm.News.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hm.News.newsfragment.AppleFragment;
import com.hm.News.newsfragment.WorldFragment;
import com.hm.News.newsfragment.FootballFragment;
import com.hm.News.newsfragment.GuoneiFragment;
import com.hm.News.newsfragment.ItFragment;
import com.hm.News.newsfragment.KejiFragment;
import com.hm.News.newsfragment.MilitaryFragment;
import com.hm.News.newsfragment.MobileFragment;
import com.hm.News.newsfragment.SocialFragment;
import com.hm.News.newsfragment.StartupFragment;

import java.util.List;

/**
 * Created by 清扬 on 2017/5/14.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{

    public static final String TAB_TAG = "@dream@";

    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<String> mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SocialFragment();
            case 1:
                return new GuoneiFragment();
            case 2:
                return new WorldFragment();
            case 3:
                return new FootballFragment();
            case 4:
                return new KejiFragment();
            case 5:
                return new StartupFragment();
            case 6:
                return new MilitaryFragment();
            case 7:
                return new MobileFragment();
            case 8:
                return new ItFragment();
            case 9:
                return new AppleFragment();
            default:
                return new SocialFragment();
        }

    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).split(TAB_TAG)[0];
    }
}
