package com.hm.News.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hm.News.util.MyApplication;
import com.hm.perfectgirl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 清扬 on 2017/5/14.
 */

public class MainFragment extends Fragment {
    // 图片资源
    private int[] mImages = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
    private List<ImageView> mList;
    Handler mHandler = new Handler();
    private LinearLayout pointGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.banner_layout,container,false);
        final ViewPager viewPager= (ViewPager) view.findViewById(R.id.banner_viewpager);
        pointGroup= (LinearLayout) view.findViewById(R.id.pointgroup);
        showImage();
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            int lastPosition;
            @Override
            public void onPageSelected(int position) {
                // 页面被选中

                // 修改position
                position = position % mList.size();

                // 设置当前页面选中
                pointGroup.getChildAt(position).setSelected(true);
                // 设置前一页不选中
                pointGroup.getChildAt(lastPosition).setSelected(false);

                // 替换位置
                lastPosition = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();

                if(currentPosition == viewPager.getAdapter().getCount() - 1){
                    // 最后一页
                    viewPager.setCurrentItem(0);
                }else{
                    viewPager.setCurrentItem(currentPosition + 1);
                }

                // 一直给自己发消息
                mHandler.postDelayed(this,3000);
            }
        },3000);
        return view;
    }

    public void showImage(){
        // 准备显示的图片集合
        mList = new ArrayList<>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(MyApplication.getContext());
            // 将图片设置到ImageView控件上
            imageView.setImageResource(mImages[i]);

            // 将ImageView控件添加到集合
            mList.add(imageView);

            // 制作底部小圆点
            ImageView pointImage = new ImageView(MyApplication.getContext());
            pointImage.setImageResource(R.drawable.shape_point_selector);

            // 设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);

            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelSize(R.dimen.point_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            // 添加到容器里
            pointGroup.addView(pointImage);
        }
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // 返回整数的最大值
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            // 修改position
            position = position % mList.size();
            // 将图片控件添加到容器
            container.addView(mList.get(position));

            // 返回
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
