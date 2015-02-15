package com.example.administrator.daiylywriting.Views_For_Layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/2/15.
 */
public class FragmentViews_Nearly_Activity extends RelativeLayout {
    public TextView mWelcomeIfNoBook;
    public ListView mNewShowListView;

    public FragmentViews_Nearly_Activity(Context context) {
        super(context);
        ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.fragment_nearly_activity, this);
        mWelcomeIfNoBook = (TextView) findViewById(R.id.welcomeIfNoBook);
        mNewShowListView = (ListView) findViewById(R.id.newShowListView);
    }
}
