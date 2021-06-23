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

public class SecondFragmentAdapter extends BaseAdapter {
    private Context context;
    private List<CollectionPaihang> collectionPaihangList = new ArrayList<>();
    private int itemLayout;
    public SecondFragmentAdapter(Context context, List<CollectionPaihang> collectionPaihangList, int itemLayout){
        this.context = context;
        this.collectionPaihangList = collectionPaihangList;
        this.itemLayout = itemLayout;
    }
    @Override
    public int getCount() {
        return collectionPaihangList.size();
    }

    @Override
    public Object getItem(int position) {
        return collectionPaihangList.get(position);
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
                .load(ConfigUtil.SERVER_ADDR+"images/book/"+collectionPaihangList.get(position).getBookPhoto())
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
        name.setText(collectionPaihangList.get(position).getBookName());
        number.setText(collectionPaihangList.get(position).getNumberOfCollections()+"");
        return convertView;
    }
}
