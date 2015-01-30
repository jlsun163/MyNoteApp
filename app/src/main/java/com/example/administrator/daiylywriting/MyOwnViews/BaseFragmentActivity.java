package com.example.administrator.daiylywriting.MyOwnViews;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2015/1/27.
 */
public abstract  class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    public void init(){
        setContentView();
        findViews();
        getData();
        showContent();
        setInteract();
    }

    public abstract void setContentView();
    public abstract void findViews();
    public abstract void getData();
    public abstract void showContent();
    public abstract void setInteract();
}
