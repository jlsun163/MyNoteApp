package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Fragment_Nearly_Adapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private Fragment mFragment_Nearly_Activity,mFragment_Nearly_Books;
    public Fragment_Nearly_Adapter (FragmentManager fm) {
        super(fm);
        mFragment_Nearly_Activity = new Fragment_Nearly_Activity();
        mFragment_Nearly_Books = new Fragment_Nearly_Books();
        mFragments = new ArrayList<Fragment>();
        mFragments .add(mFragment_Nearly_Activity);
        mFragments .add(mFragment_Nearly_Books);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size() ;
    }
}
