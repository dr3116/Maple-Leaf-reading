package com.pietyhr.yunxindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.business.recent.RecentContactsFragment;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;


/**
 * @method com.pietyhr.yunxindemo
 * @author: 我心飞扬不飘荡
 * @date: 2019/12/25 0025
 * @description 描述一下方法的作用
 */
public class MainActivity extends Activity {
    private Button logout;
    private Button chat;
    private TextView state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yunxin);
        initView();
        state.setText("你已經登陸成功了！！！");
    }

    private void initView() {
        logout = findViewById(R.id.bt_logout);
        logout.setOnClickListener(v -> logout());
        chat = findViewById(R.id.bt_chat);
        chat.setOnClickListener(v -> NimUIKit.startP2PSession(MainActivity.this,"private"));
        state = findViewById(R.id.tv_state);
    }

    /**
     * 注销回调函数
     */
    private void logout() {
        NIMClient.getService(AuthService.class).logout();
        finish();
    }
}
