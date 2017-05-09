package com.hm.perfectgirl.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 清扬 on 2017/5/8.
 */

public class ToastUtil {

    private static ToastUtil instace;

    private ToastUtil() {

    }

    public synchronized static ToastUtil getInstance(){
        if (instace==null){
            instace=new ToastUtil();
        }
        return instace;
    }

    /**
     * 短时间显示toast
     * @param context
     * @param msg
     */
    public void shortShowToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示toast
     * @param context
     * @param msg
     */
    public void LongShowToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
