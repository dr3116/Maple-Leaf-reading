package com.example.test4;

import android.content.Context;
import android.content.Intent;
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
    private String userId;

    public BookShelfAdapter(Context context, List<BookShelfBook> bookShelfBooks, int itemLayout,String userId) {
        this.context = context;
        this.bookShelfBooks = bookShelfBooks;
        this.itemLayout = itemLayout;
        this.userId=userId;
    }

    @Override
    public int getCount() {
        int count=0;
        Log.e("bookshelfBooks长度：",bookShelfBooks.size()+"");
        if((bookShelfBooks.size()%3!=0)){
            count=(int)bookShelfBooks.size()/3+1;
        }else{
            count=(int)bookShelfBooks.size()/3;
        }
        Log.e("count=",""+count);
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
            Log.e("Book1设置成功","");
            MyListener myListener1=new MyListener((position)*3);
            bookImg1.setOnClickListener(myListener1);

        }
        if ((position)*3+1<=bookShelfBooks.size()-1) {
            bookName2.setText(bookShelfBooks.get((position)*3+1).getBookName());
            Glide.with(context)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookShelfBooks.get((position)*3+1).getBookImg())
                    .apply(requestOptions)
                    .into(bookImg2);
            Log.e("Book2设置成功","");
            MyListener myListener2=new MyListener((position)*3+1);
            bookImg2.setOnClickListener(myListener2);

        }
        if ((position)*3+2<=bookShelfBooks.size()-1) {
            bookName3.setText(bookShelfBooks.get((position)*3+2).getBookName());
            Glide.with(context)
                    .load(ConfigUtil.SERVER_ADDR+"img/"+bookShelfBooks.get((position)*3+2).getBookImg())
                    .apply(requestOptions)
                    .into(bookImg3);
            Log.e("Book3设置成功","");
            MyListener myListener3=new MyListener((position)*3+2);
            bookImg3.setOnClickListener(myListener3);
        }
        return convertView;
    }
    private class MyListener implements View.OnClickListener{
        private final int truePosition;

        public MyListener(int truePosition) {
            this.truePosition = truePosition;
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(context,FileDownLoad.class);
            intent.putExtra("userId",userId);
            intent.putExtra("bookName",bookShelfBooks.get(truePosition).getBookName());
            context.startActivity(intent);
        }
    }
}
