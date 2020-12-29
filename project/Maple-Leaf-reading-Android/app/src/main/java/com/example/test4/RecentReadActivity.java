package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test4.book.BookIntroduction;
import com.example.test4.search.Search;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecentReadActivity extends AppCompatActivity {
    private String userId;
    private String str2;
    private ImageView back;
    private ImageView home;
    private ListView recentRead;
    private List<RecentRead> recentReads = new ArrayList<RecentRead>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();
            }
        }
    };
    public RecentReadActivity(){

    }
    private void setListView() {
        RecentReadAdapter recentReadAdapter = new RecentReadAdapter(this,recentReads, R.layout.recent_read_item);
        recentRead.setAdapter(recentReadAdapter);
        recentRead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(RecentReadActivity.this, BookIntroduction.class);
                intent.putExtra("bookName",recentReads.get(position).getBookName());
                intent.putExtra("readingVolume",recentReads.get(position).getReadingVolume()+"");
                intent.putExtra("numberOfChapters",recentReads.get(position).getNumberOfChapters()+"");
                intent.putExtra("bookRating",recentReads.get(position).getBookRating()+"");
                intent.putExtra("author",recentReads.get(position).getAuthor());
                intent.putExtra("numberOfCollections",recentReads.get(position).getNumberOfCollections()+"");
                intent.putExtra("briefIntroduction",recentReads.get(position).getBriefIntroduction());
                intent.putExtra("bookPhoto",recentReads.get(position).getBookPhoto());
                startActivity(intent);
            }
        });

    }
    public static RecentReadActivity newInstance(String str1) {
       RecentReadActivity recentReadActivity = new RecentReadActivity();
       recentReadActivity.str2 = str1;
       return recentReadActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_read);
        recentRead = findViewById(R.id.lv_recent_read);
        getRecentReads();
        back=findViewById(R.id.back);
        home=findViewById(R.id.back_home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecentReadActivity.this, MainActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("mark",5);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecentReadActivity.this, MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }

    private void getRecentReads() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    userId = Login.userId;
                    builder.add("userId",userId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"GetRecentReadList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的RecentRead的Json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    recentReads = gson.fromJson(responseData, new TypeToken<List<RecentRead>>(){}.getType());
                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 1;

                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
