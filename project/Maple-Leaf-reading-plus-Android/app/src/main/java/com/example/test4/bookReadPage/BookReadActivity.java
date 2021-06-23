package com.example.test4.bookReadPage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;
import com.example.test4.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.io.File;
public class BookReadActivity extends AppCompatActivity {
    private String bookName;
    private int  defaultPage=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);
//获得图书名字
        bookName = getIntent().getStringExtra("fileName");
        PDFView pdfView=findViewById(R.id.pdfreadView);
       // pdfView.fromAsset("test.pdf")
       //File file=new File("/sdcard/loverReader/"+bookName);
        File file=new File("/storage/emulated/0/loverReader/"+bookName+".pdf");
        Log.e("在图书AookReadActivit打印书名：",bookName);

//        Canvas cnvs = new Canvas();i
//        cnvs.drawColor(getResources().getColor(R.color.black));
//        pdfView.draw(cnvs);
        pdfView.setOnClickListener(new PDFView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("绘图监听","出发绘图");
                Toast.makeText(BookReadActivity.this,"绘图",Toast.LENGTH_LONG).show();
            }
        });
        pdfView.fromFile(file)
//                .pages(0, 2, 1, 3, 3, 3) //限制能显示的页面为那些页，默认展示所有页面。
                .enableSwipe(true) // 允许使用刷卡阻止更改页面
                .swipeHorizontal(false)//是否水平翻页，默认竖直翻页
                .enableDoubletap(false)
                .defaultPage(defaultPage)//打开时候的默认页面
//                 .onDraw() //允许借鉴的东西当前页面，通常在屏幕中间可见
//                .onDrawAll()//允许在所有页面上分别为每个页面绘制内容。仅针对可见页面调用
//                .onLoad(onLoadCompleteListener) // 在文档加载并开始呈现之后.设置加载监听
//                .onPageChange(onPageChangeListener)//设置翻页监听
//                .onPageScroll(onPageScrollListener)//设置页面滑动监听
//                .onError(onErrorListener)
//                .onPageError(onPageErrorListener)
//                .onRender(onRenderListener) //首次呈现文档后,首次提交文档后调用。
//                //调用轻按一次即可返回true（如果已处理），则返回false以切换滚动柄可见性
//                .onTap(onTapListener)
//                .onLongPress(onLongPressListener)
                .enableAnnotationRendering(true)//呈现注释（例如注释，颜色或表单）
//                .password(null)
//                .scrollHandle(null)
                .enableAntialiasing(true)//改善低分辨率屏幕上的渲染
//               // dp中页面之间的间距。以限定间隔颜色，组视图背景
//                .spacing(0)
//                .autoSpacing(false) //添加动态间距以适合在屏幕上在其自己的每一页
//                .linkHandler(DefaultLinkHandler)
//                .pageFitPolicy(FitPolicy.WIDTH) //模式，以适应视图中的页面
                .fitEachPage(false) //使每个页面适合视图，否则较小页面相对于最大页面缩放。
                .pageSnap(false) //将页面捕捉到屏幕边界
                .pageFling(false) //仅更改单个页面，例如ViewPager
                .nightMode(false) //切换夜间模式
                .enableSwipe(true)///是否允许翻页，默认是允许翻
                .load();








    }
}
