package com.example.test4.Fragment;

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

import java.util.ArrayList;
import java.util.List;

public class ThirdFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<ReadingVolumePaihang> readingVolumePaihangList = new ArrayList<>();
    private int itemLayout;
    public ThirdFragmentAdapter(Context context, List<ReadingVolumePaihang> readingVolumePaihangList, int itemLayout){
        this.context = context;
        this.readingVolumePaihangList = readingVolumePaihangList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return readingVolumePaihangList.size();
    }

    @Override
    public Object getItem(int position) {
        return readingVolumePaihangList.get(position);
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
        TextView paiMing = convertView.findViewById(R.id.tv_paiming);
        TextView name = convertView.findViewById(R.id.tv_name);
        TextView number = convertView.findViewById(R.id.tv_number);
        ImageView img = convertView.findViewById(R.id.iv_picture);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.loding)
                .error(R.mipmap.error)
                .fallback(R.mipmap.loding);
        Glide.with(context)
                .load(ConfigUtil.SERVER_ADDR+"images/book/"+readingVolumePaihangList.get(position).getBookPhoto())
                .apply(requestOptions)
                .into(img);
        paiMing.setText(position+1+"");
        if(position==0){
            paiMing.setBackgroundColor(context.getResources().getColor(R.color.red));
        }
        else if(position ==1){
            paiMing.setBackgroundColor(context.getResources().getColor(R.color.gold));
        }
        else if(position ==2){
            paiMing.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }
        else if(position>2){
            paiMing.setBackgroundColor(context.getResources().getColor(R.color.gray91));
        }
        name.setText(readingVolumePaihangList.get(position).getBookName());
        number.setText(readingVolumePaihangList.get(position).getReadingVolume()+"");
        return convertView;
    }
}
