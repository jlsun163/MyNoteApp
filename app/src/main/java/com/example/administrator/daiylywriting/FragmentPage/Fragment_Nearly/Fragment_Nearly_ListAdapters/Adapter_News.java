package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.FragmentPage.EditActivity.EditMYActivity;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/11.
 */
public class Adapter_News extends BaseAdapter {
    private Context activity;
    List<NowHappents> nowHappentses=new LinkedList<>();
    public Adapter_News(Context activity, List<NowHappents> nowHappentses){
        this.activity=activity;
        this.nowHappentses=nowHappentses;
        Collections.reverse(nowHappentses);
    }
    @Override
    public int getCount() {
        return nowHappentses.size();
    }

    @Override
    public Object getItem(int position) {
        return nowHappentses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        ViewGroup  view;
        ViewGroup view1 =null;
        view = (ViewGroup) inflater.inflate(R.layout.newslist_part, null);
        TextView newsShowTextView = (TextView) view.findViewById(R.id.listPartNewVauleTextView);
        newsShowTextView.setText(Html.fromHtml(nowHappentses.get(position).getNews()));
        return view;
    }
}
