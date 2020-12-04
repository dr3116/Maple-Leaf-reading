package com.example.test4.book;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test4.BookReview;
import com.example.test4.BookReviewAdapter;
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

public class BookIntroduction extends AppCompatActivity {
    private TextView bookName;
    private TextView readingVolume;
    private TextView numberOfChapters;
    private TextView bookRating;
    private TextView author;
    private TextView numberOfCollections;
    private TextView briefIntroduction;
    private ImageView bookPhoto;
    private ListView bookReview;
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private List<BookReview> bookReviews = new ArrayList<BookReview>();
    private List<Book> bookList;
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
        BookReviewAdapter bookReviewAdapter = new BookReviewAdapter(this,bookReviews, R.layout.bookreview_list_item);
        bookReview.setAdapter(bookReviewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_introduction);
        mInflater = LayoutInflater.from(this);
        //获取控件
        getView();
        //获取传递来的数据
        String name = getIntent().getStringExtra("bookName");
        bookName.setText(name);
        String rVolume = getIntent().getStringExtra("readingVolume");
        readingVolume.setText(rVolume);
        String Chapters = getIntent().getStringExtra("numberOfChapters");
        numberOfChapters.setText(Chapters);
        String bRating = getIntent().getStringExtra("bookRating");
        bookRating.setText(bRating);
        String bAuthor = getIntent().getStringExtra("author");
        author.setText(bAuthor);
        String Collections = getIntent().getStringExtra("numberOfCollections");
        numberOfCollections.setText(Collections);
        String bIntroduction = getIntent().getStringExtra("briefIntroduction");
        briefIntroduction.setText(bIntroduction);
        String bPhoto = getIntent().getStringExtra("bookPhoto")+"";
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(this)
                .load(ConfigUtil.SERVER_ADDR+"img/"+bPhoto)
                .apply(requestOptions)
                .into(bookPhoto);
        initData();
        initView();
        getBookReview();
    }

    private void getBookReview() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR+"GetBookReviewList")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    bookReviews = gson.fromJson(responseData, new TypeToken<List<BookReview>>(){}.getType());
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


    private void getView() {
        bookName = findViewById(R.id.tv_bookName);
        readingVolume = findViewById(R.id.tv_readingVolume);
        numberOfChapters = findViewById(R.id.tv_numberOfChapters);
        bookRating = findViewById(R.id.tv_bookRating);
        author = findViewById(R.id.tv_author);
        numberOfCollections = findViewById(R.id.tv_numberOfCollections);
        briefIntroduction = findViewById(R.id.tv_briefIntroduction);
        bookPhoto = findViewById(R.id.iv_book_photo);
        bookReview = findViewById(R.id.lv_comments);
    }

    private void initData() {
        mImgIds = new int[] { R.drawable.bookimg2, R.drawable.bookimg, R.drawable.linshi, R.drawable.bookimg3, R.drawable.bookimg2, R.drawable.bookimg, R.drawable.bookimg3
        };
    }

    private void initView() {
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);

        for (int i = 0; i < mImgIds.length; i++) {
            View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgIds[i]);
            TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
            txt.setText("some info + I");
            txt.setTextColor(Color.BLACK);
            mGallery.addView(view);
        }
    }

}