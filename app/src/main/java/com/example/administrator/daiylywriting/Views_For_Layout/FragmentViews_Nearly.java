package com.example.administrator.daiylywriting.Views_For_Layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.AnimateForWriting.CubeTransformer;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Fragment_Nearly_Adapter;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/15.
 */
public class FragmentViews_Nearly extends LinearLayout {
    public de.hdodenhof.circleimageview.CircleImageView mProfile_image;
    public RelativeLayout mRecentActivityDiv;
    public TextView mRecentActivity;
    public TextView mRecentActivityText;
    public RelativeLayout mRecentBooksDiv;
    public TextView mRecentBooks;
    public TextView mRecentBooksText;
    public android.support.v4.view.ViewPager mRecentViewPage;

    private Fragment_Nearly_Adapter fragment_nearly_adapter;

    public FragmentViews_Nearly(Context context,BaseFragment baseFragment) {
        super(context);
        ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_nearly,this);

        mProfile_image = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profile_image);
        mRecentActivityDiv = (RelativeLayout) findViewById(R.id.recentActivityDiv);
        mRecentActivity = (TextView) findViewById(R.id.recentActivity);
        mRecentActivityText = (TextView) findViewById(R.id.recentActivityText);
        mRecentBooksDiv = (RelativeLayout) findViewById(R.id.recentBooksDiv);
        mRecentBooks = (TextView) findViewById(R.id.recentBooks);
        mRecentBooksText = (TextView) findViewById(R.id.recentBooksText);
        mRecentViewPage = (android.support.v4.view.ViewPager) findViewById(R.id.recentViewPage);
        fragment_nearly_adapter = new Fragment_Nearly_Adapter(baseFragment.getChildFragmentManager());
        mRecentViewPage.setOffscreenPageLimit(2);
        mRecentViewPage.setPageTransformer(true, new CubeTransformer());
        mRecentViewPage.setAdapter(fragment_nearly_adapter);

        mRecentActivityText.setTextColor(Color.parseColor("#49dafa"));

        mViewPagerInteraction();
        //      初始化选项卡
        setNearlyDivInteraction(mRecentBooksDiv);
        setNearlyDivInteraction(mRecentActivityDiv);
    }


    private void setNearlyDivInteraction(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing).duration(700).playOn(v);
                int currentPage = 0;
                switch (v.getId()) {
                    case R.id.recentActivityDiv:
                        currentPage = 0;
                        break;
                    case R.id.recentBooksDiv:
                        currentPage = 1;
                        break;

                }
                if (mRecentViewPage.getCurrentItem() != currentPage) {
                    mRecentViewPage.setCurrentItem(currentPage);
                }
            }
        });
    }


    private void mViewPagerInteraction() {
        mRecentViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        clearButtonTextColor();
                        mRecentActivityText.setTextColor(Color.parseColor("#49dafa"));
                        YoYo.with(Techniques.Swing).duration(700).playOn(mRecentActivityDiv);
                        break;
                    case 1:
                        clearButtonTextColor();
                        mRecentBooksText.setTextColor(Color.parseColor("#49dafa"));
                        YoYo.with(Techniques.Swing).duration(700).playOn(mRecentBooksDiv);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }


    /**
     * 选项卡用
     */
    private void clearButtonTextColor() {
        mRecentBooksText.setTextColor(Color.GRAY);
        mRecentActivityText.setTextColor(Color.GRAY);
    }

    /**
     * view interface
     */
    public static interface OnTheViewListener {
        public void onNearlySelected();

        public void onChartSelected();

        public void onPullDownSelected();

        public void onLifeSelected();

        public void onToolSelected();
    }

    /**
     * init the interface
     */
    OnTheViewListener onTheViewListener = null;

    /**
     * view listenr callback
     */
    public void setOnTheViewListener(OnTheViewListener listener) {
        onTheViewListener = listener;
    }


}
