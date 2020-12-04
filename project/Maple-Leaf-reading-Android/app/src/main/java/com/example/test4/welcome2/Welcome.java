package com.example.test4.welcome2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test4.Login;
import com.example.test4.MainActivity;
import com.example.test4.databinding.WelcomeBinding;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {
    //视图绑定生成类文件,viewBinding技术
    private WelcomeBinding binding;
    private Timer timer;
    private int time=4;
    private Handler myHandler;//线程




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用第三代技术，viewBinding    private WelcomeBinding binding;

        binding=WelcomeBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        //设置welcome欢迎页面的点击跳过按钮，跳转到主界面
        binding.btnWelcomeTiaoGuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Welcome.this, Login.class);
                startActivity(intent1);
                timer.cancel();
                finish();
            }
        });


        myHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {//判断是什么参数
                    case 1:
                        binding.btnWelcomeTiaoGuo.setText(time+"    跳转");
                        break;
                    case 2:
                        Intent intent1=new Intent(Welcome.this,Login.class);
                        startActivity(intent1);
                        finish();
                        break;
                    }
            }
        };




        //Timer是定时器工具，
        timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Log.e("welcome页面倒计时  ",time+"");
                Message msg;
                if(time>1){
                    time--;
                    msg= myHandler.obtainMessage();
                    msg.what = 1;
                }else{
                    msg = myHandler.obtainMessage();
                    msg.what = 2;
                    timer.cancel();
                }
                myHandler.sendMessage(msg);
            }
        };



        //开启定时器,立即执行，每次耗时一秒钟
        timer.schedule(timerTask,0,1000);
    }
}