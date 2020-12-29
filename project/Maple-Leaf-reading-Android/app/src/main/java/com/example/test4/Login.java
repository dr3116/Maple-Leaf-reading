package com.example.test4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {
    private EditText phoneNumber;
    private EditText passWord;
    private Button login;
    public  static String userId;
    private CheckBox remenberPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String nameStr=getIntent().getStringExtra("nameStr");
        String passwordStr=getIntent().getStringExtra("passwordStr");
        if (nameStr!=null&&passwordStr!=null){
            phoneNumber.setText(nameStr);
            passWord.setText(passwordStr);
        }
        findView();
        setView();
    }
    private void findView() {
        phoneNumber=findViewById(R.id.et_login_username);
        passWord=findViewById(R.id.et_login_pwd);
        login=findViewById(R.id.bt_login_submit);
        remenberPassword=findViewById(R.id.cb_remember_login);
    }

    private void setView() {
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LoginAction();
            }
        });
        SharedPreferences sharedPreferences=getSharedPreferences("text",MODE_PRIVATE);
        String nameStr=sharedPreferences.getString("name","");
        String passwordStr=sharedPreferences.getString("password","");
        Log.e("sharePraferenc里面的数据",nameStr+"  :  "+passwordStr);
        if (nameStr!=null&&passwordStr!=null){
            phoneNumber.setText(nameStr);
            passWord.setText(passwordStr);
        }
        remenberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (phoneNumber.getText()!=null&&passWord.getText()!=null){
                        SharedPreferences sp=getSharedPreferences("text",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("name",phoneNumber.getText().toString());
                        editor.putString("password",passWord.getText().toString());
                        Log.e("记住密码和账号",""+phoneNumber.getText().toString()+"    "+passWord.getText().toString());
                    }else {
                        Toast.makeText(Login.this,"请先填写账号和密码",Toast.LENGTH_LONG).show();
                    }

                }
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
                            intent.setClass(Login.this, MainActivity.class);
                            intent.putExtra("userId",userId);
                            intent.putExtra("userName",phoneStr);
                            intent.putExtra("userPassword",passwordStr);
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