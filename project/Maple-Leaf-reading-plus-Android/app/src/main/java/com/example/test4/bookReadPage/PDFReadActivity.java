package com.example.test4.bookReadPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test4.MainActivity;
import com.example.test4.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class PDFReadActivity extends AppCompatActivity {
    DrawerLayout Drawer;
    NavigationView navigationView;
    private String bookName;
    private int  defaultPage=0;
    private boolean tv1boolean=true;
    private boolean tv2boolean=false;
    private int pageSide=20;
    private String userId;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_read);
        Drawer = findViewById(R.id.home_id);
        userId=getIntent().getStringExtra("userId");
        bookName = getIntent().getStringExtra("fileName");
        Log.e("在PDFReadActivity页面打印书名：",bookName);
        //Log.e("在PDFReader打印userd",userId);
        navigationView =findViewById(R.id.nav);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(PDFReadActivity.this, MainActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("mark",4);
                startActivity(intent);
            }
        });
//           设置item图标正常显示
        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                      switch (menuItem.getItemId()){
                          case  R.id.tv1:{
                              if(tv1boolean==true){
                                  tv1boolean=false;
                              }else{
                                  tv1boolean=true;
                              }
                              Toast.makeText(PDFReadActivity.this," 横屏/竖屏切换成功",Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(PDFReadActivity.this,PDFturn.class);
                              intent.putExtra("userId",userId);
                              intent.putExtra("fileName",bookName);
                              intent.putExtra("name1",tv1boolean+"");
                              intent.putExtra("name2",tv2boolean+"");
                              intent.putExtra("name3",pageSide+"");
                              intent.putExtra("num",defaultPage+"");
                              startActivity(intent);
                              finish();
                          }break;
                          case  R.id.tv2:{
                              if(tv2boolean==true){
                                  tv2boolean=false;
                              }else{
                                  tv2boolean=true;
                              }
                              Toast.makeText(PDFReadActivity.this,"白天/夜间切换成功",Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(PDFReadActivity.this,PDFturn.class);
                              intent.putExtra("userId",userId);
                              intent.putExtra("fileName",bookName);
                              intent.putExtra("name1",tv1boolean+"");
                              intent.putExtra("name2",tv2boolean+"");
                              intent.putExtra("name3",pageSide+"");
                              intent.putExtra("num",defaultPage+"");
                              startActivity(intent);
                              finish();
                          }break;
                          case  R.id.tv3:{
                              if(pageSide==20){
                                  pageSide=40;
                              }else{
                                  pageSide=20;
                              }
                              Toast.makeText(PDFReadActivity.this,"页边距切换成功",Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(PDFReadActivity.this,PDFturn.class);
                              intent.putExtra("userId",userId);
                              intent.putExtra("fileName",bookName);
                              intent.putExtra("name1",tv1boolean+"");
                              intent.putExtra("name2",tv2boolean+"");
                              intent.putExtra("name3",pageSide+"");
                              intent.putExtra("num",defaultPage+"");
                              startActivity(intent);
                              finish();
                          }break;


                      }
                return false;
            }
        });

        //获取intnet参数
        Bundle bundle = this.getIntent().getExtras();
        String order=bundle.getString("order")+"";
        if(order.equals("2")){
            tv1boolean=Boolean.valueOf(bundle.getString("name1"));
            tv2boolean=Boolean.valueOf(bundle.getString("name2"));
            pageSide=Integer.valueOf(bundle.getString("name3"));
            defaultPage=Integer.valueOf(bundle.getString("num"));
//            Log.e("PDF          ",tv1boolean+"   "+tv2boolean+"     "+pageSide+"      "+defaultPage);
        }
        /**
         * 文件下载
         */


        /**
         * 文件解压
         */


        //----------------开始pdf阅读
        PDFView pdfView=findViewById(R.id.pdfView);
        Log.e("在PDF Reader页面打印文件路径","/storage/emulated/0/loverReader/"+bookName);
        File file=new File("/storage/emulated/0/loverReader/"+bookName);
        //File file=new File("/sdcard/loverReader/"+bookName);
        pdfView.fromFile(file)
//        pdfView.fromAsset("test.pdf")
                .enableSwipe(true) // 允许使用刷卡阻止更改页面
                .swipeHorizontal(tv1boolean)//是否水平翻页，默认竖直翻页
                .enableDoubletap(true)//是否可以双击放大页面
                .defaultPage(defaultPage)//打开时候的默认页面
                .enableAnnotationRendering(true)//呈现注释（例如注释，颜色或表单）
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)//改善低分辨率屏幕上的渲染
                .spacing(pageSide)//单位dp中页面之间的间距。以限定间隔颜色，组视图背景
                .autoSpacing(true) //添加动态间距以适合在屏幕上在其自己的每一页
                .pageFitPolicy(FitPolicy.WIDTH) //模式，以适应视图中的页面
                .fitEachPage(false) //使每个页面适合视图，否则较小页面相对于最大页面缩放。
                .pageSnap(false) //将页面捕捉到屏幕边界
                .pageFling(false) //仅更改单个页面，例如ViewPager
                .nightMode(tv2boolean) //切换夜间模式
                .enableSwipe(true)///是否允许翻页，默认是允许翻
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        defaultPage=page;
                        Toast.makeText(PDFReadActivity.this,page+1+" / "+pageCount+"页",Toast.LENGTH_SHORT).show();
                    }
                })
                .load();

    }












    @Override
    public void onBackPressed() {
        if (Drawer.isDrawerOpen(GravityCompat.START)) {
            Drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}

