package com.example.administrator.daiylywriting.Views_For_Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/15.
 */

public class AdapterView_New extends LinearLayout {
    public  TextView mCycle;
    public  TextView mListPartNewVauleTextView;
    public AdapterView_New(Context context) {
        super(context);
        ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.newslist_part,this);
        mCycle = (TextView) findViewById(R.id.cycle);
        mListPartNewVauleTextView = (TextView) findViewById(R.id.listPartNewVauleTextView);
    }
}
