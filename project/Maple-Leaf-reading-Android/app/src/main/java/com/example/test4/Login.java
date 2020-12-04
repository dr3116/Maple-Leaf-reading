package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test4.main_fragment_all_5.SimpleFragment1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private EditText phoneNumber;
    private EditText passWord;
    private Button login;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        setView();
    }
    private void findView() {
        phoneNumber=findViewById(R.id.et_login_username);
        passWord=findViewById(R.id.et_login_pwd);
        login=findViewById(R.id.bt_login_submit);
    }

    private void setView() {
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LoginAction();
            }
        });
    }
    private void LoginAction(){
        new Thread() {
            @Override
            public void run() {
                try {
                    String phoneStr=phoneNumber.getText().toString().trim();
                    String passwordStr=passWord.getText().toString().trim();
                    try {
                        OkHttpClient client = new OkHttpClient();
                        FormBody.Builder builder = new FormBody.Builder();
                        builder.add("phoneStr",phoneStr);
                        builder.add("passwordStr",passwordStr);
                        FormBody body = builder.build();
                        Request request = new Request.Builder()
                                // 指定访问的服务器地址
                                .post(body)
                                .url(ConfigUtil.SERVER_ADDR+"LoginService")
                                .build();
                        Response response = client.newCall(request).execute();
                        //获得json字符串
                        String responseData = response.body().string();
                        Log.e("从服务端返回的json:",responseData);
                        //解析字符串
                        Gson gson = new GsonBuilder()
                                .create(); //生成配置好的Gson
                        String result = gson.fromJson(responseData,String.class);
                        if (result.equals("error")){
                            Toast.makeText(getApplicationContext(),"用户不存在或密码错误",Toast.LENGTH_LONG).show();
                        }else {
                            userId=result;
                            Intent intent=new Intent();
                            intent.putExtra("userId",userId);
                            intent.setClass(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}