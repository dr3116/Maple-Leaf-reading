package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.test4.book.Book;
import com.example.test4.book.BookIntroduction;
import com.example.test4.search.Search;
import com.example.test4.search.SearchAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddPostBook extends AppCompatActivity {
    private String str2;
    private ListView searchList;
    private EditText searchName;
    private ImageView search1;
    private Bitmap head;
    private String userId;
    private String inputStr;
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


    private void setListView() {
        SearchAdapter searchAdapter = new SearchAdapter(this,books, R.layout.search_list_item);
        searchList.setAdapter(searchAdapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(AddPostBook.this, AddPost.class);
                intent.putExtra("bookName",books.get(position).getBookName());
                intent.putExtra("author",books.get(position).getAuthor());
                intent.putExtra("bookPhoto",books.get(position).getBookPhoto());
                if (head!=null){
                    intent.putExtra("head",head);
                }
                if (userId!=null){
                    intent.putExtra("userId",userId);
                }
                if (inputStr!=null){
                    intent.putExtra("input",inputStr);
                }
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchList = findViewById(R.id.lv_search);
        searchName = findViewById(R.id.et_find);
        search1 = findViewById(R.id.iv_search);
        head=(Bitmap) getIntent().getParcelableExtra("head");
        userId=getIntent().getStringExtra("userId");
        inputStr=getIntent().getStringExtra("input");
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBooks();
            }
        });

    }
    //获取书的字符信息
    private void getBooks() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String name = searchName.getText()+"";
                    builder.add("search",name+"");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"GetSearchList")
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
}