package com.example.test4.search;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test4.ConfigUtil;
import com.example.test4.MainActivity;
import com.example.test4.R;
import com.example.test4.book.Book;
import com.example.test4.book.BookIntroduction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Search extends AppCompatActivity {
    private String str2;
    private ListView searchList;
    private EditText searchName;
    private ImageView search1;
    private ImageView back;
    private ImageView home;
    private LinearLayout searchHistoryLv;
    private LinearLayout hortSearchLv;
    private String userId;
    private ImageView declearSearchHistory;
    private SQLiteDatabase db;
    private String [] hortSearch;
    private List<Book> books = new ArrayList<Book>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                Log.e("设置listView", "成功");
                setListView();
            }
            else if (msg.what==2){
//                Log.e("设置热词","开始");
                setHortSearch();
            }
        }
    };

    public Search() {

    }

    //作用，参数存入str2，方便后面调用
    public static Search newInstance(String str1) {
        Search search = new Search();
        search.str2 = str1;
        return search;
    }

    private void setListView() {
        SearchAdapter searchAdapter = new SearchAdapter(this, books, R.layout.search_list_item);
        searchList.setAdapter(searchAdapter);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(Search.this, BookIntroduction.class);
                intent.putExtra("bookName", books.get(position).getBookName());
                intent.putExtra("readingVolume", books.get(position).getReadingVolume() + "");
                intent.putExtra("numberOfChapters", books.get(position).getNumberOfChapters() + "");
                intent.putExtra("bookRating", books.get(position).getBookRating() + "");
                intent.putExtra("author", books.get(position).getAuthor());
                intent.putExtra("numberOfCollections", books.get(position).getNumberOfCollections() + "");
                intent.putExtra("briefIntroduction", books.get(position).getBriefIntroduction());
                intent.putExtra("bookPhoto", books.get(position).getBookPhoto());
                startActivity(intent);
            }
        });
        //动态设置ListView将上面的部分覆盖
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) searchList.getLayoutParams();
        layoutParams.addRule(RelativeLayout.BELOW,R.id.index_ll);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        userId = getIntent().getStringExtra("userId");
        searchList = findViewById(R.id.lv_search);
        searchName = findViewById(R.id.et_find);
        search1 = findViewById(R.id.iv_search);
        back=findViewById(R.id.back);
        home=findViewById(R.id.back_home);
        searchHistoryLv = findViewById(R.id.history_lv);
        hortSearchLv=findViewById(R.id.search_heat);
        declearSearchHistory=findViewById(R.id.declear_search_history);
        createDB();
        createTable();
        setSearchHistory();
        getHortSearch();
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getBooks();
                addToHistory(searchName.getText().toString());
            }
        });
        declearSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击清除搜索历史","成功");
                //自己跳自己，实现刷新
                declearHistorySearch();
                Intent intent = new Intent(Search.this, Search.class);
                intent.putExtra("userId",userId);
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


    }
    private void declearHistorySearch(){
        String sql="drop table if exists SearchHistory";
        db.execSQL(sql);
    }

    /**
     * 大家都在搜设置
     */
    private void setHortSearch(){
        for (int i=0;i<hortSearch.length;i++){
            if (i>6){
                break;
            }else {
                @SuppressLint("ResourceType")
                XmlPullParser parser = Search.this.getResources().getXml(R.layout.search_heat_text);
                AttributeSet attributes = Xml.asAttributeSet(parser);
                int type;
                try {
                    while ((type = parser.next()) != XmlPullParser.START_TAG &&
                            type != XmlPullParser.END_DOCUMENT) {
                        // Empty
                    }

                    if (type != XmlPullParser.START_TAG) {
                        Log.e("", "the xml file is error!\n");
                    }
                } catch (XmlPullParserException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.d("", "" + parser.getAttributeCount());
                final TextView tv = new TextView(Search.this, attributes);
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                if(i<3){
                    tv.setText(hortSearch[i+1]);
                }
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("点击了热门搜索","开始搜索");
                        searchName.setText(tv.getText().toString());
                        getBooks();
                    }
                });
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Search.this, attributes);
                hortSearchLv.addView(tv, i, params);

            }
        }
    }

    /**
     * 将搜索历史传入sqlite
     */
    private void addToHistory(String indexStr) {
        ContentValues cv = new ContentValues();
        cv.put("user_id", userId);
        cv.put("search_content", indexStr);
        db.insert("SearchHistory", null, cv);

    }

    //创建sqlite数据库表
    private void createTable() {
        //判断是否存在目标表，不存在则创建
        Cursor cursor;
        String sql = "select count(*) from sqlite_master where Type='table' and name='SearchHistory'";
        cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        //检查数据库表是否存在
        if (cursor.getInt(0) == 0) {
            //拼接创建表结构的SQL语句
            String createTable = "create table SearchHistory(search_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER(10), search_content varchar(100))";
            db.execSQL(createTable);
        }

    }

    /**
     * 在本地files目录下创建数据库文件
     */
    private void createDB() {
        //获取本地目录
        String file =
                getFilesDir().getAbsoluteFile() + "/MapleReaderDB.db";
        //创建数据库,并获取数据库对象
        db = SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    private void setSearchHistory() {
        // TODO Auto-generated method stub
        @SuppressLint("ResourceType")
        XmlPullParser parser = Search.this.getResources().getXml(R.layout.search_history_text);
        AttributeSet attributes = Xml.asAttributeSet(parser);
        int type;
        try {
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }

            if (type != XmlPullParser.START_TAG) {
                Log.e("", "the xml file is error!\n");
            }
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //从数据库中拿取数据
        Cursor cursor;
        String selection = "user_id='" + userId + "'";
        cursor = db.query("SearchHistory", null, selection, null, null, null, null);
        Log.d("", "" + parser.getAttributeCount());
        while (cursor.moveToNext()) {
            final TextView tv = new TextView(Search.this, attributes);
            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            Log.e("数据库中的内容：", "" + cursor.getString(cursor.getColumnIndex("search_content")));
            tv.setText(cursor.getString(cursor.getColumnIndex("search_content")));
            //为生成的TextView 设置监听器
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("点击了搜索历史","开始搜索");
                    searchName.setText(tv.getText().toString());
                    getBooks();
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Search.this, attributes);
            searchHistoryLv.addView(tv, 0, params);
        }


    }

    //获取书的字符信息
    private void getBooks() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String name = searchName.getText() + "";
                    builder.add("search", name + "");
//                    builder.add("userName", name + "");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR + "APP/GetSearchList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("从服务端返回的json:", responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    books = gson.fromJson(responseData, new TypeToken<List<Book>>() {
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
    /**
     * 从后台获取热门搜索信息
     */
    private void getHortSearch(){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR + "APP/GetHortSearch")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    String tempStr= gson.fromJson(responseData,String.class);
                    hortSearch=tempStr.split("&&");

                    Message msg = new Message();
                    //设置Message对象的参数
                    msg.what = 2;
                    //发送Message
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}