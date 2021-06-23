package com.example.test4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditUserInfo extends AppCompatActivity {
    private CircleImageView changePhoto;
    private EditText niChengInput;
    private EditText sexInput;
    private EditText styleInput;
    private String userInfoStr;
    private String userId;
    private TextView cencleText;
    private TextView yesText;
    private static String path = "/sdcard/loverReader/";//sd路径
    private Bitmap head;//头像Bitmap
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==3){
                Log.e("userInfoStr",userInfoStr);
                String []arr=userInfoStr.split("&&");
                niChengInput.setHint(arr[2]);
                sexInput.setHint(arr[0]);
                styleInput.setHint(arr[1]);

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        userId=(String) getIntent().getStringExtra("userId");
        getView();
        getUserInfo();
        setCircleImageView();
        cencleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.putExtra("userId",userId);
                intent4.putExtra("mark",4);
                intent4.setClass(EditUserInfo.this,MainActivity.class);
                startActivity(intent4);
            }
        });
        yesText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upChangeInfo();
                upchangeImg();
            }
        });

    }
    private void getView(){
        changePhoto=findViewById(R.id.chenge_photo);
        niChengInput=findViewById(R.id.ni_cheng_input);
        sexInput=findViewById(R.id.sex_input);
        styleInput=findViewById(R.id.sytle_text_input);
        cencleText=findViewById(R.id.cencle_text);
        yesText=findViewById(R.id.yes_text);
    }
    private void setCircleImageView(){
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"images/student/"+userId+".jpg")
                .apply(requestOptions)
                .into(changePhoto);
        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(Intent.ACTION_PICK, null);//返回被选中项的URI
                    intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
//                System.out.println("MediaStore.Images.Media.EXTERNAL_CONTENT_URI  ------------   "
//                        + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//   content://media/external/images/media
                    startActivityForResult(intent1, 1);
                }catch (Exception e){
                    Toast.makeText(EditUserInfo.this, "无法打开相册，请先开启储存权限", Toast.LENGTH_LONG).show();
                }
            }
        });
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
                    }
                }
                changePhoto.setImageBitmap(head);
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
    private void upchangeImg() {
        new Thread() {
            @Override
            public void run() {
                try {
                    /**
                     * 判断文件夹是否存在
                     */
                    File fileDir = new File(path);
                    if (!fileDir.exists() && fileDir.isDirectory()) {
                        fileDir.mkdir();
                    }
                    FileOutputStream b = null;
                    File file = new File(path + "head.jpg");
                    if (!file.exists()) {
                       return ;
                    }
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
                    Log.e("头像文件名称：",file.getName());
                    //获取后缀名称
                    String ext = file.getName().substring(file.getName().lastIndexOf("."),file.getName().length());
                    MultipartBody multipartBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("image_file", userId+ext, requestBody)//文件名,请求体里的文件
                            .build();
                    Request request = new Request.Builder()
                            .url(ConfigUtil.SERVER_ADDR + "APP/UpUserPhotoChangeService")
                            //默认就是GET请求就可以不写，这里是post
                            .post(multipartBody)
                            .build();
                    Call call = client.newCall(request);
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
                            Log.e("将头像图片上传", "成功");

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {

                    Intent intent5=new Intent();
                    intent5.putExtra("userId",userId);
                    intent5.putExtra("mark",4);
                    intent5.setClass(EditUserInfo.this,MainActivity.class);
                    startActivity(intent5);
                }
            }
        }.start();
    }
    private void upChangeInfo(){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",userId);
                    String []arr=userInfoStr.split("&&");
                    if (isChanged(niChengInput)){
                        builder.add("userName",niChengInput.getText().toString());
                    }else {
                        builder.add("userName",arr[2]);
                    }

                    if (isChanged(sexInput)){
                        builder.add("userSex",sexInput.getText().toString());
                    }else {

                        builder.add("userSex",arr[0]);
                    }


                    if (isChanged(styleInput)){
                        builder.add("userStyleText",styleInput.getText().toString());
                    }else {
                        builder.add("userStyleText",arr[1]);
                    }



                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/UpUserInfoChangeService")
                            .build();
                    Response response = client.newCall(request).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private boolean isChanged(EditText editText){
        if(editText.getText().toString().equals("")){
            Log.e("用户信息更改判断结果","没改");
            return false;
        }else{
            Log.e("用户信息更改判断结果","改了");
            return true;
        }
    }
    private void getUserInfo() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",userId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetUserInfo")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的RecentRead的Json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    userInfoStr = gson.fromJson(responseData, String.class);
                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 3;

                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}