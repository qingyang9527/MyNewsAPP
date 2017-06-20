package com.hm.News;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.News.adapter.FragmentAdapter;
import com.hm.News.bean.MyUser;
import com.hm.News.fragment.MainFragment;
import com.hm.News.fragment.TalkFragment;
import com.hm.News.fragment.TestFragment1;
import com.hm.News.util.ToastUtil;
import com.hm.perfectgirl.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_View) NavigationView mNavigationView;

    private FragmentAdapter mFragmentAdapter;

    private List<TabItem> mTableItemList;

    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;

    private static final String IMAGE_FILE_NAME = "header.jpg";

    private ImageView headerImg;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"16bc982529a22273fb255aae713666ca");
        ButterKnife.bind(this);
        initTabData();
        initTabHost();
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.other);
        }

        NavigationViewClickEvent();


    }

    /**
     * NavigationView点击事件
     */
    public void NavigationViewClickEvent(){
        View headerView=mNavigationView.getHeaderView(0);
        headerImg= (ImageView)headerView.findViewById(R.id.imageView);
        TextView username= (TextView) headerView.findViewById(R.id.nav_username);
        username.setText(BmobUser.getCurrentUser().getUsername());
        TextView email= (TextView) headerView.findViewById(R.id.nav_email);
        email.setText(BmobUser.getCurrentUser().getEmail());
        headerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("头像上传")
                        .setItems(new String[]{"本地上传", "相机上传"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        Toast.makeText(MainActivity.this, "本地上传", Toast.LENGTH_SHORT).show();
                                        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                                        galleryIntent.setType("image/*");//图片
                                        startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
                                        break;
                                    case 1:
                                        Toast.makeText(MainActivity.this, "拍照上传", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }).create()
                .show();

            }
        });



        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                switch (id){
                    case R.id.updatePwd:
                        Intent intent=new Intent(MainActivity.this,UpdatePwdActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        BmobUser.logOut();
                        BmobUser currentUser=BmobUser.getCurrentUser();
                        if (currentUser==null){
                            Intent intent1=new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent1);
                        }else{
                        }

                }
                return false;
            }
        });
    }

    /**
     * 头部菜单的显示
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.bar_menu,menu);
        return true;
    }

    /**
     * 滑动菜单的显示
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }



    //初始化Tab数据
    private void initTabData() {
        mTableItemList = new ArrayList<>();
        //添加tab
        mTableItemList.add(new TabItem(R.drawable.homepage,R.drawable.homepage_fill,R.string.home, MainFragment.class));
        mTableItemList.add(new TabItem(R.drawable.news,R.drawable.news_fill,R.string.news, TestFragment1.class));
        mTableItemList.add(new TabItem(R.drawable.people,R.drawable.people_fill,R.string.chat, TalkFragment.class));

    }



    //初始化主页选项卡视图
    private void initTabHost() {
        //实例化FragmentTabHost对象
        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(MainActivity.this,getSupportFragmentManager(),android.R.id.tabcontent);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i<mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitleString()).setIndicator(tabItem.getView());
            fragmentTabHost.addTab(tabSpec,tabItem.getFragmentClass(),null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.white));

            //默认选中第一个tab
            if(i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i< mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitleString())) {
                        tabitem.setChecked(true);
                    }else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    Uri originalUri=data.getData();//获取图片uri
                    resizeImage(originalUri);
                    //以下方法将获取的uri转为String类型哦。
                    String []imgs1={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                    Cursor cursor=this.managedQuery(originalUri, imgs1, null, null, null);
                    int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String img_url=cursor.getString(index);
                    upload(img_url);
                    //showToast(img_url);
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());
                        String []imgs={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                        Cursor cursor1=this.managedQuery(getImageUri(), imgs, null, null, null);
                        int index1=cursor1.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor1.moveToFirst();
                        String img_url1=cursor1.getString(index1);
                        upload(img_url1);
                        //showToast(img_url1);



                    } else {
                        Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;

                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean isSdcardExisting() {//推断SD卡是否存在
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public void resizeImage(Uri uri) {//重塑图片大小
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//能够裁剪
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    private void showResizeImage(Intent data) {//显示图片
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            headerImg.setImageDrawable(drawable);
        }
    }

    private Uri getImageUri() {//获取路径
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }

    private void upload(String imgpath){
        final BmobFile headerImg=new BmobFile(new File(imgpath));
        headerImg.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    MyUser myUser=new MyUser();
                    myUser.setPicture(headerImg);
                    myUser.save(new SaveListener() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (e==null){
                                ToastUtil.getInstance().shortShowToast(MainActivity.this,"上传成功");
                            }
                        }

                        @Override
                        public void done(Object o, Object o2) {

                        }
                    });
                    ToastUtil.getInstance().shortShowToast(MainActivity.this,"上传成功");
                }else {
                    ToastUtil.getInstance().shortShowToast(MainActivity.this,"上传失败"+e.getMessage());
                }
            }
        });
    }

    class TabItem {
        //正常情况下显示的图片
        private int imageNormal;
        //选中情况下显示的图片
        private int imagePress;
        //tab的名字
        private int title;
        private String titleString;

        //tab对应的fragment
        public Class<? extends Fragment> fragmentClass;

        public View view;
        public ImageView imageView;
        public TextView textView;

        public TabItem(int imageNormal, int imagePress, int title,Class<? extends Fragment> fragmentClass) {
            this.imageNormal = imageNormal;
            this.imagePress = imagePress;
            this.title = title;
            this.fragmentClass =fragmentClass;
        }

        public Class<? extends Fragment> getFragmentClass() {
            return fragmentClass;
        }
        public int getImageNormal() {
            return imageNormal;
        }

        public int getImagePress() {
            return imagePress;
        }

        public int getTitle() {
            return  title;
        }

        public String getTitleString() {
            if (title == 0) {
                return "";
            }
            if(TextUtils.isEmpty(titleString)) {
                titleString = getString(title);
            }
            return titleString;
        }

        public View getView() {
            if(this.view == null) {
                this.view = getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
                this.imageView = (ImageView) this.view.findViewById(R.id.tab_iv_image);
                this.textView = (TextView) this.view.findViewById(R.id.tab_tv_text);
                if(this.title == 0) {
                    this.textView.setVisibility(View.GONE);
                } else {
                    this.textView.setVisibility(View.VISIBLE);
                    this.textView.setText(getTitleString());
                }
                this.imageView.setImageResource(imageNormal);
            }
            return this.view;
        }

        //切换tab的方法
        public void setChecked(boolean isChecked) {
            if(imageView != null) {
                if(isChecked) {
                    imageView.setImageResource(imagePress);
                }else {
                    imageView.setImageResource(imageNormal);
                }
            }
            if(textView != null && title != 0) {
                if(isChecked) {
                    textView.setTextColor(getResources().getColor(R.color.black));
                } else {
                    textView.setTextColor(getResources().getColor(R.color.black));
                }
            }
        }
    }

}
