package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Adapter_News;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.R;
import com.example.administrator.daiylywriting.Views_For_Layout.FragmentViews_Nearly_Activity;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2014/12/28.
 */
public class Fragment_Nearly_Activity extends BaseFragment {
    private GreenDaoService greenDaoService;
    private Adapter_News newsAdapter;
    private FragmentViews_Nearly_Activity fragmentViews_nearly_activity;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentViews_nearly_activity =new FragmentViews_Nearly_Activity(getActivity());
        return fragmentViews_nearly_activity;
    }


    @Override
    public void findViews() {

    }

    @Override
    public void getData() {
        //init the GreenDaoOrm for sqlite
        greenDaoService =GreenDaoService.getGreenDaoService(getActivity().getApplicationContext());
        //The ListVaule and Adpater of News for the newsListView
        List<NowHappents> nowHappentses =new LinkedList<>();
        nowHappentses.clear();
        nowHappentses.addAll(greenDaoService.loadAllNews());
        newsAdapter =new Adapter_News(getActivity(),nowHappentses);
    }

    @Override
    public void showContent() {
        fragmentViews_nearly_activity.mNewShowListView.setAdapter(newsAdapter);
    }

    @Override
    public void setInteract() {
        fragmentViews_nearly_activity.mWelcomeIfNoBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Nearly.gotoBook();
            }
        });
    }

    @Override
    public void reStartInit() {

    }


}
