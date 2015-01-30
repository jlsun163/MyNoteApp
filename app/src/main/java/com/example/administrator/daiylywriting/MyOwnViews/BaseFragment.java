package com.example.administrator.daiylywriting.MyOwnViews;

import android.support.v4.app.FragmentManager;

import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly;

/**
 * Created by Administrator on 2015/1/27.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {
    /**
     * 基础类型用来处理fragment的基本结构
     */
    private boolean isStart =false;
    @Override
    public void onStart(){
        super.onStart();
        if (!isStart) {
            findViews();
            getData();
            showContent();
            setInteract();
            isStart=true;
        }else {
            reStartInit();
        }
    }
    public abstract void findViews();
    public abstract void getData();
    public abstract void showContent();
    public abstract void setInteract();
    public abstract void reStartInit();
}
