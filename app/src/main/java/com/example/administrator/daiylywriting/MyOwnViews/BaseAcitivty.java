package com.example.administrator.daiylywriting.MyOwnViews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/1/27.
 */
public abstract class BaseAcitivty extends Activity {
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
