package com.example.test4;

import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import com.example.test4.Fragment.FirstFragment;
import com.example.test4.Fragment.SecondFragment;
import com.example.test4.Fragment.ThirdFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 12/9   杜然 排行页面
 */
public class PaihangActivity extends AppCompatActivity {
    private Map<String, TextView> textViewMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paihang);
        //获取FragmentTabHost的引用
        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),//管理多个Fragment对象的管理器
                android.R.id.tabcontent);//显示内容页面的控件的id
        //创建内容页面的TabSpec对象
        TabHost.TabSpec tab1 = fragmentTabHost.newTabSpec("first_tab")
                .setIndicator(getTabSpecView("first_tab","累计签到"));
        fragmentTabHost.addTab(tab1,
                FirstFragment.class,//FristFragment类的Class（字节码）对象
                null);//传递数据时使用，不需要传递数据直接传null
        TabHost.TabSpec tab2 = fragmentTabHost.newTabSpec("second_tab")
                .setIndicator(getTabSpecView("second_tab","热门收藏"));
        fragmentTabHost.addTab(tab2,
                SecondFragment.class,//SecondFragment类的Class对象
                null);
        TabHost.TabSpec tab3 = fragmentTabHost.newTabSpec("third_tab")
                .setIndicator(getTabSpecView("third_tab","人气热读"));
        fragmentTabHost.addTab(tab3,
                ThirdFragment.class,//ThirdFragment类的Class对象
                null);
        //处理fragmentTabHost的选项切换事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //修改文字颜色
                switch (tabId){
                    case "first_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.red));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.black));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.black));
                        break;
                    case "second_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.black));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.red));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.black));
                        break;
                    case "third_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.black));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.black));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.red));
                        break;
                }
            }
        });
        //设置默认选中的标签页:参数是下标
        fragmentTabHost.setCurrentTab(0);
        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.red));
    }

    public View getTabSpecView(String tag,String title) {
        View view = getLayoutInflater().inflate(R.layout.tab_spec_layout,null);
        //获取tab_spec_layout布局当中视图控件的引用
        TextView tvTitle = view.findViewById(R.id.title);
        tvTitle.setText(title);
        textViewMap.put(tag,tvTitle);
        return view;
    }
}
