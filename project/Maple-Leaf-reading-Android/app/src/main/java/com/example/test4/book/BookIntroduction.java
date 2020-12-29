package com.example.test4.book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test4.AddPingLunActivity;
import com.example.test4.bookReadPage.BookReadActivity;
import com.example.test4.BookReview;
import com.example.test4.BookReviewAdapter;
import com.example.test4.ConfigUtil;
import com.example.test4.Login;
import com.example.test4.R;
import com.example.test4.bookReadPage.PDFReadActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
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
    private LinearLayout bookRead;
    private LinearLayout bookPinglun;
    private LinearLayout bookShoucang;
    private ListView bookReview;
    private TextView tvshoucang;
    private ImageView ivshoucang;
    private LinearLayout mGallery;
    private int[] mImgIds;
    private String userId;
    private LayoutInflater mInflater;
    private List<BookReview> bookReviews = new ArrayList<BookReview>();
    private List<Book> bookList;
    private int sum=0;
    private String name;
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
        setListViewHeight(bookReview);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_book_introduction);
        userId=getIntent().getStringExtra("userId");
        mInflater = LayoutInflater.from(this);
        //获取控件
        getView();
        //获取传递来的数据
        name = getIntent().getStringExtra("bookName");
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
        setOnclick();
    }

    /**
     * 按钮的点击事件
     */
    private void setOnclick() {
        //阅读按钮点击事件
        bookRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始阅读，intent跳转。
                Intent intent = new Intent(BookIntroduction.this, PDFReadActivity.class);
                intent.putExtra("bookName",name);
                intent.putExtra("userId",userId);
                intent.putExtra("fileName",name+".pdf");
                intent.putExtra("order","1");
                startActivity(intent);
            }
        });
        //评论按钮点击事件
        bookPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookIntroduction.this, AddPingLunActivity.class);
                intent.putExtra("bookName",name);
                String rVolume = getIntent().getStringExtra("readingVolume");
                intent.putExtra("readingVolume",rVolume);
                String Chapters = getIntent().getStringExtra("numberOfChapters");
                intent.putExtra("numberOfChapters",Chapters);
                String bRating = getIntent().getStringExtra("bookRating");
                intent.putExtra("bookRating",bRating);
                String bAuthor = getIntent().getStringExtra("author");
                intent.putExtra("author",bAuthor);
                String Collections = getIntent().getStringExtra("numberOfCollections");
                intent.putExtra("numberOfCollections",Collections);
                String bIntroduction = getIntent().getStringExtra("briefIntroduction");
                intent.putExtra("briefIntroduction",bIntroduction);
                String bPhoto = getIntent().getStringExtra("bookPhoto")+"";
                intent.putExtra("bookPhoto",bPhoto);
                startActivity(intent);
            }
        });
        //收藏按钮点击事件
        bookShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sum= sum+1;
                if(sum%2==1){
                    tvshoucang.setText("已收藏");
                    ivshoucang.setBackgroundColor(getResources().getColor(R.color.account_pressed_true));
                    setBookShelf();
                }else{
                    tvshoucang.setText("收藏");
                    ivshoucang.setBackgroundColor(getResources().getColor(R.color.white));
                    DeleteBookShelf();
                }
            }
        });
    }

    private void DeleteBookShelf() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String n = name;
                    String a = Login.userId;
                    Log.e("name:", n + "");
                    Log.e("userId:", a + "");
                    builder.add("bookkName", n);
                    builder.add("uuserId",a);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR + "DeleteBookShelfList")
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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setBookShelf() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String n = name;
                    String a = Login.userId;
                    Log.e("name:", n + "");
                    Log.e("userId:", a + "");
                    builder.add("bbbookName", n);
                    builder.add("uuuserId",a);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR + "SetBookShelfList")
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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void getBookReview() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    String n = name;
                    Log.e("name:",n+"");
                    builder.add("bookName", n);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
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
        bookRead = findViewById(R.id.ll_read);
        bookPinglun = findViewById(R.id.ll_pinglun);
        bookShoucang = findViewById(R.id.ll_shoucang);
        tvshoucang = findViewById(R.id.tv_shoucang);
        ivshoucang = findViewById(R.id.iv_shoucang);
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
    /**
     * 重新计算ListView的高度，解决ScrollView和ListView两个View都有滚动的效果，在嵌套使用时起冲突的问题
     * @param listView
     */
    public void setListViewHeight(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount            ()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }

}