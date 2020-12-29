package com.example.test4.main_fragment_all_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.test4.AboutUs;
import com.example.test4.FensActivity;
import com.example.test4.Login;
import com.example.test4.MyCollection;
import com.example.test4.MyPost;
import com.example.test4.PaihangActivity;
import com.example.test4.R;
import com.example.test4.RecentReadActivity;
import com.example.test4.SignDate;
import com.example.test4.SignToday;
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
    private LinearLayout fens;
    private LinearLayout recentReading;
    private TextView signIn;
    private String userId;
    private String nameStr;
    private String passWordStr;
    private TextView paihang;
    private LinearLayout myCollection;
    private LinearLayout myPost;
    private LinearLayout loginOut;

    public SimpleFragment5() {


    }



    //作用，参数存入str2，方便后面调用
    public static SimpleFragment5 newInstance(String str1) {
        SimpleFragment5 simpleFragment5 = new SimpleFragment5();
        simpleFragment5.str2 = str1;
        return simpleFragment5;
    }




    @Override//这个方法比上个方法后执行
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获得view对象，仅为获取控件对象并赋值
        /**
         * 周双文
         * 更改Fragment布局
         */
        /**
         * 杜然 增加点击事件
         */
        if (getArguments()!=null){
            userId=getArguments().getString("userId");
            nameStr=getArguments().getString("userName");
            passWordStr=getArguments().getString("userPassword");
        }
        View view = inflater.inflate(R.layout.activity_mine, container, false);
        search= view.findViewById(R.id.ll_search);
        about = view.findViewById(R.id.ll_about_us);
        paihang = view.findViewById(R.id.tv_paihang);
        fens = view.findViewById(R.id.ll_fens);
        recentReading = view.findViewById(R.id.ll_recent_read);
        signIn = view.findViewById(R.id.tv_sign_in);
        myCollection=view.findViewById(R.id.s_my_collection);
        myPost=view.findViewById(R.id.my_post_ll);
        loginOut=view.findViewById(R.id.login_out);

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
        fens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FensActivity.class);
                startActivity(intent);
            }
        });
        recentReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecentReadActivity.class);
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(getContext(), SignToday.class);
                startActivity(intent);
            }
        });
        myCollection.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(getContext(), MyCollection.class);
                startActivity(intent);
            }
        });
        myPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userId",userId);
                intent.setClass(getContext(), MyPost.class);
                startActivity(intent);
            }
        });
        paihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaihangActivity.class);
                startActivity(intent);
            }
        });
        loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getContext(), Login.class);
                intent.putExtra("nameStr",nameStr);
                intent.putExtra("passwordStr",passWordStr);
                startActivity(intent);
            }
        });
        return view;
    }
}