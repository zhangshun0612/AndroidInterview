package com.example.zhangshun.fragmentflipdemo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView pager1TextView;
    private TextView pager2TextView;
    private TextView pager3TextView;
    private TextView pager4TextView;

    private ViewPager viewPager;
    private View viewBar;

    private ArrayList<Fragment> fragmentList;

    private int currIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager1TextView = (TextView) findViewById(R.id.tv_page1);
        pager2TextView = (TextView) findViewById(R.id.tv_page2);
        pager3TextView = (TextView) findViewById(R.id.tv_page3);
        pager4TextView = (TextView) findViewById(R.id.tv_page4);

        pager1TextView.setOnClickListener(this);
        pager2TextView.setOnClickListener(this);
        pager3TextView.setOnClickListener(this);
        pager4TextView.setOnClickListener(this);

        viewBar = findViewById(R.id.bar);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        fragmentList = new ArrayList<>();
        Fragment oneFragment = new OneFragment();
        Fragment twoFragment = new TwoFragment();
        Fragment threeFragment = new ThreeFragment();
        Fragment fourFragment = new FourFragment();

        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);

        viewPager.setAdapter(new DemoFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*
                当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到
                position 当前点击滑动的页面
                positionOffset 当前页面移动的百分比
                positionOffsetPixels 当前页面偏移的像素位置
                 */
                /*
                LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) viewBar.getLayoutParams();

                if(currIndex == position){
                    ll.leftMargin = (int)(currIndex * viewBar.getWidth() + positionOffset * viewBar.getWidth());

                }else if(currIndex > position){
                    ll.leftMargin = (int)(currIndex * viewBar.getWidth() - (1 - positionOffset) * viewBar.getWidth());
                }
                viewBar.setLayoutParams(ll);
                */
            }

            @Override
            public void onPageSelected(int position) {
                //position 当前页面的位置编号
                currIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*
                state 0 默认什么都没有做
                state 1 正在滑动
                state 2 滑动完毕
                 */
                //Log.d("MainActivity", Integer.toString(state));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_page1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_page2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_page3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tv_page4:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
