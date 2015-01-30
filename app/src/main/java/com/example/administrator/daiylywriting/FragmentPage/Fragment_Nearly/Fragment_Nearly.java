package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.AnimateForWriting.CubeTransformer;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.R;


/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Nearly extends BaseFragment {
    private static ViewPager mViewPager;
    private Fragment_Nearly_Adapter fragment_nearly_adapter;
    private RelativeLayout activityDiv, chapterDiv, booksDiv;
    private TextView nearly_ActivityText, nearly_BooksText;
    private boolean isStart = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearly, container, false);
    }


    @Override
    public void findViews() {
        mViewPager = (ViewPager) getView().findViewById(R.id.recentViewPage);
        booksDiv = (RelativeLayout) getActivity().findViewById(R.id.recentBooksDiv);
        activityDiv = (RelativeLayout) getActivity().findViewById(R.id.recentActivityDiv);
        nearly_ActivityText = (TextView) getActivity().findViewById(R.id.recentActivityText);
        nearly_BooksText = (TextView) getActivity().findViewById(R.id.recentBooksText);

    }

    @Override
    public void getData() {
//        viewpager的适配器
        fragment_nearly_adapter = new Fragment_Nearly_Adapter(this.getChildFragmentManager());
    }

    @Override
    public void showContent() {
        /*子页的ViewPager
        * */
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageTransformer(true, new CubeTransformer());
        mViewPager.setAdapter(fragment_nearly_adapter);

        nearly_ActivityText.setTextColor(Color.parseColor("#49dafa"));
    }

    @Override
    public void setInteract() {
        mViewPagerInteraction();
        //      初始化选项卡
        setNearlyDivInteraction(booksDiv);
        setNearlyDivInteraction(activityDiv);
    }

    @Override
    public void reStartInit() {

    }

    private void mViewPagerInteraction() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        clearButtonTextColor();
                        nearly_ActivityText.setTextColor(Color.parseColor("#49dafa"));
                        YoYo.with(Techniques.Swing).duration(700).playOn(activityDiv);
                        break;
                    case 1:
                        clearButtonTextColor();
                        nearly_BooksText.setTextColor(Color.parseColor("#49dafa"));
                        YoYo.with(Techniques.Swing).duration(700).playOn(booksDiv);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /**
     * 用于选中之后跳转到添加小说页面
     */
    public static void gotoBook() {
        mViewPager.setCurrentItem(1);
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
                if (mViewPager.getCurrentItem() != currentPage) {
                    mViewPager.setCurrentItem(currentPage);
                }
            }
        });
    }

    /**
     * 选项卡用
     */
    private void clearButtonTextColor() {
        nearly_BooksText.setTextColor(Color.GRAY);
        nearly_ActivityText.setTextColor(Color.GRAY);
    }
}
