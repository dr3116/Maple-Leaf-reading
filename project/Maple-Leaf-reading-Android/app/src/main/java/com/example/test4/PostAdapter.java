package com.example.test4;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test4.book.Book;
import com.example.test4.book.BookIntroduction;
import com.example.test4.main_fragment_all_5.SimpleFragment1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:周双文
 * time:2020/11/28
 *
 * 发出的帖子布局的Adapter
 */
public class PostAdapter extends BaseAdapter {
    private Context context;
    private List<Post> postList=new ArrayList<>();
    private int itemLayout;
    private List<Book> books=new ArrayList<>();
    private List<Integer> ImgInAdress=new ArrayList<>();

    public PostAdapter(Context context, List<Post> postList, int itemLayout,List<Integer> ImgInAdress) {
        this.context = context;
        this.postList = postList;
        this.itemLayout = itemLayout;
        this.ImgInAdress=ImgInAdress;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
        }
        CircleImageView posterPhoto=convertView.findViewById(R.id.poster_img);
        TextView posterName=convertView.findViewById(R.id.poster_name);
        ImageView menu=convertView.findViewById(R.id.menu_img);
        TextView postContent=convertView.findViewById(R.id.post_text);
        ImageView postImg=convertView.findViewById(R.id.post_img);
        LinearLayout bookInfo=convertView.findViewById(R.id.bookInfo);
        ImageView bookImg=convertView.findViewById(R.id.z_book_img);
        TextView bookName=convertView.findViewById(R.id.z_book_name);
        TextView bookAuthor=convertView.findViewById(R.id.z_book_author);
        TextView postTime=convertView.findViewById(R.id.post_time);
        ImageView commentImg=convertView.findViewById(R.id.com_img);
        TextView commentNum=convertView.findViewById(R.id.com_num);
        ImageView likesImg=convertView.findViewById(R.id.likes_img);
        TextView likesNum=convertView.findViewById(R.id.likes_num);
        ImageView forward=convertView.findViewById(R.id.forward);


        //设置监听器和属性
        MyListener myListener=new MyListener(position);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);

        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"imgs/"+postList.get(position).getPhoto())
                .apply(requestOptions)
                .into(postImg);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img1/"+postList.get(position).getUserId()+".jpg")
                .apply(requestOptions)
                .into(posterPhoto);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img/"+postList.get(position).getBookImg())
                .apply(requestOptions)
                .into(bookImg);
        posterName.setText(postList.get(position).getUserName());
        menu.setOnClickListener(myListener);
        postContent.setText(postList.get(position).getContent());
        bookInfo.setOnClickListener(myListener);
        bookName.setText(postList.get(position).getBookName());
        bookAuthor.setText(postList.get(position).getBookAuthor());
        postTime.setText(postList.get(position).getPostTime());
        commentImg.setOnClickListener(myListener);
        commentNum.setText(String.valueOf(postList.get(position).getComments()));
        likesImg.setImageResource(ImgInAdress.get(position));
        likesImg.setOnClickListener(myListener);
        likesNum.setText(String.valueOf(postList.get(position).getNumberOfLikes()));
        forward.setOnClickListener(myListener);
        return convertView;
    }

    /**
     * postItem监听器
     */
    private class MyListener implements View.OnClickListener{
        private int position;
        public MyListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu_img:
                    int s=showPopupMenu(v,position);
                    break;
                case R.id.com_img:
                    break;
                case R.id.likes_img:
                    if (ImgInAdress.get(position)==R.drawable.likes){
                        ImgInAdress.set(position,R.drawable.liked);
                        postList.get(position).setNumberOfLikes(postList.get(position).getNumberOfLikes()+1);
                    }else if (ImgInAdress.get(position)==R.drawable.liked){
                        ImgInAdress.set(position,R.drawable.likes);
                    }
                    notifyDataSetChanged();
                    break;
                case R.id.bookInfo:
                    Log.e("点击了书本信息","触发");
                    downLoadBookInfo(postList.get(position).getBookName());
                    break;
            }
        }
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
        intent.setClass(context, BookIntroduction.class);
        intent.putExtra("bookName",books.get(0).getBookName());
        intent.putExtra("readingVolume",books.get(0).getReadingVolume()+"");
        intent.putExtra("numberOfChapters",books.get(0).getNumberOfChapters()+"");
        intent.putExtra("bookRating",books.get(0).getBookRating()+"");
        intent.putExtra("author",books.get(0).getAuthor());
        intent.putExtra("numberOfCollections",books.get(0).getNumberOfCollections()+"");
        intent.putExtra("briefIntroduction",books.get(0).getBriefIntroduction());
        intent.putExtra("bookPhoto",books.get(0).getBookPhoto());
        context.startActivity(intent);
    }
    private int showPopupMenu(final View view, final int mark) {
        final PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.post_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
//                    case R.id.attention:
//
//                        try {
//                            de.join();
//                            Intent intent1=new Intent();
//                            intent1.setClass(context,userCheckOrder.class);
//                            context.startActivity(intent1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        break;
                    case R.id.report:
                        Log.e("点击举报","成功");
                        showChiledPopupMenu(view,mark);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
        return 1;
    }
    private int showChiledPopupMenu(View view, final int mark) {
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.post_menu_child,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.seqing:
                        Toast.makeText(context,"举报成功",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.weifa:
                        Toast.makeText(context,"举报成功",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.guangao:
                        Toast.makeText(context,"举报成功",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.qita:
                        Toast.makeText(context,"举报成功",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();

        return 1;
    }

}
