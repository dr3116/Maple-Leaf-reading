package com.example.test4;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

public class HolderView implements Holder<Integer> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView=new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }



    /**
     * 这里要注意传的是字符串还是int型的
     * @param context
     * @param position
     * @param data
     */
    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setImageResource(data);
        //Glide.with(context).load(data).into(imageView);
    }
}
