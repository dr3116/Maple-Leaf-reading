package com.example.test4.main_fragment_all_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.test4.ClickFenLei10Activity;
import com.example.test4.ClickFenLei2Activity;
import com.example.test4.ClickFenLei3Activity;
import com.example.test4.ClickFenLei4Activity;
import com.example.test4.ClickFenLei5Activity;
import com.example.test4.ClickFenLei6Activity;
import com.example.test4.ClickFenLei7Activity;
import com.example.test4.ClickFenLei8Activity;
import com.example.test4.ClickFenLei9Activity;
import com.example.test4.ClickFenLeiActivity;
import com.example.test4.R;


public class SimpleFragment2 extends Fragment {
    private String str2;
    private int img;


    public SimpleFragment2() {

    }

    //作用，参数存入str2，方便后面调用
    public static SimpleFragment2 newInstance(String str1) {
        SimpleFragment2 simpleFragment2 = new SimpleFragment2();
        simpleFragment2.str2 = str1;
        return simpleFragment2;
    }

    public static SimpleFragment2 newInstance1(int str1) {
        SimpleFragment2 simpleFragment2 = new SimpleFragment2();
        simpleFragment2.img = str1;
        return simpleFragment2;
    }


    @Override//这个方法比上个方法后执行
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        View view = inflater.inflate(R.layout.fragment_fen_lei, container, false);
        /**
         * 周双文
         * 将下面的代码注释掉
         */
//        TextView textView= view.findViewById(R.id.textview);
//        textView.setText(str2);
//
//        ImageView imageView=view.findViewById(R.id.img1);
//        if(str2=="彭于晏"){
//            imageView.setImageResource(R.mipmap.a10);
//        }else if(str2=="街拍"){
//            imageView.setImageResource(R.mipmap.a20);
//        }else if(str2=="狗狗"){
//            imageView.setImageResource(R.mipmap.a30);
//        }else if(str2=="风景"){
//            imageView.setImageResource(R.mipmap.a40);
//        }
        //史恺伦
        //点击分类的点击事件，例如点击小程序跳转界面
        RelativeLayout relativeLayout=view.findViewById(R.id.s_qianduan_firstline_left);
        RelativeLayout relativeLayout2=view.findViewById(R.id.s_qianduan_firstline_right);
        RelativeLayout relativeLayout3=view.findViewById(R.id.s_qianduan_twiceline_left);
        RelativeLayout relativeLayout4=view.findViewById(R.id.s_qianduan_twiceline_right);
        RelativeLayout relativeLayout5=view.findViewById(R.id.s_qianduan_threadline_left);
        RelativeLayout relativeLayout6=view.findViewById(R.id.s_qianduan_threadline_right);
        RelativeLayout relativeLayout7=view.findViewById(R.id.s_else_1);
        RelativeLayout relativeLayout8=view.findViewById(R.id.s_else_2);
        RelativeLayout relativeLayout9=view.findViewById(R.id.s_else_3);
        RelativeLayout relativeLayout10=view.findViewById(R.id.s_else_4);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLeiActivity.class);
                startActivity(intent);

            }
        });
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei2Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei3Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei4Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei5Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei6Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei7Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei8Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei9Activity.class);
                startActivity(intent);

            }
        });
        relativeLayout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ClickFenLei10Activity.class);
                startActivity(intent);

            }
        });

        //iv.setImageResource(R.mipmap.yishenghuo2);
        return view;
    }
}