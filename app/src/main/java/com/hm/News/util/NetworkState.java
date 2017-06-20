package com.hm.News.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 清扬 on 2017/5/7.
 */

public class NetworkState {


    /**
     * 检查是否连接到网络
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context){
        if (context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }


    /**
     * 检查WiFi是否连接
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context){
        if (context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType()==ConnectivityManager.TYPE_WIFI){
                    return info.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 检查移动网络是否连接
     * @param context
     * @return
     */
    public static boolean isMobileDataConnected(Context context){
        if (context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null){
                if (info.getType()==ConnectivityManager.TYPE_MOBILE){
                    return info.isAvailable();
                }
            }
        }
        return false;
    }
}
