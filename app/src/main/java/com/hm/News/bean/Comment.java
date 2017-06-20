package com.hm.News.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by 清扬 on 2017/5/23.
 */

public class Comment extends BmobObject {
    private String content;//评论内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private MyUser author;//评论的用户，Pointer类型，一对一关系

    private Post post; //所评论的帖子，这里体现的是一对多的关系，一个评论只能属于一个微博
}
