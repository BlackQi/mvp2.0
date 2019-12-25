package com.qzp.mymvpframe.view.test;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.qzp.mymvpframe.base.BaseLazyLoadFragment;

import java.util.ArrayList;

/**
 * Created by qzp on 2018/11/22.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public FragmentAdapter(FragmentManager fm,ArrayList<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
