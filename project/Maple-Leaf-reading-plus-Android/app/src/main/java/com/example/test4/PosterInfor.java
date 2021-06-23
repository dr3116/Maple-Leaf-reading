package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.pietyhr.yunxindemo.LoginActivity;
import com.pietyhr.yunxindemo.MainActivity2;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PosterInfor extends AppCompatActivity {
    private String posterId;
    private String userId;
    private String posterName;
    private Button sendMessage;
    private CircleImageView posterImg;
    private TextView posterNameText;
    private ImageView posterSex;
    private ImageView addAttention;
    private TextView posterIdText;
    private TextView styleText;
    private LinearLayout recentRead;
    private ListView posterPostList;
    private PostAdapter postAdapter;
    private String userInfoStr;
    private List<Integer> ImgInAdress=new ArrayList<>();
    private List<Post> posts=new ArrayList<Post>();
    private List<Integer> ImgAttention=new ArrayList<>();
    private List<RecentRead> recentReads = new ArrayList<RecentRead>();
    private List<Collection> collections=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
//                Log.e("设置listView","成功");
                setListView();

            }
            if (msg.what==2){
                setView();
            }
            if(msg.what==3){
                String info[]=userInfoStr.split("&&");
                if(info[0].equals("男")){
                    posterSex.setImageResource(R.drawable.man);
                }else {
                    posterSex.setImageResource(R.drawable.woman);
                }
                styleText.setText(info[1]);
            }
            else if (msg.what==4){
                Toast.makeText(PosterInfor.this,"你已经关注过了",Toast.LENGTH_LONG).show();
            }else if (msg.what==5){
                Toast.makeText(PosterInfor.this,"关注成功",Toast.LENGTH_LONG).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_infor);
        userId=getIntent().getStringExtra("userId");
        posterId=getIntent().getStringExtra("posterId");
        posterName=getIntent().getStringExtra("posterName");
        getUserInfo();
        getView();
        getRecentReads();
        getpost();
        LoginInfo info = new LoginInfo("123", "123456"); // config...
        RequestCallback<LoginInfo> callback =  new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                NimUIKitImpl.setAccount(param.getAccount());
            }
            @Override
            public void onFailed(int code) { }
            @Override
            public void onException(Throwable exception) {}
            // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
        };
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);

        //登录
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NimUIKit.startP2PSession(PosterInfor.this,"private");
//                Intent intent=new Intent();
//                intent.setClass(PosterInfor.this, MainActivity2.class);
//                Log.e("开始跳转到LoginActivity","开始");
//                startActivity(intent);
            }
        });
        addAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttention();
            }
        });
    }

    private void  setView(){
        Log.e("设置控件","开始"+recentReads.size());
        for(int i=0;i<recentReads.size();i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout child0=new LinearLayout(this);
            child0.setOrientation(LinearLayout.VERTICAL);
            child0.setLayoutParams(layoutParams);

            ImageView grandChild=new ImageView(this);
            LinearLayout.LayoutParams grandChildPa=new LinearLayout.LayoutParams(150,180);
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.mipmap.loding)
                    .error(R.mipmap.error)
                    .fallback(R.mipmap.loding);

            Glide.with(this)
                    .load(ConfigUtil.SERVER_ADDR+"images/book/"+recentReads.get(i).getBookPhoto())
                    .apply(requestOptions)
                    .into(grandChild);
            grandChild.setLayoutParams(grandChildPa);
            grandChild.setLayoutParams(grandChildPa);


            TextView grandChild2=new TextView(this);
            LinearLayout.LayoutParams grandChild2Pa=new LinearLayout.LayoutParams(200,100);
            grandChild2.setText(recentReads.get(i).getBookName());
            grandChild2.setTextSize(10);
            grandChild2.setGravity(Gravity.CENTER);
            grandChild2.setTextColor(getResources().getColor(R.color.black));
            grandChild2.setLayoutParams(grandChild2Pa);

            Log.e("添加子控件","开始");
            child0.addView(grandChild);
            child0.addView(grandChild2);
            recentRead.addView(child0);
        }

    }

    private void getView(){
        posterImg=findViewById(R.id.poster_infor_img);
        posterNameText=findViewById(R.id.poster_info_name);
        posterSex=findViewById(R.id.poster_info_sex);
        addAttention=findViewById(R.id.poster_info_add_attantion);
        posterIdText=findViewById(R.id.poster_info_id);
        styleText=findViewById(R.id.poster_info_style_text);
        recentRead=findViewById(R.id.poster_info_recentRead);
        posterPostList=findViewById(R.id.poster_info_post_list);
        sendMessage=findViewById(R.id.poster_info_send_messinage);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);

        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"images/student/"+posterId+".jpg")
                .apply(requestOptions)
                .into(posterImg);

        posterNameText.setText(posterName);
        posterIdText.setText(""+posterId);

    }
    private void setListView(){
        putImg();
        postAdapter=new PostAdapter(this,posts,R.layout.post_item,ImgInAdress,ImgAttention,posterId);
        posterPostList.setAdapter(postAdapter);
        posterPostList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return true;
            }
        });
        posterPostList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("userId",posterId);
                intent.setClass(PosterInfor.this, Comment.class);
                intent.putExtra("post",posts.get(position));
                startActivity(intent);
            }
        });

    }

    private void putImg(){
//        Log.e("posts量",""+posts.size());
        int imgCount=posts.size();
        for (int i=0;i<imgCount;i++){
            ImgInAdress.add(R.drawable.likes);
            ImgAttention.add(R.drawable.circles);
        }
    }
    private void getUserInfo() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",posterId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetUserInfo")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的RecentRead的Json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    userInfoStr = gson.fromJson(responseData, String.class);
                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 3;

                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void getRecentReads() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",posterId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetRecentReadList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的RecentRead的Json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    recentReads = gson.fromJson(responseData, new TypeToken<List<RecentRead>>(){}.getType());
                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 2;

                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //获取post的字符信息
    private void getpost() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",posterId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetMyPost")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的json:",responseData);
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
    private void addAttention(){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",userId);
                    builder.add("posterId",posterId+"");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/AddAttention")
                            .build();


                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    String attentionInfo=gson.fromJson(responseData,String.class);
                    if (attentionInfo!=null && attentionInfo.equals("error")){
                        Message msg = new Message();
                        //设置Message对象的参数
                        msg.what = 4;
                        //发送Message
                        handler.sendMessage(msg);
                    }else {
                        Message msg = new Message();
                        //设置Message对象的参数
                        msg.what = 5;
                        //发送Message
                        handler.sendMessage(msg);
                    }

                    Log.e("增加关注","结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}