package com.example.test4.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test4.ConfigUtil;
import com.example.test4.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 12/11 杜然
 */
public class FirstFragment extends Fragment {
    private String str2;
    private ListView firstList;
    private List<SignInPaihang> signInPaihangs = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();
            }
        }
    };

    private void setListView() {
        FirstFragmentAdapter firstFragmentAdapter = new FirstFragmentAdapter(getContext(),signInPaihangs, R.layout.paihang_item);
        firstList.setAdapter(firstFragmentAdapter);
        firstList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面的布局文件（将内容页面的XML布局文件转成View类型的对象）
        View view = inflater.inflate(R.layout.fragment_paihang,//内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到container
        //true表示不需要手动调用addView方法
        firstList = view.findViewById(R.id.lv_paihang);
        getSignInPaihang();
        return view;
    }
    public FirstFragment(){

    }
    public static FirstFragment newInstance(String str1) {
        FirstFragment firstFragment = new FirstFragment();
        firstFragment.str2= str1;
        return firstFragment;
    }

    private void getSignInPaihang() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR+"GetSignInPaihangList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    signInPaihangs= gson.fromJson(responseData, new TypeToken<List<SignInPaihang>>(){}.getType());
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
