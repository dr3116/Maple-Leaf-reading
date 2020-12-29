package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.test4.bookReadPage.BookReadActivity;
import com.example.test4.bookReadPage.PDFReadActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FileDownLoad extends AppCompatActivity {
    private String fileName;
    String path = "/sdcard/loverReader/";
    private ProgressBar pro;
    private String userId;
    private FrameLayout frameLayout;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if (msg.what == 1) {
                pro.setProgress(msg.arg1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_down_load);
        userId=getIntent().getStringExtra("userId");
        fileName=getIntent().getStringExtra("bookName");
        pro = findViewById(R.id.pro);
        frameLayout=findViewById(R.id.fra);
        /**
         * 判断文件夹是否存在
         */
        File fileDir = new File(path);
        if (!fileDir.exists()&&fileDir.isDirectory()){
            fileDir.mkdir();
        }
        /**
         * 判断文件是否存在
         */
        File file = new File(path, fileName+".pdf");
        if (!file.exists()){
            Log.e("文件不存在本地","开始下载");
            downLoad();
        }else {
            Log.e("文件本地有本地副本","跳过下载");
            frameLayout.removeView(pro);
        }


        Thread thread=new Action();
        thread.start();

    }

    /**
     * 注意！不能在主线程中这样跳转
     * 因为写在oncreate()中的话，sleep()会影响activity生命周期
     */
    private class Action extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent=new Intent();
            intent.setClass(FileDownLoad.this, PDFReadActivity.class);
            intent.putExtra("userId",userId);
            Log.e("在文件下载页面答应书名：",fileName+".pdf");
            intent.putExtra("fileName",fileName+".pdf");
            startActivity(intent);
        }
    }
    private void downLoad(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(ConfigUtil.SERVER_ADDR+"test/"+fileName+".pdf").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("文件下载失败","结束");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    writeFile(response);
                }
            }

        });
    }
    private void writeFile(Response response) {
        InputStream is = null;
        FileOutputStream fos = null;
        is = response.body().byteStream();

        File file = new File(path, fileName+".pdf");
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            //获取下载的文件的大小
            long fileSize = response.body().contentLength();
            long sum = 0;
            int porSize = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes);
                sum += len;
                porSize = (int) ((sum * 1.0f / fileSize) * 100);
                Message message = handler.obtainMessage(1);
                message.arg1 = porSize;
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("myTag", "下载成功");


        }

    }

}