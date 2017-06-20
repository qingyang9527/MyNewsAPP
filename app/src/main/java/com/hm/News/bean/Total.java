package com.hm.News.bean;

/**
 * Created by 清扬 on 2017/5/29.
 */

public class Total{

    private String username;//用户
    private String comment;//评论
    private String title;//标题
    private int hearder_image;//头像
    private String content;//帖子内容
    private String comment_num;//评论数
    private String likes;//喜欢该帖子的人数

    public Total(String username, String comment, String title, int hearder_image, String content, String comment_num, String likes) {
        this.username = username;
        this.comment = comment;
        this.title = title;
        this.hearder_image = hearder_image;
        this.content = content;
        this.comment_num = comment_num;
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHearder_image() {
        return hearder_image;
    }

    public void setHearder_image(int hearder_image) {
        this.hearder_image = hearder_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
