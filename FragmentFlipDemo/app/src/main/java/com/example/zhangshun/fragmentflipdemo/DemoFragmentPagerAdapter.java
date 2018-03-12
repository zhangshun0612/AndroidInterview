package com.example.zhangshun.fragmentflipdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ZhangShun on 2018/3/11.
 */

public class DemoFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;

    public DemoFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
