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

public class BookShelfAdapter extends BaseAdapter {
    private Context context;
    private List<BookShelfBook> bookShelfBooks=new ArrayList<>();
    private int itemLayout;

    public BookShelfAdapter(Context context, List<BookShelfBook> bookShelfBooks, int itemLayout) {
        this.context = context;
        this.bookShelfBooks = bookShelfBooks;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getCount() {
        int count=0;
        if((bookShelfBooks.size()%3!=0)){
            count=(int)bookShelfBooks.size()+1;
        }else{
            count=(int)bookShelfBooks.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        TextView bookName1=convertView.findViewById(R.id.z_book_name1);
        TextView bookName2=convertView.findViewById(R.id.z_book_name2);
        TextView bookName3=convertView.findViewById(R.id.z_book_name3);
        ImageView bookImg1=convertView.findViewById(R.id.z_book_img1);
        ImageView bookImg2=convertView.findViewById(R.id.z_book_img2);
        ImageView bookImg3=convertView.findViewById(R.id.z_book_img3);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Log.e("position",""+position);
        if ((position)*3<=bookShelfBooks.size()-1) {
            bookName1.setText(bookShelfBooks.get((position)*3).getBookName());
            Glide.with(context)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookShelfBooks.get((position)*3).getBookImg())
                    .apply(requestOptions)
                    .into(bookImg1);
        }
        if ((position)*3+1<=bookShelfBooks.size()-1) {
            bookName2.setText(bookShelfBooks.get((position)*3+1).getBookName());
            Glide.with(context)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookShelfBooks.get((position)*3+1).getBookImg())
                    .apply(requestOptions)
                    .into(bookImg2);
        }
        if ((position)*3+2<=bookShelfBooks.size()-1) {
            bookName3.setText(bookShelfBooks.get((position)*3+2).getBookName());
            Glide.with(context)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookShelfBooks.get((position)*3+2).getBookImg())
                    .apply(requestOptions)
                    .into(bookImg3);
        }
        return convertView;
    }
}
