package com.hm.News.bean;

/**
 * Created by 清扬 on 2017/5/9.
 */

public class Title {
    private String title;
    private String descr;
    private String imageUrl;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    private String uri;
    private String ctime;

    public Title(String title, String descr, String imageUrl, String uri, String ctime) {
        this.title = title;
        this.descr = descr;
        this.imageUrl = imageUrl;
        this.uri = uri;
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescr() {
        return descr;
    }

    @Override
    public String toString() {
        return "Title{" +
                "title='" + title + '\'' +
                ", descr='" + descr + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }

    public String getUri() {
        return uri;
    }
}
