package com.example.test4.main_fragment_all_5;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test4.BookCategory;
import com.example.test4.BookShowAdapter;
import com.example.test4.ClickFenLei2Activity;
import com.example.test4.ClickFenLeiActivity;
import com.example.test4.Comment;
import com.example.test4.ConfigUtil;
import com.example.test4.HolderView;
import com.example.test4.Post;
import com.example.test4.PostAdapter;
import com.example.test4.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.test4.book.Book;
import com.example.test4.book.BookIntroduction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.AEADBadTagException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleFragment1 extends Fragment implements OnItemClickListener{

    private String str2;
    private int img;
    private ListView qianduanList;
    private ListView houduanList;
    private ListView yidongduanList;
    private ListView shujukuList;
    private ListView yunjishuanList;
    private ListView qitaList;
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
                Log.e("设置listView","成功");
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
        BookShowAdapter bookShowAdapter1=new BookShowAdapter(getContext(),qianduans,R.layout.shouye_item_layout);
        qianduanList.setAdapter(bookShowAdapter1);
        BookShowAdapter bookShowAdapter2=new BookShowAdapter(getContext(),houduans,R.layout.shouye_item_layout);
        houduanList.setAdapter(bookShowAdapter2);
        BookShowAdapter bookShowAdapter3=new BookShowAdapter(getContext(),yidongduans,R.layout.shouye_item_layout);
        yidongduanList.setAdapter(bookShowAdapter3);
        BookShowAdapter bookShowAdapter4=new BookShowAdapter(getContext(),shujukus,R.layout.shouye_item_layout);
        shujukuList.setAdapter(bookShowAdapter4);
        BookShowAdapter bookShowAdapter5=new BookShowAdapter(getContext(),yunjishuans,R.layout.shouye_item_layout);
        yunjishuanList.setAdapter(bookShowAdapter5);
        BookShowAdapter bookShowAdapter6=new BookShowAdapter(getContext(),qitas,R.layout.shouye_item_layout);
        qitaList.setAdapter(bookShowAdapter6);
//        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent();
//                intent.setClass(getContext(), Comment.class);
//                intent.putExtra("post",posts.get(position));
//                startActivity(intent);
//            }
//        });
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
                            .url(ConfigUtil.SERVER_ADDR+"GetBookCategory")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    categories = gson.fromJson(responseData, new TypeToken<List<BookCategory>>(){}.getType());
                    //将获取的所有类的图书进行拆分
                    for (BookCategory item:categories){
                        if (item.getCategory().equals("前端")){
                            qianduans.add(item);
                        }else if (item.getCategory().equals("后端")){
                            houduans.add(item);
                        }else if (item.getCategory().equals("移动端")){
                            yidongduans.add(item);
                        }else if (item.getCategory().equals("数据库")){
                            shujukus.add(item);
                        }else if (item.getCategory().equals("云计算&大数据")){
                            yunjishuans.add(item);
                        }else {
                            qitas.add(item);
                        }
                    }
                    Log.e("前端数据量：",""+qianduans.size());
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
    private void downLoadBookInfo(final String bookName){
        new Thread() {
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
                            .url(ConfigUtil.SERVER_ADDR+"GetOneBookInfo")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
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
        intent.setClass(getContext(), BookIntroduction.class);
        intent.putExtra("bookName",books.get(0).getBookName());
        intent.putExtra("readingVolume",books.get(0).getReadingVolume()+"");
        intent.putExtra("numberOfChapters",books.get(0).getNumberOfChapters()+"");
        intent.putExtra("bookRating",books.get(0).getBookRating()+"");
        intent.putExtra("author",books.get(0).getAuthor());
        intent.putExtra("numberOfCollections",books.get(0).getNumberOfCollections()+"");
        intent.putExtra("briefIntroduction",books.get(0).getBriefIntroduction());
        intent.putExtra("bookPhoto",books.get(0).getBookPhoto());

        startActivity(intent);
    }

}


