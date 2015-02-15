package com.example.administrator.daiylywriting.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.Fragment_Charts;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Main.Fragment_Me;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Main.Fragment_Push;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Main.Fragment_Tool;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/26.
 */
public class Adapter_MainViewPage extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private Fragment mFragment_Create,mFragment_Me,mFragment_Nearly,mFragment_Push,mFragment_Tool;
    public Adapter_MainViewPage(FragmentManager fm) {
        super(fm);
        mFragment_Create=new Fragment_Charts();
        mFragment_Me=new Fragment_Me();
        mFragment_Nearly=new Fragment_Nearly();
        mFragment_Push =new Fragment_Push();
        mFragment_Tool =new Fragment_Tool();
        mFragments = new ArrayList<Fragment>();
        mFragments .add(mFragment_Nearly);
        mFragments .add(mFragment_Create);
        mFragments .add(mFragment_Push);
        mFragments .add(mFragment_Me);
        mFragments.add(mFragment_Tool);
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
