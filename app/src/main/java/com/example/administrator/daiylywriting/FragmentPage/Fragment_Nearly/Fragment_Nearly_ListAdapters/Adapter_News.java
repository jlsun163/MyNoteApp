package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.FragmentPage.EditActivity.EditMYActivity;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.Views_For_Layout.AdapterView_New;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/11.
 */
public class Adapter_News extends BaseAdapter {
    private Context activity;
    private GreenDaoService greenDaoService;
    List<NowHappents> nowHappentses=new LinkedList<>();
    public Adapter_News(Context activity, List<NowHappents> nowHappentses){
        this.activity=activity;
        this.nowHappentses=nowHappentses;
        Collections.reverse(nowHappentses);
        greenDaoService=GreenDaoService.getGreenDaoService(activity);
    }
//    @Override
//    public int getCount() {
//        return nowHappentses.size();
//    }
@Override
public int getCount() {
    return greenDaoService.getAllWebData().size();
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
        AdapterView_New adapterView_new=null;
        if (convertView==null){
            adapterView_new = new AdapterView_New(activity);
        }else {
            adapterView_new=(AdapterView_New)convertView;
        }
//        adapterView_new.mListPartNewVauleTextView.setText(Html.fromHtml(nowHappentses.get(position).getNews()));
        WebData webData=greenDaoService.getAllWebData().get(position);
        adapterView_new.mListPartNewVauleTextView.setText(webData.getBookName().toString()+"："+webData.getClickNumber()+"点击"+webData.getLikeNumber()+"推荐"+webData.getTimeDate());
        return adapterView_new;
    }
}
