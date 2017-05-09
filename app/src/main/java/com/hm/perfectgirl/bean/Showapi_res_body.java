package com.hm.perfectgirl.bean;

import java.util.List;

/**
 * Created by 清扬 on 2017/5/7.
 */

public class Showapi_res_body {


    private List<Newslist> newslist ;

    private int code;

    private String msg;

    public void setNewslist(List<Newslist> newslist){
        this.newslist = newslist;
    }
    public List<Newslist> getNewslist(){
        return this.newslist;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }

    @Override
    public String toString() {
        return "Showapi_res_body{" +
                "newslist=" + newslist +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
