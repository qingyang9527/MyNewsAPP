package com.hm.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hm.perfectgirl.R;
import com.hm.News.bean.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity{
    private Toolbar mToolbar;
    private Button register_next;
    private EditText reg_email1;
    private EditText reg_password,reg_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Bmob.initialize(this,"16bc982529a22273fb255aae713666ca");
        initView();
        mToolbar.setTitle("用户注册");
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=reg_email1.getText().toString();
                String password=reg_password.getText().toString();
                String username=reg_username.getText().toString();
                Log.d("email",email);
                Log.d("email",password);
                MyUser user=new MyUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser user, BmobException e) {
                        if (e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Log.e("TAG",e.getMessage());
                            Toast.makeText(RegisterActivity.this, "注册失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                
            }
        });
    }

    /**
     * 初始化所有控件
     */
    public void initView(){
        mToolbar=(Toolbar)findViewById(R.id.tool_bar);
        register_next=(Button)findViewById(R.id.register_next);
        reg_email1=(EditText) findViewById(R.id.reg_email);
        reg_password=(EditText)findViewById(R.id.reg_password);
        reg_username=(EditText) findViewById(R.id.reg_username);
    }
}
