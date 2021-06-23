package com.example.test4;

import android.content.Context;
import android.util.Log;
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

public class BookShowAdapter extends BaseAdapter {
    private Context context;
    private List<BookCategory> bookCategories=new ArrayList<>();
    private int itemLayout;

    public BookShowAdapter(Context context, List<BookCategory> bookCategories, int itemLayout) {
        this.context = context;
        this.bookCategories = bookCategories;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        return bookCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return bookCategories.get(position);
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
        ImageView bookImg=convertView.findViewById(R.id.s_first_qianduan_img);
        TextView bookName=convertView.findViewById(R.id.s_first_qianduan_bookname);
        TextView yueduliang=convertView.findViewById(R.id.s_first_qianduan_yueduliang);
        TextView zhangjieshu=convertView.findViewById(R.id.s_first_qianduan_zhangjieshu);
        TextView bookIntroduction=convertView.findViewById(R.id.s_first_qianduan_info);

        bookName.setText(bookCategories.get(position).getBookName());
        yueduliang.setText(String.valueOf(bookCategories.get(position).getReadingVolume()));
        zhangjieshu.setText(String.valueOf(bookCategories.get(position).getChapterNum()));
        bookIntroduction.setText(bookCategories.get(position).getIntroduction());


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);

        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"images/book/"+bookCategories.get(position).getBookPhoto())
                .apply(requestOptions)
                .into(bookImg);
//                Log.e("BookShowAdapter.java:","图片请求路径为："+ConfigUtil.SERVER_ADDR+"images/book/"+bookCategories.get(position).getBookPhoto());
        return convertView;

    }
}
