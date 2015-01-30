package com.example.administrator.daiylywriting.MainActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;


public class MainActivity extends BaseFragmentActivity {
    private final int VIEWPAGER_NEARLY = 0;
    private final int VIEWPAGER_CHARTS = 1;
    private final int VIEWPAGER_PULLDOWN = 2;
    private final int VIEWPAGER_LIFT = 3;
    private final int VIEWPAGER_TOOL = 4;
    private Adapter_MainViewPage mainViewPageAdapter;
    private ViewPager viewPager;
    private ImageView nearlyImg, createImg, pushImg, meImg, toolImg;
    private RadioGroup radioGroup;
    private RadioButton nearlyButton, chartButton, pushButton, meButton, toolButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main_page);
    }

    @Override
    public void findViews() {
        viewPager = (ViewPager) findViewById(R.id.MainBodyDiv);
        nearlyImg = (ImageView) findViewById(R.id.nearlypoint);
        createImg = (ImageView) findViewById(R.id.newcreatepoint);
        pushImg = (ImageView) findViewById(R.id.pushpoint);
        meImg = (ImageView) findViewById(R.id.mepoint);
        toolImg = (ImageView) findViewById(R.id.toolpoint);
        radioGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        nearlyButton = (RadioButton) findViewById(R.id.ButtonNearly);
        chartButton = (RadioButton) findViewById(R.id.charts);
        pushButton = (RadioButton) findViewById(R.id.ButtonPush);
        meButton = (RadioButton) findViewById(R.id.ButtonMe);
        toolButton = (RadioButton) findViewById(R.id.ButtonTool);
    }

    @Override
    public void getData() {
        mainViewPageAdapter = new Adapter_MainViewPage(getSupportFragmentManager());
    }

    @Override
    public void showContent() {
        nearlyImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(mainViewPageAdapter);
    }

    @Override
    public void setInteract() {
        radioGroup.check(R.id.ButtonNearly);
        setRadioGroupInteration();
        setViewpagerInteraction();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * RadioGroup for viewpager
     */
    public void setRadioGroupInteration() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int curtPage = viewPager.getCurrentItem();
                int curtButton = 0;
                switch (checkedId) {
                    case R.id.ButtonNearly:
                        curtButton = VIEWPAGER_NEARLY;
                        YoYo.with(Techniques.Swing).duration(700).playOn(nearlyButton);
                        buttonBackgroundClear();
                        nearlyImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.charts:
                        curtButton = VIEWPAGER_CHARTS;
                        YoYo.with(Techniques.Swing).duration(700).playOn(chartButton);
                        buttonBackgroundClear();
                        createImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonPush:
                        curtButton = VIEWPAGER_PULLDOWN;
                        YoYo.with(Techniques.Swing).duration(700).playOn(pushButton);
                        buttonBackgroundClear();
                        pushImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonMe:
                        curtButton = VIEWPAGER_LIFT;
                        YoYo.with(Techniques.Swing).duration(700).playOn(meButton);
                        buttonBackgroundClear();
                        meImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                    case R.id.ButtonTool:
                        curtButton = VIEWPAGER_TOOL;
                        YoYo.with(Techniques.Swing).duration(700).playOn(toolButton);
                        buttonBackgroundClear();
                        toolImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypointl));
                        break;
                }
                if (curtPage != curtButton) {
                    viewPager.setCurrentItem(curtButton);
                }
            }
        });
    }

    /**
     * viewpager for radiogroup
     */
    private void setViewpagerInteraction() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case VIEWPAGER_NEARLY:
                        nearlyButton.performClick();
                        break;
                    case VIEWPAGER_CHARTS:
                        chartButton.performClick();
                        break;
                    case VIEWPAGER_PULLDOWN:
                        pushButton.performClick();
                        break;
                    case VIEWPAGER_LIFT:
                        meButton.performClick();
                        break;
                    case VIEWPAGER_TOOL:
                        toolButton.performClick();
                        break;
                }
            }


            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * change the buttonColor to sign which pager you have been selceted!
     */
    private void buttonBackgroundClear() {
        nearlyImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        createImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        pushImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        meImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
        toolImg.setImageDrawable(getResources().getDrawable(R.drawable.greeypoint));
    }
}
