package com.example.administrator.daiylywriting.MainActivity;

import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.daiylywriting.MyOwnViews.BaseFragmentActivity;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.Views_For_Layout.ActivityViews_MainActivity;


public class MainActivity extends BaseFragmentActivity {

    private ActivityViews_MainActivity views_mainActivity;

    @Override
    public void setContentView() {
        views_mainActivity = new ActivityViews_MainActivity(this);
        setContentView(views_mainActivity);
    }

    @Override
    public void findViews() {

    }

    @Override
    public void getData() {
    }

    @Override
    public void showContent() {

    }

    @Override
    public void setInteract() {
        views_mainActivity.setOnTheViewListener(new ActivityViews_MainActivity.OnTheViewListener() {
            @Override
            public void onNearlySelected() {
                views_mainActivity.mButtonNearly.performClick();
            }

            @Override
            public void onChartSelected() {
                views_mainActivity.mCharts.performClick();
            }

            @Override
            public void onPullDownSelected() {
                views_mainActivity.mButtonPush.performClick();
            }

            @Override
            public void onLifeSelected() {
                views_mainActivity.mButtonMe.performClick();
            }

            @Override
            public void onToolSelected() {
                views_mainActivity.mButtonTool.performClick();
            }
        });
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


}
