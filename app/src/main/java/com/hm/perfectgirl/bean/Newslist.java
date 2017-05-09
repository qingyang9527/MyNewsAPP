package com.hm.perfectgirl.bean;

/**
 * Created by 清扬 on 2017/5/8.
 */

public class Newslist {
    private String title;

    private String picUrl;

    private String description;

    private String ctime;

    @Override
    public String toString() {
        return "Newslist{" +
                "title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", description='" + description + '\'' +
                ", ctime='" + ctime + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
