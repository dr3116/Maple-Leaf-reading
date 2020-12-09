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
 * 杜然 12/6
 */

public class FensAdapter extends BaseAdapter {
    private Context context;
    private List<Fens> fensList = new ArrayList<>();
    private int itemLayout;
    public FensAdapter(Context context, List<Fens> fensList, int itemLayout){
        this.context = context;
        this.fensList = fensList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return fensList.size();
    }

    @Override
    public Object getItem(int position) {
        return fensList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(itemLayout,null);
        }
        ImageView userImg = convertView.findViewById(R.id.iv_fens_img);
        TextView userName = convertView.findViewById(R.id.tv_fens_name);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"img1/"+fensList.get(position).getUserPhoto())
                .apply(requestOptions)
                .into(userImg);
        userName.setText(fensList.get(position).getUserName()+"");
        return convertView;
    }


}
