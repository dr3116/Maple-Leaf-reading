package com.example.test4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.example.test4.main_fragment_all_5.SimpleFragment1;
import com.example.test4.main_fragment_all_5.SimpleFragment2;
import com.example.test4.main_fragment_all_5.SimpleFragment3;
import com.example.test4.main_fragment_all_5.SimpleFragment4;
import com.example.test4.main_fragment_all_5.SimpleFragment5;
import com.example.test4.welcome2.MyEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String userId;
    private CommonTabLayout tab;
    private ViewPager view;
    private ArrayList<CustomTabEntity> dates = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"首页", "分类", "发现", "书架", "我的"};
    private int[] unselectItem = {R.drawable.shou_ye_11, R.drawable.fen_lei_21, R.drawable.dao_hang_lan_31, R.drawable.shu_jia_41, R.drawable.wo_de_51};
    private int[] selectItem = {R.drawable.shou_ye_12, R.drawable.fen_lei_22, R.drawable.dao_hang_lan_32, R.drawable.shu_jia_42, R.drawable.wo_de_52};
    private int[] imglist = {R.drawable.linshi, R.drawable.linshi, R.drawable.linshi, R.drawable.linshi, R.drawable.linshi};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取用户id
        userId=(String)getIntent().getStringExtra("userId");


        //步骤1：实例化控件对象
        initView();
        //2.初始化fragment对象，初始化fragment界面
        initFragment();
        //3.小page
        initTabLayout();
        //4.大page
        initViewPager();
    }


    private void initViewPager() {
        view.setAdapter(new myAdapter(getSupportFragmentManager()));
        view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);//------------------------------------------------------
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    class myAdapter extends FragmentPagerAdapter {
        public myAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override//拿到每一个item
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override//拿到每一页title
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            Log.e("------",fragments.size()+"");
            return fragments.size();//记得除以2。
        }
    }

    public void initTabLayout() {
        //1. 绑定图片数组与文字数组,实例初始化完成
        for (int i = 0; i < titles.length; ++i) {
            dates.add(new MyEntity(selectItem[i], unselectItem[i], titles[i], imglist[i]));
        }
        //数据放控图
        tab.setTabData(dates);
        //tab转变时触发事件
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override//被选中,互相监听
            //底部导航栏选中
            public void onTabSelect(int position) {
                view.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }//---------------------------------------------------------------

    public void initView() {
        tab = findViewById(R.id.tb);
        view = findViewById(R.id.vp);
    }

    public void initFragment() {
        /**
         * 周双文，注释下面的代码将我的Fragment添加到fragments中去
         */

        //每个界面都必须先初始化后添加。
        SimpleFragment1 simpleFragment1 = SimpleFragment1.newInstance("1");
        SimpleFragment2 simpleFragment2 = SimpleFragment2.newInstance("2");
        SimpleFragment3 simpleFragment3 = SimpleFragment3.newInstance("3");
        SimpleFragment4 simpleFragment4 = SimpleFragment4.newInstance("4");
        SimpleFragment5 simpleFragment5 = SimpleFragment5.newInstance("5");
        //将userId传递给每个Fragment
        Bundle bundle=new Bundle();
        bundle.putString("userId",userId);
        simpleFragment1.setArguments(bundle);
        simpleFragment2.setArguments(bundle);
        simpleFragment3.setArguments(bundle);
        simpleFragment4.setArguments(bundle);
        simpleFragment5.setArguments(bundle);
        //先后顺序关系着界面展示的顺序
        fragments.add(simpleFragment1);
        fragments.add(simpleFragment2);
        fragments.add(simpleFragment3);
        fragments.add(simpleFragment4);
        fragments.add(simpleFragment5);

    }

}