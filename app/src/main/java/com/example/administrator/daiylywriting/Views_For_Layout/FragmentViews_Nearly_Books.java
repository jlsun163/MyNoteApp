package com.example.administrator.daiylywriting.Views_For_Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.administrator.daiylywriting.MyOwnViews.MyLayoutManager;
import com.example.administrator.daiylywriting.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/2/15.
 */
public class FragmentViews_Nearly_Books extends LinearLayout {
    public android.support.v7.widget.RecyclerView mHot_fragment_recycler;
    public android.support.v7.widget.RecyclerView mChapterList_recycler;
    public FragmentViews_Nearly_Books(Context context) {
        super(context);
        ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_nearly_books,this);
        mHot_fragment_recycler = (android.support.v7.widget.RecyclerView) findViewById(R.id.hot_fragment_recycler);
        mChapterList_recycler = (android.support.v7.widget.RecyclerView) findViewById(R.id.chapterList_recycler);
        mHot_fragment_recycler.setHasFixedSize(false);
        mChapterList_recycler.setHasFixedSize(false);
    }
}
