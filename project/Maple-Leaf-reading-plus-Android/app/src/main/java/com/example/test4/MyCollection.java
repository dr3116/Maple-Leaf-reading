package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.test4.main_fragment_all_5.SimpleFragment3;
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

public class MyCollection extends AppCompatActivity {
    private String userId;
    private ListView postListView;
    private ImageView addPostImg;
    private PostAdapter postAdapter;
    private ImageView back;
    private ImageView backHome;
    private List<Integer> ImgAttention=new ArrayList<>();
    private List<Integer> ImgInAdress=new ArrayList<>();
    private List<Post> posts=new ArrayList<Post>();
    private List<Post> postTemp=new ArrayList<>();
    private List<Collection> collections=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
//                Log.e("设置listView","成功");
                setListView();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        userId=getIntent().getStringExtra("userId");
        postListView=findViewById(R.id.my_cllection_list);
        back=findViewById(R.id.back);
        backHome=findViewById(R.id.back_home);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCollection.this, MainActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("mark",5);
                startActivity(intent);
            }
        });
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCollection.this, MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        getAllPost();

    }
    private void getPsotTemp(){
        for (Collection item: collections){
            for (Post item2:posts){
                if (item2.getPostId()==item.getPostId()){
                    postTemp.add(item2);
                }
            }
        }
    }
    private void setListView(){
        getPsotTemp();
        putImg();
//        Log.e("postTemp数量",postTemp.size()+"");
        postAdapter=new PostAdapter(this,postTemp,R.layout.post_item,ImgInAdress,ImgAttention,userId);
        postListView.setAdapter(postAdapter);
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(MyCollection.this, Comment.class);
                intent.putExtra("post",postTemp.get(position));
                startActivity(intent);
            }
        });
    }
    private void putImg(){
//        Log.e("posts数量",""+posts.size());
        int imgCount=posts.size();
        for (int i=0;i<imgCount;i++){
            ImgInAdress.add(R.drawable.likes);
            ImgAttention.add(R.drawable.circles);
        }
    }
    //获取post的字符信息
    private void getpost() {
        new Thread() {
//            @Override
//            public void run() {
//                try {
////                    OkHttpClient client = new OkHttpClient();
////                    FormBody.Builder builder = new FormBody.Builder();
////                    builder.add("userId",userId);
////                    FormBody body = builder.build();
////                    Request request = new Request.Builder()
////                            // 指定访问的服务器地址
////                            .post(body)
////                            .url(ConfigUtil.SERVER_ADDR+"APP/GetCollection")
////                            .build();
////                    Response response = client.newCall(request).execute();
////                    //获得json字符串
////
////                    String responseData = response.body().string();
//////                    Log.e("从服务端返回的json:",responseData);
////                    //解析字符串
////                    Gson gson = new GsonBuilder()
////                            .create(); //生成配置好的Gson
////                    collections = gson.fromJson(responseData, new TypeToken<List<Collection>>(){}.getType());
////                    Message msg = new Message();
////                    //设置Message对象的参数
////                    msg.what = 1;
////
////                    //发送Message
////                    handler.sendMessage(msg);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }.start();
    }
    //获取post的字符信息
    private void getAllPost() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetCommentList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    posts = gson.fromJson(responseData, new TypeToken<List<Post>>(){}.getType());
                    //获取我收藏的post
                    getpost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}