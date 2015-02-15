package com.example.administrator.daiylywriting.Views_For_Layout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.MainActivity.Adapter_MainViewPage;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/15.
 */
public class ActivityViews_MainActivity extends RelativeLayout {
    public LinearLayout mMainbody;
    public LinearLayout mParentMenuMain;
    public RadioGroup mButtonGroup;
    public RadioButton mButtonNearly;
    public RadioButton mCharts;
    public RadioButton mButtonPush;
    public RadioButton mButtonMe;
    public RadioButton mButtonTool;
    public RelativeLayout mParentMenuShader;
    public ImageView mNearlypoint;
    public ImageView mNewcreatepoint;
    public ImageView mPushpoint;
    public ImageView mMepoint;
    public ImageView mToolpoint;
    public android.support.v4.view.ViewPager mMainBodyDiv;

    private final int VIEWPAGER_NEARLY = 0;
    private final int VIEWPAGER_CHARTS = 1;
    private final int VIEWPAGER_PULLDOWN = 2;
    private final int VIEWPAGER_LIFT = 3;
    private final int VIEWPAGER_TOOL = 4;

    private Adapter_MainViewPage mainViewPageAdapter;

    public ActivityViews_MainActivity(Context context) {
        super(context);
        BaseFragmentActivity activity = (BaseFragmentActivity) context;
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_main_page, this);
        mMainbody = (LinearLayout) findViewById(R.id.mainbody);
        mParentMenuMain = (LinearLayout) findViewById(R.id.parentMenuMain);
        mButtonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        mButtonNearly = (RadioButton) findViewById(R.id.ButtonNearly);
        mCharts = (RadioButton) findViewById(R.id.charts);
        mButtonPush = (RadioButton) findViewById(R.id.ButtonPush);
        mButtonMe = (RadioButton) findViewById(R.id.ButtonMe);
        mButtonTool = (RadioButton) findViewById(R.id.ButtonTool);
        mParentMenuShader = (RelativeLayout) findViewById(R.id.parentMenuShader);
        mNearlypoint = (ImageView) findViewById(R.id.nearlypoint);
        mNewcreatepoint = (ImageView) findViewById(R.id.newcreatepoint);
        mPushpoint = (ImageView) findViewById(R.id.pushpoint);
        mMepoint = (ImageView) findViewById(R.id.mepoint);
        mToolpoint = (ImageView) findViewById(R.id.toolpoint);
        mMainBodyDiv = (android.support.v4.view.ViewPager) findViewById(R.id.MainBodyDiv);
        mainViewPageAdapter = new Adapter_MainViewPage(activity.getSupportFragmentManager());
        mButtonGroup.check(R.id.ButtonNearly);
        initViewpages();
        setViewpagerInteraction();
        setCheck();


    }

    private void initViewpages() {
        mNearlypoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
        mMainBodyDiv.setOffscreenPageLimit(5);
        mMainBodyDiv.setAdapter(mainViewPageAdapter);

    }

    private void setCheck() {
        mButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int curtPage = mMainBodyDiv.getCurrentItem();
                int curtButton = 0;
                switch (checkedId) {
                    case R.id.ButtonNearly:
                        curtButton = VIEWPAGER_NEARLY;
                        YoYo.with(Techniques.Swing).duration(700).playOn(mButtonNearly);
                        buttonBackgroundClear();
                        mNearlypoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.charts:
                        curtButton = VIEWPAGER_CHARTS;
                        YoYo.with(Techniques.Swing).duration(700).playOn(mCharts);
                        buttonBackgroundClear();
                        mNewcreatepoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonPush:
                        curtButton = VIEWPAGER_PULLDOWN;
                        YoYo.with(Techniques.Swing).duration(700).playOn(mButtonPush);
                        buttonBackgroundClear();
                        mPushpoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonMe:
                        curtButton = VIEWPAGER_LIFT;
                        YoYo.with(Techniques.Swing).duration(700).playOn(mButtonMe);
                        buttonBackgroundClear();
                        mMepoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonTool:
                        curtButton = VIEWPAGER_TOOL;
                        YoYo.with(Techniques.Swing).duration(700).playOn(mButtonTool);
                        buttonBackgroundClear();
                        mToolpoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                }
                if (curtPage != curtButton) {
                    mMainBodyDiv.setCurrentItem(curtButton);
                }
            }
        });

    }

    /**
     * change the buttonColor to sign which pager you have been selceted!
     */
    private void buttonBackgroundClear() {
        mNearlypoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        mNewcreatepoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        mPushpoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        mMepoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        mToolpoint.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
    }

    /**
     * viewpager for radiogroup
     */
    private void setViewpagerInteraction() {
        mMainBodyDiv.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case VIEWPAGER_NEARLY:
                        onTheViewListener.onNearlySelected();
                        break;
                    case VIEWPAGER_CHARTS:
                        onTheViewListener.onChartSelected();
                        break;
                    case VIEWPAGER_PULLDOWN:
                        onTheViewListener.onPullDownSelected();
                        break;
                    case VIEWPAGER_LIFT:
                        onTheViewListener.onLifeSelected();
                        break;
                    case VIEWPAGER_TOOL:
                        onTheViewListener.onToolSelected();
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
