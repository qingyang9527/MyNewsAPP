package com.hm.News.util;

import com.google.gson.Gson;

/**
 * Created by 清扬 on 2017/5/9.
 */

public class CommonUtil {

    public final static String KEY="f8af33c2ac61b58f99e591d903f56602";

    /**
     * 泛型解析json数据成实体类
     * @param responseText
     * @param c
     * @param <T>
     * @return
     */
    public static<T> T parseJsonWithGson(final String responseText,Class<T> c) {
        try {
            Gson gson=new Gson();
            T t=gson.fromJson(responseText,c);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
