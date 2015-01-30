package com.example.administrator.daiylywriting.OtherActivity;

/**
 * Created by Administrator on 2015/1/13.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.BooksSqilte.DaoSession;
import com.example.administrator.daiylywriting.R;

import java.net.Inet4Address;
import java.util.LinkedList;
import java.util.List;


public class SingerChapterAdd extends Activity {
    private TextView backTextView,bookNameVaule;
    private Button nameCreateButton;
    private static final Integer SINGLE_RETURN=1;
    @Override
    public void onCreate(Bundle savedInstanceStatus){
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.onlyedit_activity);
        initView();
    }
    private void initView(){
        bookNameVaule = (TextView) findViewById(R.id.editText);
        backTextView = (TextView) findViewById(R.id.returnDiv);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameCreateButton= (Button) findViewById(R.id.nameCreate);
        nameCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bookNameVaule.getText().length()==0){
                    SomeStaticThing.toastSomthing(getApplicationContext(),"章节名不可为空!");
                }else {
                    Intent intent =getIntent();
                    String bookkey=intent.getStringExtra("bookkey");
                    GreenDaoService greenDaoService =GreenDaoService.getGreenDaoService(getApplicationContext());
                    List<Charpters> charpterses =new LinkedList<Charpters>();
                    Charpters charpters =new Charpters();
                    charpters.setBookKey(bookkey);
                    charpters.setCharpterName(bookNameVaule.getText().toString());
                    charpterses.add(charpters);
                    greenDaoService.saveOrReplacChapters(charpterses);

                    Intent in = new Intent();
                    in.putExtra("book", "dqcao+newname");
                    //-1为RESULT_OK, 1为RESULT_CANCEL..
                    // in 则是回调的Activity内OnActivityResult那个方法内处理
                    setResult(-1, in);
                    finish();

                }
            }
        });
    }

}
