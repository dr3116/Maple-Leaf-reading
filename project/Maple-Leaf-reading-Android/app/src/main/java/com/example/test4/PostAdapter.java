package com.example.test4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
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
    private String userId;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage( Message msg) {
            if(msg.what==1){
                Toast.makeText(context,"你已经收藏过了",Toast.LENGTH_LONG).show();
            }else if (msg.what==2){
                Toast.makeText(context,"收藏成功",Toast.LENGTH_LONG).show();
            }
        }
    };

    public PostAdapter(Context context, List<Post> postList, int itemLayout,List<Integer> ImgInAdress,String userId) {
        this.context = context;
        this.postList = postList;
        this.itemLayout = itemLayout;
        this.ImgInAdress=ImgInAdress;
        this.userId=userId;
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

        Bitmap head= drawableToBitmap(postImg.getDrawable());

        return convertView;
    }

    /**
     * 将Drawble对象转换为BitMap对象
     * @param drawable
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap( drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
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
                    Intent intent=new Intent();
                    intent.setClass(context,Comment.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("post",postList.get(position));
                    context.startActivity(intent);
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
                case R.id.forward:
                    Intent intent2=new Intent();
                    intent2.setClass(context,AddPost.class);
                    intent2.putExtra("input",postList.get(position).getContent());
                    intent2.putExtra("userId",userId);
                    intent2.putExtra("headStr",postList.get(position).getPhoto());
                    intent2.putExtra("bookPhoto",postList.get(position).getBookImg());
                    intent2.putExtra("bookName",postList.get(position).getBookName());
                    intent2.putExtra("author",postList.get(position).getBookAuthor());
                    context.startActivity(intent2);
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
    //周双文，这里进行菜单处理
    private int showPopupMenu(final View view, final int mark) {
        final PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.post_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.attention:
                        addCollect(mark);
                        break;
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
    private void addCollect(final int position){
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("userId",userId);
                    builder.add("postId",postList.get(position).getPostId()+"");
                    FormBody body = builder.build();
                    Request request = new Request.Builder()
                            // 指定访问的服务器地址
                            .post(body)
                            .url(ConfigUtil.SERVER_ADDR+"UpAttentionInfo")
                            .build();
                    Response response = client.newCall(request).execute();
                    //获得json字符串
                    String responseData = response.body().string();
                    Log.e("从服务端返回的json:",responseData);
                    //解析字符串
                    Gson gson = new GsonBuilder()
                            .create(); //生成配置好的Gson
                    String attentionInfo=gson.fromJson(responseData,String.class);
                    if (attentionInfo!=null && attentionInfo.equals("error")){
                        Message msg = new Message();
                        //设置Message对象的参数
                        msg.what = 1;
                        //发送Message
                        handler.sendMessage(msg);
                    }else {
                        Message msg = new Message();
                        //设置Message对象的参数
                        msg.what = 2;
                        //发送Message
                        handler.sendMessage(msg);
                    }

                    Log.e("增加收藏","结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
