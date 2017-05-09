package com.hm.perfectgirl.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hm.perfectgirl.bean.Showapi_res_body;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 清扬 on 2017/5/6.
 */

public class OkhttpUtil {

    private static OkhttpUtil okhttpUtil=null;
    private OkhttpUtil(){
    }

    public static synchronized OkhttpUtil getInstance(){
        if(okhttpUtil==null){
            okhttpUtil=new OkhttpUtil();
        }
        return okhttpUtil;
    }

    /**
     * 将返回的json(体育新闻)数据解析成实体类
     */

        public Showapi_res_body handleSportNewsResponse(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            //JSONArray jsonArray=jsonObject.getJSONArray("showapi_res_body");
            String SportNewsContent=jsonObject.toString();
            return new Gson().fromJson(SportNewsContent,Showapi_res_body.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * okhttp异步请求封装
     * @param url
     * @param callback
     */
    public void sendRequestWithOkhttpSync(String url, Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }


    public List<?> parseResponseWithGson(String jsonData, List<?> e){
        Gson gson=new Gson();
        List<?> list=gson.fromJson(jsonData,new TypeToken<List<?>>(){}.getType());
        return list;
    }

    public <T> T parse(String jsonData,Class<T> e){
        T t=new Gson().fromJson(jsonData,e);
        return t;
    }




}
