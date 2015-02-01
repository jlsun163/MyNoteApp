package com.example.administrator.daiylywriting.MyOwnViews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/1.
 */
public class MyPushDialogView extends CardView {
    public TextView mChapterNameVaule;
    public TextView mPushStatus;
    public TextView mPushPath;

    public MyPushDialogView(Context context) {
        super(context);
        ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.cardview_chapterpushout, this);
        mChapterNameVaule = (TextView) findViewById(R.id.chapterNameVaule);
        mPushStatus = (TextView) findViewById(R.id.pushStatus);
        mPushPath = (TextView) findViewById(R.id.pushPath);
    }
}
