package com.hm.perfectgirl.bean;

/**
 * Created by 清扬 on 2017/5/3.
 */

public class News{
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    private String desc;
    private int photoId;

    public News(String title, String desc, int photoId) {
        this.title = title;
        this.desc = desc;
        this.photoId = photoId;
    }
}
