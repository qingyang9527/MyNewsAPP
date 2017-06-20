package com.hm.News;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hm.News.bean.MyUser;
import com.hm.perfectgirl.R;
import com.hm.News.bean.Comment;
import com.hm.News.bean.Post;
import com.hm.News.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class PostActivity extends AppCompatActivity {

    private final String TAG = "PostActivity";
    @BindView(R.id.btn_addData)
    Button btnAddData;
    @BindView(R.id.btn_showData)
    Button btnShowData;
    @BindView(R.id.tv_showData)
    TextView tvShowData;

    String postId = "";
    String commentId = "";
    @BindView(R.id.btn_showCommont)
    Button btnShowCommont;
    @BindView(R.id.btn_createComment)
    Button btnCreateComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        Bmob.initialize(this, "16bc982529a22273fb255aae713666ca");
    }

    /**
     * 查询一对一关联 -> 查询当前用户发表的所有帖子
     */
    private void queryOneToOne() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Post> query = new BmobQuery<>();
        query.addWhereEqualTo("author", user);
        query.order("-updatedAt");
        // 查询完post表后想把发布者的信息查询出来 要用内部查询
        query.include("author");
        query.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                    ToastUtil.getInstance().shortShowToast(PostActivity.this, "ok " + list.get(0).getContent());
                    tvShowData.setText(list.get(0).getContent());
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    /**
     * 添加一对多关联 -> 创建评论并关联评论和帖子
     */
    private void addOneToMore() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        Post post = new Post();
        post.setObjectId(postId);
        Comment comment = new Comment();
        comment.setContent("不能同意更多");
        comment.setPost(post);
        comment.setAuthor(user);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastUtil.getInstance().shortShowToast(PostActivity.this, "ok");
                    commentId = s;
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    /**
     * 查询某个帖子下的所有评论
     */
    private void queryOneToMore() {
        BmobQuery<Comment> query = new BmobQuery<>();
        Post post = new Post();
        post.setObjectId(postId);
        // TODO 不友好
        query.addWhereEqualTo("post", new BmobPointer(post));
        // 希望查询到评论发布者的具体信息 用下内部查询
        // 这里稍复杂些 查询评论发布者的信息 和 帖子作者的信息 参见include的并列对象查询和内嵌对象的查询
        query.include("user,post.author");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null) {
                    ToastUtil.getInstance().shortShowToast(PostActivity.this, "ok" + list.get(0).getContent());
                    tvShowData.setText(list.get(0).getContent());
                } else {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }


    @OnClick({R.id.btn_addData, R.id.btn_showData,R.id.btn_showCommont, R.id.btn_createComment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_addData:
                MyUser user = BmobUser.getCurrentUser(MyUser.class);
                Post post = new Post();
                post.setContent("this is my fist post");
                post.setAuthor(user);
                post.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            ToastUtil.getInstance().shortShowToast(PostActivity.this, "ok");
                            postId = s;
                        } else {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                });
                break;
            case R.id.btn_showData:
                queryOneToOne();
                break;
            case R.id.btn_showCommont:
                queryOneToMore();
                break;
            case R.id.btn_createComment:
                addOneToMore();
                break;
        }
    }

}
