package com.hm.perfectgirl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar loginToolbar;
    private Button mLogin;
    private EditText mUsername,mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Bmob.initialize(this,"16bc982529a22273fb255aae713666ca");
        initView();
        loginToolbar.setTitle("用户登录");
        loginToolbar.setNavigationIcon(R.drawable.back);

        mLogin.setOnClickListener(this);


    }

    public void initView(){
        loginToolbar=(Toolbar) findViewById(R.id.login_toolbar);
        mLogin=(Button) findViewById(R.id.login_bt);
        mUsername=(EditText) findViewById(R.id.username);
        mPassword=(EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.login_bt:
                String email=mUsername.getText().toString();
                String password=mPassword.getText().toString();
                forEmailLogin(email,password);
                break;
            default:
        }
    }

    public void forEmailLogin(String email,String password){
        BmobUser.loginByAccount(email, password, new LogInListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "登录失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *  免登陆
     */

    public void noLogin(){
        BmobUser b=BmobUser.getCurrentUser();
        if (b!=null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else{

        }
    }
}
