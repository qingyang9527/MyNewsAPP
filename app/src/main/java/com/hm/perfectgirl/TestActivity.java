package com.hm.perfectgirl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hm.perfectgirl.util.OkhttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        button= (Button) findViewById(R.id.button);
    }

    public void test(View view){
        String url="http://route.showapi.com/191-1?showapi_appid=37481&showapi_sign=b8b88e8f324f4b179b01a2fe98a2ba6e&num=10&rand=1&";
        String JUHEurl="http://op.juhe.cn/onebox/basketball/nba&dtype=&=&key=bbd56b70233e557ae8843574ec46878f";
        OkhttpUtil.getInstance().sendRequestWithOkhttpSync(JUHEurl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("faliure",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res=response.body().string();
                Log.d("BAIDU",res);
                //Gson gson=new Gson();
                //SportNewsRoot sportNewsRoot=gson.fromJson(res,SportNewsRoot.class);
              //  Showapi_res_body Showapi_res_body=OkhttpUtil.getInstance().handleSportNewsResponse(res);
                //List<Newslist> list=new ArrayList<Newslist>();
               // list=gson.fromJson(res,new TypeToken<List<Newslist>>(){}.getType());
                //System.out.println("长度为："+sportNewsRoot.getShowapi_res_body().getNewslist().size());
//                for (int i = 0; i <sportNewsRoot.getShowapi_res_body().getNewslist().size(); i++) {
//
//                }
                

//                Log.d("SPORT",sportNewsRoot.getShowapi_res_error());
//                Log.d("SPORT",sportNewsRoot.getShowapi_res_body().getNewsList().getTitle());
//                Log.d("SPORT",sportNewsRoot.getShowapi_res_body().getNewsList().getPicUrl());

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
