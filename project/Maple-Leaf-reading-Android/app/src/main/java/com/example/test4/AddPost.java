package com.example.test4;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddPost extends AppCompatActivity {
    private ImageView cancle;
    private TextView postAction;
    private EditText postConnentInput;
    private ImageView addImg;
    private ImageView camera;
    private ImageView addBook;
    private ImageView addImgShow;
    private LinearLayout bookInfo;
    private ImageView bookImg;
    private TextView bookName;
    private TextView bookAuthor;
    private String tempInputContentStr;
    private String tempuserId;
    private String bookImgStr;
    private String bookNameStr;
    private String bookAuthorStr;
    private String userId;
    private Bitmap head;//头像Bitmap
    private static String path = "/sdcard/loverReader/";//sd路径
    //(/sdcard/  目录怎么感觉跟Environment.getExternalStorageDirectory()得到的目录是一个效果？)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        tempInputContentStr=getIntent().getStringExtra("input");
        userId=getIntent().getStringExtra("userId");
        head=getIntent().getParcelableExtra("head");

        bookImgStr=getIntent().getStringExtra("bookPhoto");
        bookNameStr=getIntent().getStringExtra("bookName");
        bookAuthorStr=getIntent().getStringExtra("author");

        //取消严格模式 FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }
        initView();
        setView();

    }
    private void initView(){
        cancle=findViewById(R.id.cancle_post);
        postAction=findViewById(R.id.post_actionn);
        postConnentInput=findViewById(R.id.post_conent_input);
        addImg=findViewById(R.id.add_img_img);
        camera=findViewById(R.id.camera);
        addBook=findViewById(R.id.add_book_img);
        addImgShow=findViewById(R.id.up_img_img);
        bookInfo=findViewById(R.id.bookInfo);
        bookImg=findViewById(R.id.z_book_img);
        bookName=findViewById(R.id.z_book_name);
        bookAuthor=findViewById(R.id.z_book_author);

    }
    @SuppressLint("ResourceAsColor")
    private void setView(){
        MyListener myListener=new MyListener();
        addImg.setOnClickListener(myListener);
        camera.setOnClickListener(myListener);
        addBook.setOnClickListener(myListener);
        cancle.setOnClickListener(myListener);
        postAction.setOnClickListener(myListener);




        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        if (bookImgStr!=null){
            Glide.with(this)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookImgStr)
                    .apply(requestOptions)
                    .into(bookImg);
            //顺便设置一下背景
            bookInfo.setBackgroundColor(R.color.gray91);
        }
        if (bookNameStr!=null){
            bookName.setText(bookNameStr);
        }
        if (bookAuthorStr!=null){
            bookAuthor.setText(bookAuthorStr);
        }
        if (head!=null){
            addImgShow.setImageBitmap(head);//用ImageView显示出来
        }
        if (tempuserId!=null){
            userId=tempuserId;
        }
        if (tempInputContentStr!=null){
            postConnentInput.setText(tempInputContentStr);
        }

    }
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add_img_img:
                    try {
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);//返回被选中项的URI
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
//                System.out.println("MediaStore.Images.Media.EXTERNAL_CONTENT_URI  ------------   "
//                        + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//   content://media/external/images/media
                        startActivityForResult(intent1, 1);
                    }catch (Exception e){
                        Toast.makeText(AddPost.this, "无法打开相册，请先开启储存权限", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.camera:
                    //最好用try/catch包裹一下，防止因为用户未给应用程序开启相机权限，而使程序崩溃
                    try {
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//开启相机应用程序获取并返回图片（capture：俘获）
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "head.jpg")));//指明存储图片或视频的地址URI
                        startActivityForResult(intent2, 2);//采用ForResult打开
                    } catch (Exception e) {
                        Toast.makeText(AddPost.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.add_book_img:
                    Log.e("EditText失去焦点","储存内容");
                    Intent intent3=new Intent();
                    intent3.setClass(AddPost.this,AddPostBook.class);
                    if (head!=null){
                        intent3.putExtra("head",head);
                    }
                    if (postConnentInput.getText().toString()!=null){
                        intent3.putExtra("input",postConnentInput.getText().toString());
                    }
                    if (userId!=null){
                        intent3.putExtra("userId",userId);
                    }
                    startActivity(intent3);
                    break;
                case R.id.cancle_post:
                    Intent intent4=new Intent();
                    intent4.putExtra("userId",userId);
                    intent4.setClass(AddPost.this,MainActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.post_actionn:
                    upPostImg();
                    upPostStrInfo();
                    break;
            }
        }
    }
    /**
     * 这里分两步发送
     * 先将BItMap发送
     * 再将POST的json字符串发送
     */
    private void upPostImg(){
        new Thread(){
            @Override
            public void run() {
                try {
                    FileOutputStream b = null;
                    File file = new File(path + "head.jpg");
                    String fileName = path + "head.jpg";//图片名字
                    try {
                        b = new FileOutputStream(fileName);
                        //对图片进行压缩，不压缩会报错
                        head.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            //关闭流
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    //将图片信息上传
                    MediaType mediaType = MediaType.parse("application/octet-stream");//设置类型，类型为八位字节流
                    OkHttpClient client = new OkHttpClient();
                    //POST请求
                    RequestBody requestBody = RequestBody.create(mediaType, file);//把文件与类型放入请求体

                    MultipartBody multipartBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("image_file", file.getName(), requestBody)//文件名,请求体里的文件
                            .build();
                    Request request = new Request.Builder()
                            .url(ConfigUtil.SERVER_ADDR+"UpPostImgService")
                            //默认就是GET请求就可以不写，这里是post
                            .post(multipartBody)
                            .build();
                    Call call=client.newCall(request);
                    call.enqueue(new okhttp3.Callback() {//todo 图片太大会报错 expected 346 bytes but received 8192
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    e.printStackTrace();
                                    //失败的操作
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //成功的操作
                            Log.e("将图片上传","成功");

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {

                    Intent intent5=new Intent();
                    intent5.putExtra("userId",userId);
                    intent5.setClass(AddPost.this,MainActivity.class);
                    startActivity(intent5);
                }
            }
        }.start();

    }
    private void upPostStrInfo(){
        /**
         * 将字符串信息上传
         */
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("posterId",userId);
                    builder.add("input",""+postConnentInput.getText().toString());
                    builder.add("bookName",bookNameStr);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"/UpPosetInfo")
                            .build();
                    Call call2=client.newCall(request);
                    call2.enqueue(new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("将字符串上传","失败");
                                    e.printStackTrace();
                                    //失败的操作
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //成功的操作
                            Log.e("将字符串上传","成功");
                            Log.e("将新的POST发送","结束");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从相册里面取相片的返回结果
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            //相机拍照后的返回结果
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            //调用系统裁剪图片后
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        addImgShow.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    ;

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        //进入系统裁剪图片的界面
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建以此File对象为名（path）的文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}