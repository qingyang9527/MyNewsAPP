package com.hm.perfectgirl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.hm.perfectgirl.adapter.NewsAdapter;
import com.hm.perfectgirl.adapter.SportNewsAdapter;
import com.hm.perfectgirl.bean.News;
import com.hm.perfectgirl.bean.SportNewsRoot;
import com.hm.perfectgirl.util.OkhttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<News> newsList;
    private NewsAdapter mNewsAdapter;
    private List<SportNewsRoot> mSportNewsRootList;
    private SportNewsAdapter mSportNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item_main);


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
//       initDatas();
//        mNewsAdapter=new NewsAdapter(newsList,this);
        initDatasFromAPI();
        //mSportNewsAdapter=new SportNewsAdapter(SnewsList,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mNewsAdapter);

    }

    public void initDatas(){
        newsList=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            newsList.add(new News("title"+i,"desc"+i,R.mipmap.ic_launcher));
        }
    }

    public void initDatasFromAPI(){
        String url="http://route.showapi.com/196-1?showapi_appid=37481&showapi_sign=b8b88e8f324f4b179b01a2fe98a2ba6e&num=10&rand=1&";
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("JSON failure",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                final SportNewsRoot sportNewsRoot = new Gson().fromJson(res, SportNewsRoot.class);
            }
        });
    }

    public void test(){
        String url="www.baidu.com";
        OkhttpUtil.getInstance().sendRequestWithOkhttpSync(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res=response.body().string();
                Log.e("BAIDU",res);
//                List<News> NewsListes=new ArrayList<News>();
//                NewsListes= (List<News>) OkhttpUtil.getInstance().parseResponseWithGson(res,new ArrayList<News>());
//                for (News n:NewsListes
//                     ) {
//                    Log.d("TAG",n.getTitle());
//                    Log.d("TAG",n.getDesc());
//                }
            }
        });
    }


}
