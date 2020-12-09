package com.example.test4.main_fragment_all_5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.test4.ConfigUtil;
import com.example.test4.BookShelfBook;
import com.example.test4.BookShelfAdapter;
import com.example.test4.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment4 extends Fragment {

    private String str2;
    private int img;
    private Button btn11;
    private ListView bookShelfListView;
    private List<BookShelfBook> books=new ArrayList<BookShelfBook>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Log.e("设置listView","成功");
                setListView();

            }
        }
    };

    private void setListView(){
        BookShelfAdapter bookShelfAdapter=new BookShelfAdapter(getContext(),books,R.layout.bookshelf_item);
        bookShelfListView.setAdapter(bookShelfAdapter);
//        bookShelfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent();
//                intent.setClass(getContext(), Comment.class);
//                intent.putExtra("BookShelfBook",books.get(position));
//                startActivity(intent);
//            }
//        });
    }
    //获取post的字符信息
    private void getBookShelfInfo() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId","1");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"GetBookShelf")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    books = gson.fromJson(responseData, new TypeToken<List<BookShelfBook>>(){}.getType());
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
    public SimpleFragment4() {


    }



    //作用，参数存入str2，方便后面调用
    public static SimpleFragment4 newInstance(String str1) {
        SimpleFragment4 simpleFragment4 = new SimpleFragment4();
        simpleFragment4.str2 = str1;
        return simpleFragment4;
    }




    @Override//这个方法比上个方法后执行
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        View view = inflater.inflate(R.layout.activity_bookshelf, container, false);
        bookShelfListView=view.findViewById(R.id.bookshelf_list);
        getBookShelfInfo();
        return view;
    }
}