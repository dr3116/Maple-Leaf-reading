package com.example.test4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

    public PostAdapter(Context context, List<Post> postList, int itemLayout) {
        this.context = context;
        this.postList = postList;
        this.itemLayout = itemLayout;
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
        MyListener myListener=new MyListener();
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
        likesImg.setOnClickListener(myListener);
        likesNum.setText(String.valueOf(postList.get(position).getNumberOfLikes()));
        forward.setOnClickListener(myListener);
        return convertView;
    }

    /**
     * postItem监听器
     */
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.menu_img:
                    break;
                case R.id.com_img:
                    break;
                case R.id.likes_img:
                    break;
            }
        }
    }
}
