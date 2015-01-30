package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters.Adapter_BooksRecycle;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.MyOwnViews.MyLayoutManager;
import com.example.administrator.daiylywriting.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Fragment_Nearly_Books extends BaseFragment {
    private  MyLayoutManager mBookLayoutManager,mChapterLayoutManager;
    private  RecyclerView bookRecyclerView,chapterRecycleView;
    private Adapter_BooksRecycle booksRecycleAdapter;
    private ArrayList<BooksVaules> bookModels =new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_nearly_books, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }

    @Override
    public void findViews() {
        bookRecyclerView = (RecyclerView) getActivity()
                .findViewById(R.id.hot_fragment_recycler);
        chapterRecycleView = (RecyclerView) getActivity().findViewById(R.id.chapterList_recycler);
        bookRecyclerView.setHasFixedSize(false);
        chapterRecycleView.setHasFixedSize(false);
        mChapterLayoutManager =new MyLayoutManager(getActivity());
        mBookLayoutManager = new MyLayoutManager(getActivity());
    }

    @Override
    public void getData() {
        //上方横着的RcyclerView的适配
        booksRecycleAdapter =new Adapter_BooksRecycle(getActivity(),bookModels,chapterRecycleView);
    }

    @Override
    public void showContent() {
        //RcyclerView的初始化设置
        mChapterLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBookLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bookRecyclerView.setLayoutManager(mBookLayoutManager);
        chapterRecycleView.setLayoutManager(mChapterLayoutManager);
        bookRecyclerView.setAdapter(booksRecycleAdapter);
    }

    @Override
    public void setInteract() {

    }

    @Override
    public void reStartInit() {

    }


}
