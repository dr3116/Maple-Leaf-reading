package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test4.book.BookIntroduction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AddPingLunActivity extends AppCompatActivity {
    private EditText pinglun;
    private Button addPinglun;
    private String bookName;
    private List<BookReview> bookReviews = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
//                Log.e("设置listView","成功");
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinglun);
        bookName = getIntent().getStringExtra("bookName");
        Log.e("dr3116",bookName);
        getView();
        setOnClick();
    }

    private void setOnClick() {
        addPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPingLuns();
                String bName = getIntent().getStringExtra("bookName");
                String rVolume = getIntent().getStringExtra("readingVolume");
                String Chapters = getIntent().getStringExtra("numberOfChapters");
                String bRating = getIntent().getStringExtra("bookRating");
                String bAuthor = getIntent().getStringExtra("author");
                String Collections = getIntent().getStringExtra("numberOfCollections");
                String bIntroduction = getIntent().getStringExtra("briefIntroduction");
                String bPhoto = getIntent().getStringExtra("bookPhoto")+"";
                Intent intent = new Intent(AddPingLunActivity.this, BookIntroduction.class);
                intent.putExtra("bookName",bName);
                intent.putExtra("readingVolume",rVolume);
                intent.putExtra("numberOfChapters",Chapters);
                intent.putExtra("bookRating",bRating);
                intent.putExtra("author",bAuthor);
                intent.putExtra("numberOfCollections",Collections);
                intent.putExtra("briefIntroduction",bIntroduction);
                intent.putExtra("bookPhoto",bPhoto);
                startActivity(intent);
            }
        });
    }

    /**
     * 添加书评
     */
    private void addPingLuns() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String a = bookName;
                    String b = pinglun.getText()+"";
                    String c = Login.userId+"";
                    Log.e("addname:",a+"");
                    Log.e("pinglun:",b+"");
                    Log.e("userid:",c+"");
                    builder.add("AddBookName", a);
                    builder.add("AddPingLun", b);
                    builder.add("uuId", c);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/SetBookReviewList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    bookReviews = gson.fromJson(responseData, new TypeToken<List<BookReview>>(){}.getType());
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
    private void getView() {
        pinglun = findViewById(R.id.et_pinglun);
        addPinglun = findViewById(R.id.bt_addPinglun);
    }
}
