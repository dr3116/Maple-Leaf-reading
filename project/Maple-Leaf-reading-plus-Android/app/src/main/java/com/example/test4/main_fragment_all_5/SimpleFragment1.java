package com.example.test4.main_fragment_all_5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.test4.BookCategory;
import com.example.test4.BookShowAdapter;
import com.example.test4.ConfigUtil;
import com.example.test4.HolderView;
import com.example.test4.R;
import com.example.test4.book.Book;
import com.example.test4.book.BookIntroduction;
import com.example.test4.search.Search;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleFragment1 extends Fragment implements OnItemClickListener{

    private String str2;
    private int img;
    private ImageView search;

    private RelativeLayout one;
    private RelativeLayout two;
    private RelativeLayout three;
    private RelativeLayout four;
    private RelativeLayout five;
    private ImageView share;
    private ListView qianduanList;
    private ListView houduanList;
    private ListView yidongduanList;
    private ListView shujukuList;
    private ListView yunjishuanList;
    private ListView qitaList;
    private String userId;
    private String userName;
    private TextView moreQianduan;
    private TextView moreHouduan;
    private TextView moreYidongduan;
    private TextView moreShuJuku;
    private TextView moreYunJiSuan;
    private TextView moreQita;

    private List<BookCategory> categories=new ArrayList<>();
    private List<BookCategory> qianduans=new ArrayList<>();
    private List<BookCategory> houduans=new ArrayList<>();
    private List<BookCategory> yidongduans=new ArrayList<>();
    private List<BookCategory> shujukus=new ArrayList<>();
    private List<BookCategory> yunjishuans=new ArrayList<>();
    private List<BookCategory> qitas=new ArrayList<>();
    private ConvenientBanner convenientBanner;
    private List<Book> books = new ArrayList<Book>();
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
//                Log.e("设置listView","成功");
                setListView();
                setListener();

            }
        }
    };
    public SimpleFragment1() {

    }

    //作用，参数存入str2，方便后面调用
    public static SimpleFragment1 newInstance(String str1){
        SimpleFragment1 simpleFragment=new SimpleFragment1();
        simpleFragment.str2=str1;
        return simpleFragment;
    }
    public static SimpleFragment1 newInstance1(int str1){
        SimpleFragment1 simpleFragment=new SimpleFragment1();
        simpleFragment.img=str1;
        return simpleFragment;
    }



    private void setListView(){
        BookShowAdapter bookShowAdapter1=new BookShowAdapter(getContext(),qianduans, R.layout.shouye_item_layout);
        qianduanList.setAdapter(bookShowAdapter1);
        BookShowAdapter bookShowAdapter2=new BookShowAdapter(getContext(),houduans, R.layout.shouye_item_layout);
        houduanList.setAdapter(bookShowAdapter2);
        BookShowAdapter bookShowAdapter3=new BookShowAdapter(getContext(),yidongduans, R.layout.shouye_item_layout);
        yidongduanList.setAdapter(bookShowAdapter3);
        BookShowAdapter bookShowAdapter4=new BookShowAdapter(getContext(),shujukus, R.layout.shouye_item_layout);
        shujukuList.setAdapter(bookShowAdapter4);
        BookShowAdapter bookShowAdapter5=new BookShowAdapter(getContext(),yunjishuans, R.layout.shouye_item_layout);
        yunjishuanList.setAdapter(bookShowAdapter5);
        BookShowAdapter bookShowAdapter6=new BookShowAdapter(getContext(),qitas, R.layout.shouye_item_layout);
        qitaList.setAdapter(bookShowAdapter6);




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
    //获取post的字符信息
    private void getBookCategory() {
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetBookCategory")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("SimpleFragment1.java:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    categories = gson.fromJson(responseData, new TypeToken<List<BookCategory>>(){}.getType());
                    //将获取的所有类的图书进行拆分
                    for (BookCategory item:categories){
//                        Log.e("SimpleFragment1.java:",item.toString());
                        if (item.getCategory().equals("小程序") || item.getCategory().equals("JavaScript") || item.getCategory().equals("H5小游戏") || item.getCategory().equals("HTML5")){
                            qianduans.add(item);
                        }else if (item.getCategory().equals("C/C++") || item.getCategory().equals("测试") || item.getCategory().equals("Python") || item.getCategory().equals("Java")){
                            houduans.add(item);
                        }else if (item.getCategory().equals("Android")){
                            yidongduans.add(item);
                        }else if (item.getCategory().equals("MySQL")){
                            shujukus.add(item);
                        }else if (item.getCategory().equals("机器学习") || item.getCategory().equals("深度学习")){
                            yunjishuans.add(item);
                        }else {
                            qitas.add(item);
                        }
                    }
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

    @Override//这个方法比上个方法后执行````````````````````````
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        View view=inflater.inflate(R.layout.fragment_shouye, container, false);

        search = view.findViewById(R.id.s_main_sousuo);

        if (getArguments()!=null){
            userId=getArguments().getString("userId");
            userName=getArguments().getString("userName");
        }

        one = view.findViewById(R.id.r1);
        two = view.findViewById(R.id.r2);
        three = view.findViewById(R.id.r3);
        four = view.findViewById(R.id.r4);
        five = view.findViewById(R.id.r5);
        share=view.findViewById(R.id.s_main_share);

        moreQianduan=view.findViewById(R.id.s_more_qianduan);
        moreHouduan=view.findViewById(R.id.s_more_houduan);
        moreYidongduan=view.findViewById(R.id.s_more_yidongduan);
        moreShuJuku=view.findViewById(R.id.s_more_shujuku);
        moreYunJiSuan=view.findViewById(R.id.s_more_dashuju);
        moreQita=view.findViewById(R.id.s_more_qita);


        moreQianduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了更多前端","开始");
                setListViewHeight(qianduanList);
            }
        });
        moreHouduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了更多后端","开始");
                setListViewHeight(houduanList);
            }
        });
        moreYidongduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("点击了更多移动端","开始");
                setListViewHeight(yidongduanList);
            }
        });
        moreShuJuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了更多数据库","开始");
                setListViewHeight(shujukuList);
            }
        });
        moreYunJiSuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了更多云计算","开始");
                setListViewHeight(yunjishuanList);
            }
        });
        moreQita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("点击了更多其他","开始");
                setListViewHeight(qitaList);
            }
        });

        qianduanList=view.findViewById(R.id.qianduan);
        houduanList=view.findViewById(R.id.houduan);
        yidongduanList=view.findViewById(R.id.yidongdaun);
        shujukuList=view.findViewById(R.id.shujuku);
        yunjishuanList=view.findViewById(R.id.yunjishuan);
        qitaList=view.findViewById(R.id.qita);
        convenientBanner = view.findViewById(R.id.s_banner);

        if(localImages.size()==0) {
            localImages.add(R.drawable.lunbo1);
            localImages.add(R.drawable.lunbo2);
            localImages.add(R.drawable.lunbo3);
        }


        /**
         * 周双文，添加分享电点击事件
         */
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allShare();
            }
        });



        /**
         * 杜然 12/7 添加推荐的点击事件
         */
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                intent.putExtra("userId",userId);

                startActivity(intent);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookIntroduction.class);
                intent.putExtra("userId",userId);
                intent.putExtra("bookName","Android Studio 用户指南");
                intent.putExtra("readingVolume","53004");
                intent.putExtra("numberOfChapters","130");
                intent.putExtra("bookRating","4.00");
                intent.putExtra("author","进击的皇虫");
                intent.putExtra("numberOfCollections","122");
                intent.putExtra("briefIntroduction","Android Studio 是基于IntelliJ IDEA的官方Android应用开发集成开发环境（IDE)。除了InterlliJ强大的代码编译器和开发者工具，Android Studio提供了更多可提高Android应用的构建效率的功能");
                intent.putExtra("bookPhoto","2.jpg");
                startActivity(intent);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookIntroduction.class);
                intent.putExtra("userId",userId);
                intent.putExtra("bookName","Python - 100天从新手到大师");
                intent.putExtra("readingVolume","118744");
                intent.putExtra("numberOfChapters","193");
                intent.putExtra("bookRating","4.00");
                intent.putExtra("author","进击的皇虫");
                intent.putExtra("numberOfCollections","1000");
                intent.putExtra("briefIntroduction","Python是一个“优雅”、“明确”、“简单”的编程语言");
                intent.putExtra("bookPhoto","15.jpg");
                startActivity(intent);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookIntroduction.class);
                intent.putExtra("userId",userId);
                intent.putExtra("bookName","技术面试需要掌握的基础知识整理");
                intent.putExtra("readingVolume","69940");
                intent.putExtra("numberOfChapters","29");
                intent.putExtra("bookRating","4.70");
                intent.putExtra("author","进击的皇虫");
                intent.putExtra("numberOfCollections","772");
                intent.putExtra("briefIntroduction","本仓库是笔者在准备2018春招实习过程中的学习总结，内容以计算机书籍的学习笔记为主，在整理重点知识的同时会尽量保证知识的系统性");
                intent.putExtra("bookPhoto","32.jpg");
                startActivity(intent);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookIntroduction.class);
                intent.putExtra("userId",userId);
                intent.putExtra("bookName","奋斗");
                intent.putExtra("readingVolume","513004");
                intent.putExtra("numberOfChapters","30");
                intent.putExtra("bookRating","5.00");
                intent.putExtra("author","进击的皇虫");
                intent.putExtra("numberOfCollections","1000");
                intent.putExtra("briefIntroduction","奋斗，奋斗，奋斗，重要的事情说三遍");
                intent.putExtra("bookPhoto","22.jpg");
                startActivity(intent);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookIntroduction.class);
                intent.putExtra("userId",userId);
                intent.putExtra("bookName","Python 网络爬虫教程");
                intent.putExtra("readingVolume","44739");
                intent.putExtra("numberOfChapters","122");
                intent.putExtra("bookRating","4.00");
                intent.putExtra("author","进击的皇虫");
                intent.putExtra("numberOfCollections","372");
                intent.putExtra("briefIntroduction","使用Python实现网络爬虫教程，教你如何抓取内容。");
                intent.putExtra("bookPhoto","16.jpg");
                startActivity(intent);
            }
        });
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new HolderView();
            }
        }, localImages)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.point1, R.drawable.point})
                //设置指示器的方向（左、中、右）
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置点击监听事件
                .setOnItemClickListener(this)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);

        getBookCategory();
        return view;
    }
    //轮播图点击事件
    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "position:" + position, Toast.LENGTH_SHORT).show();
        String arr[] = {"https://account.huaweicloud.com/obmgr/invitation/invitation.html","https://www.aliyun.com/activity/daily/bestoffer","https://cloud.tencent.com/act"};
        Uri uri = Uri.parse(arr[position]);//要跳转的网址
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void setListener(){
        qianduanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(qianduans.get(position).getBookName());
            }
        });
        houduanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(houduans.get(position).getBookName());
            }
        });
        yidongduanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(yidongduans.get(position).getBookName());
            }
        });
        shujukuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(shujukus.get(position).getBookName());
            }
        });
        yunjishuanList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(yunjishuans.get(position).getBookName());
            }
        });
        qitaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                downLoadBookInfo(qitas.get(position).getBookName());
            }
        });
    }
    /**
     * Android原生分享功能
     * 默认选取手机所有可以分享的APP
     */
    public void allShare(){
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "share");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, "枫悦阅读来啦！"+"\n"+"点击下面的链接下载吧！"+"\n"+"https://github.com/dr3116/Maple-Leaf-reading");//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "share");
        startActivity(share_intent);
    }
    private void downLoadBookInfo(final String bookName){
        new Thread() {
            @SuppressLint("LongLogTag")
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("bookName",bookName);
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"APP/GetOneBookInfo")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
//                    Log.e("SimpleFragment1从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    books = gson.fromJson(responseData, new TypeToken<List<Book>>(){}.getType());
                    intentToIntroduction();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void intentToIntroduction(){
        Intent intent=new Intent();
        intent.putExtra("userId",userId);
        intent.setClass(getContext(), BookIntroduction.class);
        intent.putExtra("bookName",books.get(0).getBookName());
        intent.putExtra("readingVolume",books.get(0).getReadingVolume()+"");
        intent.putExtra("numberOfChapters",books.get(0).getNumberOfChapters()+"");
        intent.putExtra("bookRating",books.get(0).getBookRating()+"");
        intent.putExtra("author",books.get(0).getAuthor());
        intent.putExtra("numberOfCollections",books.get(0).getNumberOfCollections()+"");
        intent.putExtra("briefIntroduction",books.get(0).getBriefIntroduction());
        intent.putExtra("bookPhoto",books.get(0).getBookPhoto());

        Log.e("开始从SimpleFragment1","跳转到BookInteroduction");
        startActivity(intent);
        Log.e("从SimpleFragment1","跳转到BookInteroduction跳转结束");
    }

}


