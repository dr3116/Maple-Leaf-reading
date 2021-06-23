package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.os.Bundle;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignToday extends AppCompatActivity {
    private SignDate signDate;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_today);
        userId=(String)getIntent().getStringExtra("userId");
        signDate = findViewById(R.id.signDate);
        Log.e("开始签到","开始");
        signDate.setOnSignedSuccess(new OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                Log.e("wqf","Success");
                upLoadPostComment();
            }
        });
    }
    //上传评论
    private void upLoadPostComment(){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("signerId",userId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/UpSignDateNum")
                            .build();
                    Response response = client.newCall(request).execute();
                    Log.e("增加签到日期数","结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}