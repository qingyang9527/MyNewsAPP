package com.hm.News.api;

import com.hm.News.bean.TianNews;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 清扬 on 2017/5/14.
 */

public interface TianAPI {



    @GET("/{types}/")
    Observable<TianNews> getAllNews(@Path("types")String types, @Query("key")String key, @Query("num")int num, @Query("rand")int rand, @Query("page")int page);

    @GET("/{types}/?key=f8af33c2ac61b58f99e591d903f56602")
    Observable<TianNews> getAllNews_test(@Path("types")String types, @QueryMap Map<String,Integer> map);
    final static String SOCIAL="https://api.tianapi.com/social/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String SOCIAL_random="https://api.tianapi.com/social/?key=f8af33c2ac61b58f99e591d903f56602&num=20&rand=1";
    final static String GUONEI="https://api.tianapi.com/guonei/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String WORLD="https://api.tianapi.com/world/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String TIYU="https://api.tianapi.com/tiyu/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String FOOTBALL="https://api.tianapi.com/football/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String FOOTBALL_random="https://api.tianapi.com/football/?key=f8af33c2ac61b58f99e591d903f56602&num=40&rand=1";
    final static String KEJI="https://api.tianapi.com/keji/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String MOBILE="https://api.tianapi.com/mobile/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String STARTUP="https://api.tianapi.com/startup/?key=f8af33c2ac61b58f99e591d903f56602&num=20";//创业
    final static String MILITARY="https://api.tianapi.com/military/?key=f8af33c2ac61b58f99e591d903f56602&num=20";//军事
    final static String IT="https://api.tianapi.com/it/?key=f8af33c2ac61b58f99e591d903f56602&num=20";
    final static String apple="https://api.tianapi.com/apple/?key=f8af33c2ac61b58f99e591d903f56602&num=20";

    /**
     * 社会新闻
     * @return
     */
    @GET(SOCIAL)
    Observable<TianNews> getSOCIALNews();

    /**
     * 国内新闻
     * @return
     */
    @GET(GUONEI)
    Observable<TianNews> getGUONEINews();

    /**
     * 世界新闻
     * @return
     */
    @GET(WORLD)
    Observable<TianNews> getWORLDNews();

    /**
     * 体育新闻
     * @return
     */
    @GET(TIYU)
    Observable<TianNews> getTIYUNews();

    /**
     * 足球新闻
     * @return
     */
    @GET(FOOTBALL)
    Observable<TianNews> getFOOTBALLNews();

    /**
     * 随机足球新闻
     * @return
     */
    @GET(FOOTBALL_random)
    Observable<TianNews> getFOOTBALLNews_random();

    /**
     * 科技新闻
     * @return
     */
    @GET(KEJI)
    Observable<TianNews> getKEJINews();

    /**
     * 移动互联新闻
     * @return
     */
    @GET(MOBILE)
    Observable<TianNews> getMOBILENews();

    /**
     * 创业新闻
     * @return
     */
    @GET(STARTUP)
    Observable<TianNews> getSTARTUPNews();

    /**
     * 军事新闻
     * @return
     */
    @GET(MILITARY)
    Observable<TianNews> getMILITARYNews();

    /**
     * IT新闻
     * @return
     */
    @GET(IT)
    Observable<TianNews> getITNews();

    /**
     * IT新闻
     * @return
     */
    @GET(apple)
    Observable<TianNews> getAppleNews();


}
