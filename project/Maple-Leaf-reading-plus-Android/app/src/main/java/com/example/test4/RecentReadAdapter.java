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
 * 12/7 杜然
 */
public class RecentReadAdapter extends BaseAdapter {
    private Context context;
    private List<RecentRead> recentReadList = new ArrayList<>();
    private int itemLayout;
    public RecentReadAdapter(Context context, List<RecentRead> recentReadList, int itemLayout){
        this.context = context;
        this.recentReadList = recentReadList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return recentReadList.size();
    }

    @Override
    public Object getItem(int position) {
        return recentReadList.get(position);
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
        ImageView bookPhoto = convertView.findViewById(R.id.iv_recent_photo);
        TextView bookName = convertView.findViewById(R.id.tv_recent_bookName);
        TextView readingVolume = convertView.findViewById(R.id.tv_recent_readingVolume);
        TextView numberOfChapters = convertView.findViewById(R.id.tv_recent_menu);
        TextView lastReadingTime = convertView.findViewById(R.id.tv_recent_time);
        TextView briefIntroduction = convertView.findViewById(R.id.tv_recent_info);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"images/book/"+recentReadList.get(position).getBookPhoto())
                .apply(requestOptions)
                .into(bookPhoto);
        bookName.setText(recentReadList.get(position).getBookName());
        readingVolume.setText(recentReadList.get(position).getReadingVolume()+"");
        numberOfChapters.setText(recentReadList.get(position).getNumberOfChapters()+"");;
        lastReadingTime.setText(recentReadList.get(position).getLastReadingTime()+"");
        briefIntroduction.setText(recentReadList.get(position).getBriefIntroduction());
        return convertView;
    }
}
