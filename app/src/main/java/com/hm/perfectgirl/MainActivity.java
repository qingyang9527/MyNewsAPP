package com.hm.perfectgirl;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon("");
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.other);
        }

//        Bmob.initialize(this,"16bc982529a22273fb255aae713666ca");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    //    /**
//     * 增加单个数据
//     * @param view
//     */
//    public void save(View view){
//        String username=mUsername.getText().toString();
//        String password=mPassword.getText().toString();
//
//        user=new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e==null){
//                    Toast.makeText(MainActivity.this, "注册成功,返回String"+s, Toast.LENGTH_SHORT).show();
//                }else
//                {
//                    Toast.makeText(MainActivity.this, "注册失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    /**
//     * 批量增加数据
//     * @param view
//     */
//    public void userInsertBatch(View view){
//        List<BmobObject> allUser=new ArrayList<BmobObject>();
//        for (int i = 0; i < 10; i++) {
//            user=new User();
//            user.setUsername("张三丰"+i);
//            allUser.add(user);
//        }
//        new BmobBatch().insertBatch(allUser).doBatch(new QueryListListener<BatchResult>() {
//            @Override
//            public void done(List<BatchResult> list, BmobException e) {
//                if(e==null){
//                    for (int i = 0; i < list.size(); i++) {
//                        BatchResult result=list.get(i);
//                        BmobException ex=result.getError();
//                        if (ex==null){
//                            Log.e("BMOb insert success", "第"+i+"个数据批量添加成功:"+result.getCreatedAt()+","+result.getObjectId()+","+result.getUpdatedAt());
//                        }else
//                        {
//                            Log.e("BMOb insert faliure","第"+i+"个数据批量添加成功:"+ex.getMessage()+","+ex.getErrorCode());
//                        }
//                    }
//                }
//            }
//        });
//    }
//
//    /**
//     * 批量更新用户
//     * @param view
//     */
//    public void userUpdateBatch(View view){
//        List<BmobObject> persons = new ArrayList<BmobObject>();
//        user=new User();
//        user.setObjectId("6ca70465c1");
//        user.setUsername("张卫健");
//        persons.add(user);
//        User p1 = new User();
//        p1.setObjectId("95c8b6f854");
//        p1.setUsername("张学友");
//        User p2 = new User();
//        p2.setObjectId("3982ec9e63");
//        p2.setPassword("123");
//
//
//        persons.add(p1);
//        persons.add(p2);
//
//
////第二种方式：v3.5.0开始提供
//        new BmobBatch().updateBatch(persons).doBatch(new QueryListListener<BatchResult>() {
//
//            @Override
//            public void done(List<BatchResult> o, BmobException e) {
//                if(e==null){
//                    for(int i=0;i<o.size();i++){
//                        BatchResult result = o.get(i);
//                        BmobException ex =result.getError();
//                        if(ex==null){
//                            Log.d("","第"+i+"个数据批量更新成功："+result.getUpdatedAt());
//                        }else{
//                            Log.e("","第"+i+"个数据批量更新失败："+ex.getMessage()+","+ex.getErrorCode());
//                        }
//                    }
//                }else{
//                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
//                }
//            }
//        });
//    }
//
//    public void userQuery(View view){
//        BmobQuery<User> query=new BmobQuery<>();
//        query.getObject("3982ec9e63", new QueryListener<User>() {
//            @Override
//            public void done(User user, BmobException e) {
//                if (e==null){
//                    final String username=user.getUsername()+user.getPassword();
//                    showData.setText(username);
//                }
//            }
//        });
//
//    }
}
