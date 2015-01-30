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

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2014/12/28.
 */
public class Fragment_Nearly_Activity extends BaseFragment {
    private ListView newsListview;
    private GreenDaoService greenDaoService;
    private Adapter_News newsAdapter;
    private TextView mWelcomeIfNoBook;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nearly_activity, container, false);
    }


    @Override
    public void findViews() {
        newsListview= (ListView) getActivity().findViewById(R.id.newShowListView);
        mWelcomeIfNoBook= (TextView) getActivity().findViewById(R.id.welcomeIfNoBook);
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
        newsListview.setAdapter(newsAdapter);
    }

    @Override
    public void setInteract() {
        mWelcomeIfNoBook.setOnClickListener(new View.OnClickListener() {
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
