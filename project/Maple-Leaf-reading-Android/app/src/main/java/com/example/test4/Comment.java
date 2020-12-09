package com.example.test4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Comment extends AppCompatActivity {
    private CircleImageView posterPhoto;
    private TextView posterName;
    private TextView postContent;
    private ImageView postImg;
    private LinearLayout bookInfo;
    private ImageView bookImg;
    private TextView bookName;
    private TextView bookAuthor;
    private ListView commentList;
    private EditText inputComment;
    private ImageView upComment;
    private Post post;
    private String userId;
    private MyThread myThread;
    private List<PostComment> postComments=new ArrayList<>();
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
        setContentView(R.layout.activity_comment);
        //HideIMEUtil.wrap(Comment.this);
        HideIMEUtil.wrap(Comment.this);
        userId=(String)getIntent().getStringExtra("userId");
        post=(Post) getIntent().getSerializableExtra("post");

        findViews();
        setView();
        getpostComment();
    }
    private void findViews() {
        posterPhoto=findViewById(R.id.poster_img);
        posterName=findViewById(R.id.poster_name);
        postContent=findViewById(R.id.post_text);
        postImg=findViewById(R.id.post_img);
        bookInfo=findViewById(R.id.bookInfo);
        bookImg=findViewById(R.id.z_book_img);
        bookName=findViewById(R.id.z_book_name);
        bookAuthor=findViewById(R.id.z_book_author);
        commentList=findViewById(R.id.comment_list);
        inputComment=findViewById(R.id.coment_input);
        upComment=findViewById(R.id.up_comment_img);
    }
    private void  setView(){


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"imgs/"+post.getPhoto())
                .apply(requestOptions)
                .into(postImg);
        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"img1/"+post.getUserId()+".jpg")
                .apply(requestOptions)
                .into(posterPhoto);
        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"img/"+post.getBookImg())
                .apply(requestOptions)
                .into(bookImg);
        posterName.setText(post.getUserName());
        postContent.setText(post.getContent());
        bookName.setText(post.getBookName());
        bookAuthor.setText(post.getBookAuthor());
        upComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("发送评论按钮被点击","发送");
                if (inputComment.getText().toString()!=null){
                    upLoadPostComment();
                }

            }
        });


    }
    private void setListView(){
        PostCommentAdapter adapter=new PostCommentAdapter(getApplicationContext(),postComments,R.layout.post_comment_item);
        commentList.setAdapter(adapter);
        //解决ListView和SorrentView起冲突问题
        setListViewHeight(commentList);
    }
    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     * @param listView
     */
    public void setListViewHeight(ListView listView) {
    // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount            ()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }
    private void getpostComment() {
       myThread=new MyThread();
       myThread.start();
    }
    private class MyThread extends Thread{
        @Override
        public void run() {
            try {
                OkHttpClient client = new OkHttpClient();
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("postId",String.valueOf(post.getPostId()));
                FormBody body = builder.build();
                Request request = new Request.Builder()
                        // 指定访问的服务器地址
                        .post(body)
                        .url(ConfigUtil.SERVER_ADDR+"GetPostComment")
                        .build();
                Response response = client.newCall(request).execute();
                //获得json字符串
                String responseData = response.body().string();
                Log.e("从服务端返回的json:",responseData);
                //解析字符串
                Gson gson = new GsonBuilder()
                        .create(); //生成配置好的Gson
                postComments = gson.fromJson(responseData, new TypeToken<List<PostComment>>(){}.getType());
                Message msg = new Message();
                //设置Message对象的参数
                msg.what = 1;
                //发送Message
                handler.sendMessage(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //上传评论
    private void upLoadPostComment(){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("posterId",userId);
                    builder.add("postId",""+post.getPostId());
                    builder.add("postComment",inputComment.getText().toString());
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"UpLoadPostComment")
                            .build();
                    Response response = client.newCall(request).execute();
                    Log.e("重新获取评论","开始");
                    getpostComment();
                    inputComment.clearFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}