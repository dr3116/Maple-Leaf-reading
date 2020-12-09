package com.example.test4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FensActivity extends AppCompatActivity {
    private String userId;
    private String str2;
    private ListView fen;
    private List<Fens> fens = new ArrayList<Fens>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();
            }
        }
    };
    public FensActivity(){

    }
    //作用，参数存入str2，方便后面调用
    public static FensActivity newInstance(String str1) {
        FensActivity fensActivity = new FensActivity();
        fensActivity.str2= str1;
        return fensActivity;
    }
    private void setListView() {
        FensAdapter fensAdapter = new FensAdapter(this,fens, R.layout.my_fens_item);
        fen.setAdapter(fensAdapter);
        fen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"我的粉丝："+fens.get(position).getUserName(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fens);
        fen = findViewById(R.id.lv_fens);
        getFens();
    }
    public void getFens() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String id = Login.userId;
                    userId = id+"";
                    Log.e("userId",userId+"");
                    builder.add("fensId", userId);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR + "FensService")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:", responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    fens = gson.fromJson(responseData, new TypeToken<List<Fens>>() {
                    }.getType());
                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 1;
                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
