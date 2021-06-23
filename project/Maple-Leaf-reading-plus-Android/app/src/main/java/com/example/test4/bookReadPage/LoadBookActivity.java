package com.example.test4.bookReadPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.test4.ConfigUtil;
import com.example.test4.R;
import com.example.test4.book.BookIntroduction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

/**
 * 加载BookPDF
 */
public class LoadBookActivity extends AppCompatActivity {
    private String name;
    private String userId;
    private TextView processNum;
    private TextView persentStr;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            setView1(msg.what+"");
            if(msg.what==100){
                Log.e("文件下载完成开始解压","成功");
                //执行解压
                setView2();
                Thread thread=new uncompressFile(Environment.getExternalStorageDirectory()+"/loverReader/"+name+".gz",
                        Environment.getExternalStorageDirectory()+"/loverReader/"+name+".pdf");
                thread.start();
            }else if (msg.what==88888888){
                Log.e("解压完成","完成");
                startIntent();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_book);
        name = getIntent().getStringExtra("bookName");
        userId=getIntent().getStringExtra("userId");
        getView();
        File file = new File(Environment.getExternalStorageDirectory()+"/loverReader/"+name+".pdf");
        Log.e("path",Environment.getExternalStorageDirectory()+"/loverReader/"+name+".pdf");
        if(!file.exists() || file.length() == 0) {
            Log.e("结果","文件不存在或者为空");
            //执行下载
            Log.e("主线程开始下载文件","开始");
            doDownLoad();

        }else {
            Log.e("正常","文件不为空");
            //文件不为空，直接跳转
            startIntent();
        }




    }
    private void getView(){
        processNum=findViewById(R.id.process_num);
        persentStr=findViewById(R.id.persent_str);
    }
    private void setView1(String process){
        processNum.setText(process);
    }
    private void setView2(){
        processNum.setText("正在解压");
        persentStr.setText(" ");
    }
    //result=0，表示还没有结果，注意还不能返回
    //result=1,表示成功
    //result=2,表示出错
    private int result=0;
    public void   doDownLoad(){
        ///sdcard/loverReader
        /**
         * 主线程进来后在匿名内部类中启动了一个子线程
         */





        //开始从服务器下载图书压缩包,路劲为："+"D:\\Users\\Administrator\\Desktop\\Maple-Leaf-Reading\\pdfs\\"+name+".gz");
        //ConfigUtil.SERVER_ADDR+"APP/UpPosetInfo"
//        DownLoadBookPDF.get().download("http://192.168.43.29/upload/pdf/"+"test"+".gz", "/loverReader/", new DownLoadBookPDF.OnDownloadListener(){
        DownLoadBookPDF.get().download(ConfigUtil.SERVER_ADDR+"APP/download/"+name, "/loverReader/", new DownLoadBookPDF.OnDownloadListener(){
//        DownLoadBookPDF.get().download(ConfigUtil.SERVER_ADDR+"pdfs/"+name+".gz", "/loverReader/", new DownLoadBookPDF.OnDownloadListener(){
                @Override
            public void onDownloadSuccess () {
                //成功
                Log.i("注意", "下载成功");
            }

            @Override
            public void onDownloading ( int progress){
                //进度
                Log.i("注意", progress + "%");
                Message msg = new Message();
                //设置Message对象的参数
                msg.what =progress;
                //发送Message
                handler.sendMessage(msg);
            }

            @Override
            public void onDownloadFailed () {
                //失败
                Log.i("注意", "下载失败");

            }
        });


//        /**
//         * 每3秒判断一次下载是否完成，没有完成就休眠等待
//         * 这样做很耗费资源，但是没办法
//         */
//        while (true){
//            if (result==0){
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }else {
//                break;
//            }
//        }
//        if (result==1){
//            return true;
//        }else{
//            return false;
//        }
    }
    public class uncompressFile extends Thread {

       private String BeforeFileName;
       private String AfterFileName;
       public uncompressFile(String BeforeFileName,String AfterFileName){
           this.AfterFileName=AfterFileName;
           this.BeforeFileName=BeforeFileName;
       }
        @Override
        public void run() {
            //先判断文件是否为解压缩文件
            //通过后缀名判断
            Log.e("正在解压","成功");
            if(!getHouZuiMing(BeforeFileName).equals("gz")) {
                System.out.println("这不是一个压缩文件");
                System.exit(1);
            }
            //使用GZIPInputStream进行解压缩
            try {
                GZIPInputStream in=new GZIPInputStream(new FileInputStream(BeforeFileName));
                FileOutputStream out=new FileOutputStream(AfterFileName);
                byte [] temp=new byte[1024];
                int len;
                while((len=in.read(temp))>0) {
                    out.write(temp,0,len);
                }
                out.close();
                in.close();
                Message msg = new Message();
                //设置Message对象的参数
                msg.what =88888888;
                //发送Message
                handler.sendMessage(msg);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static String getHouZuiMing(String fileName) {
        String houZui="";
        System.out.println("文件名称"+fileName);
        String temp[]=fileName.split("\\.");

        int len=temp.length;
        houZui=temp[len-1];
        return houZui;
    }
    private void startIntent(){
        Intent intent = new Intent(this, PDFReadActivity.class);
        intent.putExtra("bookName",name);
        intent.putExtra("userId",userId);
        intent.putExtra("fileName",name+".pdf");
        intent.putExtra("order","1");
        startActivity(intent);
    }
}