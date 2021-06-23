package com.example.test4.bookReadPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PDFturn extends AppCompatActivity {
    private String bookName;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        userId=getIntent().getStringExtra("userId");
        bookName = getIntent().getStringExtra("fileName");
        Bundle bundle = this.getIntent().getExtras();
        String name1=bundle.get("name1").toString();
        String name2=bundle.get("name2").toString();
        String name3=bundle.get("name3").toString();
        String num=bundle.getString("num").toString();


        Intent intent=new Intent(PDFturn.this,PDFReadActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("fileName",bookName);
        intent.putExtra("name1",name1);
        intent.putExtra("name2",name2);
        intent.putExtra("name3",name3);
        intent.putExtra("num",num);
        intent.putExtra("order","2");
        Log.e("这是打印结果，期待false",name1);
        Log.e(name1+"     "+name2+"      "+name3+"      "+num,"--------------");
        startActivity(intent);








    }
}