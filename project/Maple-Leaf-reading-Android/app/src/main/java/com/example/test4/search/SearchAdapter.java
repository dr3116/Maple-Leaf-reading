package com.example.test4.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.test4.ConfigUtil;
import com.example.test4.R;
import com.example.test4.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * 杜然 12/1
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList = new ArrayList<>();
    private int itemLayout;
    public SearchAdapter(Context context, List<Book> bookList, int itemLayout){
        this.context = context;
        this.bookList = bookList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
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
        ImageView img = convertView.findViewById(R.id.iv_search_photo);
        TextView bookName = convertView.findViewById(R.id.tv_search_bookName);
        TextView readingVolume = convertView.findViewById(R.id.tv_search_readingVolume);
        TextView numberOfChapters = convertView.findViewById(R.id.tv_search_menu);
        TextView releaseTime = convertView.findViewById(R.id.tv_search_time);
        TextView briefIntroduction = convertView.findViewById(R.id.tv_search_info);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img/"+bookList.get(position).getBookPhoto())
                .apply(requestOptions)
                .into(img);
        bookName.setText(bookList.get(position).getBookName());
        readingVolume.setText(bookList.get(position).getReadingVolume()+"");
        numberOfChapters.setText(bookList.get(position).getNumberOfChapters()+"");
        releaseTime.setText(bookList.get(position).getReleaseTime());
        briefIntroduction.setText(bookList.get(position).getBriefIntroduction());
        return convertView;
    }
}
