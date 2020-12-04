package com.example.test4.main_fragment_all_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.test4.AboutUs;
import com.example.test4.R;
import com.example.test4.search.Search;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleFragment5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleFragment5 extends Fragment {

    private String str2;
    private int img;
    private LinearLayout search;
    private LinearLayout about;



    public SimpleFragment5() {


    }



    //作用，参数存入str2，方便后面调用
    public static SimpleFragment5 newInstance(String str1) {
        SimpleFragment5 simpleFragment5 = new SimpleFragment5();
        simpleFragment5.str2 = str1;
        return simpleFragment5;
    }




    @Override//这个方法比上个方法后执行
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        /**
         * 杜然 增加点击事件
         */
        View view = inflater.inflate(R.layout.activity_mine, container, false);
        search= view.findViewById(R.id.ll_search);
        about = view.findViewById(R.id.ll_about_us);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Search.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutUs.class);
                startActivity(intent);
            }
        });
        return view;
    }
}