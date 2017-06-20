package com.hm.News.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by 清扬 on 2017/5/8.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
