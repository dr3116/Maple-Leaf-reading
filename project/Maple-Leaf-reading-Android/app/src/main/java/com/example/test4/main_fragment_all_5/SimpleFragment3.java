package com.example.test4.main_fragment_all_5;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.test4.Comment;
import com.example.test4.ConfigUtil;
import com.example.test4.Post;
import com.example.test4.PostAdapter;
import com.example.test4.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;;import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SimpleFragment3 extends Fragment{
    private String str2;
    private int img;
    private Button btn11;
    private String userId;
    private ListView postListView;
    private List<Post> posts=new ArrayList<Post>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();

            }
        }
    };


    public SimpleFragment3() {


    }

    //作用，参数存入str2，方便后面调用
    public static SimpleFragment3 newInstance(String str1) {
        SimpleFragment3 simpleFragment3 = new SimpleFragment3();
        simpleFragment3.str2 = str1;
        return simpleFragment3;
    }

    @Override//这个方法比上个方法后执行
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        if (getArguments()!=null){
            userId=getArguments().getString("userId");
        }
        View view = inflater.inflate(R.layout.activity_discovery, container, false);
        postListView=view.findViewById(R.id.postListView);
        getpost();
        return view;
    }
    private void setListView(){
        PostAdapter postAdapter=new PostAdapter(getContext(),posts,R.layout.post_item);
        postListView.setAdapter(postAdapter);
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(getContext(), Comment.class);
                intent.putExtra("post",posts.get(position));
                startActivity(intent);
            }
        });
    }
    //获取post的字符信息
    private void getpost() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR+"GetCommentList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    posts = gson.fromJson(responseData, new TypeToken<List<Post>>(){}.getType());
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