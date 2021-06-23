package com.pietyhr.yunxindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.netease.nim.uikit.impl.NimUIKitImpl;

import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText pwdText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_yunxin);
        Log.e("跳转到LoginActivity","成功");
        findViews();//初始化控件
    }

    private void findViews() {
        nameText = findViewById(R.id.et_username);

        pwdText = findViewById(R.id.et_password);

        loginButton = findViewById(R.id.bt_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }
    private void login() {
        LoginInfo info = new LoginInfo(nameText.getText().toString(), pwdText.getText().toString()); // config...
        RequestCallback<LoginInfo> callback =  new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo param) {
                Toast.makeText(LoginActivity.this, "恭喜您登陆成功", Toast.LENGTH_SHORT).show();
                NimUIKitImpl.setAccount(param.getAccount());
                startActivity(new Intent(LoginActivity.this, MainActivity2.class));
            }
            @Override
            public void onFailed(int code) { }
            @Override
            public void onException(Throwable exception) {}
            // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
        };
        NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }
}
