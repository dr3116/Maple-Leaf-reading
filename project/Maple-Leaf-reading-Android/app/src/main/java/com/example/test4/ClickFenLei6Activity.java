package com.example.test4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test4.book.Book;
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

/**
 * 杜然 12/1
 */
public class ClickFenLei6Activity extends AppCompatActivity {
    private String str2;
    private String userId;
    private ListView classification;
    private List<Book> books = new ArrayList<Book>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_fen_lei);
        userId=getIntent().getStringExtra(userId);

        classification = findViewById(R.id.s_clickfenlei_listview);
        getBooks();
    }
    public ClickFenLei6Activity(){

    }
    //作用，参数存入str2，方便后面调用
    public static ClickFenLei6Activity newInstance(String str1) {
        ClickFenLei6Activity clickFenLei6Activity = new ClickFenLei6Activity();
        clickFenLei6Activity.str2= str1;
        return clickFenLei6Activity;
    }

    //获取书的字符信息
    private void getBooks() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("classification","JavaScript");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"GetBookList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    books = gson.fromJson(responseData, new TypeToken<List<Book>>(){}.getType());
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

    private void setListView() {
        ClickFenLeiAdapter clickFenLeiAdapter = new ClickFenLeiAdapter(this,books, R.layout.fenlei_list_item);
        classification.setAdapter(clickFenLeiAdapter);

        classification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(ClickFenLei6Activity.this, BookIntroduction.class);
                intent.putExtra("bookName",books.get(position).getBookName());
                intent.putExtra("readingVolume",books.get(position).getReadingVolume()+"");
                intent.putExtra("numberOfChapters",books.get(position).getNumberOfChapters()+"");
                intent.putExtra("bookRating",books.get(position).getBookRating()+"");
                intent.putExtra("author",books.get(position).getAuthor());
                intent.putExtra("numberOfCollections",books.get(position).getNumberOfCollections()+"");
                intent.putExtra("briefIntroduction",books.get(position).getBriefIntroduction());
                intent.putExtra("bookPhoto",books.get(position).getBookPhoto());

                startActivity(intent);
            }
        });

    }
}