package com.example.administrator.daiylywriting.MyOwnViews;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2015/1/3.
 */
public class MyLayoutManager extends LinearLayoutManager {
    public MyLayoutManager(Context context) {
        super(context);
    }
//    @Override
//    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec,int heightSpec) {
//        View view = recycler.getViewForPosition(0);
//        if(view != null){
//            measureChild(view, 500, heightSpec);
//            int measuredWidth = View.MeasureSpec.getSize(widthSpec);
//            int measuredHeight = view.getMeasuredHeight();
//            setMeasuredDimension(measuredWidth, measuredHeight);
//        }
//    }
}
