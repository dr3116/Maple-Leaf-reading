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
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.test4.AddPost;
import com.example.test4.Comment;
import com.example.test4.ConfigUtil;
import com.example.test4.Post;
import com.example.test4.PostAdapter;
import com.example.test4.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;;import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SimpleFragment3 extends Fragment{
    private String str2;
    private int img;
    private SmartRefreshLayout srl;
    private Button btn11;
    private String userId;
    private ListView postListView;
    private ImageView addPostImg;
    private PostAdapter postAdapter;
    private List<Integer> ImgInAdress=new ArrayList<>();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        addPostImg=view.findViewById(R.id.addPost_img);
        getpost();
        addPostImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(getContext(), AddPost.class);
                startActivity(intent);
            }
        });
        srl = view.findViewById(R.id.smartLayout);
        //设置Header和Footer的样式
        //创建Header样式对象
        WaveSwipeHeader header = new WaveSwipeHeader(getContext());
        srl.setRefreshHeader(header);
        //创建Footer样式
        FalsifyFooter footer = new FalsifyFooter(getContext());
        //设置Footer样式
        srl.setRefreshFooter(footer);
        //设置回弹时间
        srl.setReboundDuration(2000);

        //给智能刷新控件注册下拉刷新事件监听器
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                posts.clear();
                getpost();

                //通知刷新完毕
                srl.finishRefresh();
            }
        });
        //给智能刷新控件注册上拉加载更多事件监听器
        srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多数据(假设超过10条数据则加载完毕）
//                if(stus.size() < 10) {
//                    loadMoreData();
//                    //通知加载数据完毕
//                    srl.finishLoadMore();
//                } else {
//                    //通知没有更多数据可加载
//                    srl.finishLoadMoreWithNoMoreData();
//                }
            }
        });
        return view;
    }
    private void setListView(){
        PutImgAdress();
        postAdapter=new PostAdapter(getContext(),posts,R.layout.post_item,ImgInAdress);
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
    private void PutImgAdress(){
        Log.e("posts数量",""+posts.size());
        int imgCount=posts.size();
        for (int i=0;i<imgCount;i++){
            ImgInAdress.add(R.drawable.likes);
        }
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