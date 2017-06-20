package com.hm.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hm.perfectgirl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class UpdatePwdActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.old_password)
    EditText oldPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.register_next)
    Button registerNext;
    @BindView(R.id.register_return)
    Button registerReturn;

    private String oldP;
    private String newP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
        Bmob.initialize(this, "16bc982529a22273fb255aae713666ca");
        ButterKnife.bind(this);
        toolBar.setTitle("修改密码");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.whiteback);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.register_next, R.id.register_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_next:
                oldP=oldPassword.getText().toString();
                newP=newPassword.getText().toString();
                if (oldP.equals("")||newP.equals("")){
                    new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                            .setContentText("旧密码或新密码不能为空")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                }
                            }).show();
                }else {
                    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("修改密码")
                            .setContentText("确认提交")
                            .setConfirmText("是")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    BmobUser.updateCurrentUserPassword(oldP, newP, new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                Toast.makeText(UpdatePwdActivity.this, "密码修改成功,请重新登录", Toast.LENGTH_SHORT).show();
                                                BmobUser.logOut();
                                                Intent intent=new Intent(UpdatePwdActivity.this,LoginActivity.class);
                                            } else {
                                                Toast.makeText(UpdatePwdActivity.this, "密码修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    sweetAlertDialog.cancel();
                                }
                            }).setCancelText("否")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.cancel();
                                }
                            }).show();
                }
                break;
            case R.id.register_return:
                finish();
                break;
        }
    }
}
