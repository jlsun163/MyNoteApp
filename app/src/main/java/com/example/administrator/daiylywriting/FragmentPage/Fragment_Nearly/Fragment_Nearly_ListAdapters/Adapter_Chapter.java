package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.FragmentPage.EditActivity.EditMYActivity;
import com.example.administrator.daiylywriting.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/1/13.
 */
public class Adapter_Chapter extends BaseAdapter {
    private Context activity;
    List<Charpters> charpterses=new LinkedList<>();
    public Adapter_Chapter(Context activity, List<Charpters> charpterses){
        this.activity=activity;
       this.charpterses=charpterses;
        Collections.reverse(charpterses);
    }
    @Override
    public int getCount() {
        return charpterses.size();
    }

    @Override
    public Object getItem(int position) {
        return charpterses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        ViewGroup  view;
        ViewGroup view1 =null;
        view = (ViewGroup) inflater.inflate(R.layout.cardview_chaptercard, null);
        TextView newsShowChapter = (TextView) view.findViewById(R.id.textView_ChapterShow);
        newsShowChapter.setText(charpterses.get(position).getCharpterName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, EditMYActivity.class);
                intent.putExtra("bookkey",charpterses.get(position).getCharpterName());
                intent.putExtra("id",charpterses.get(position).getId());
                activity.startActivity(intent);
            }
        });
        return view;
    }

}
