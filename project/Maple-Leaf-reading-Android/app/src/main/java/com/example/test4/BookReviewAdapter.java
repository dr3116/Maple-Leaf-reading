package com.example.test4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 杜然 12/2
 */
public class BookReviewAdapter extends BaseAdapter {
    private Context context;
    private List<BookReview> bookReviewList = new ArrayList<>();
    private int itemLayout;
    public BookReviewAdapter(Context context, List<BookReview> bookReviewList, int itemLayout){
        this.context = context;
        this.bookReviewList = bookReviewList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return bookReviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookReviewList.get(position);
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
        ImageView bookReviewImg = convertView.findViewById(R.id.s_review_user_img);
        TextView bookReviewUserName = convertView.findViewById(R.id.s_review_user_name);
        TextView bookReviewUserContent = convertView.findViewById(R.id.s_review_user_content);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img1/"+bookReviewList.get(position).getUserId()+".jpg")
                .apply(requestOptions)
                .into(bookReviewImg);
        bookReviewUserName.setText(bookReviewList.get(position).getUserId()+"");
        bookReviewUserContent.setText(bookReviewList.get(position).getContent());
        return convertView;
    }
}
