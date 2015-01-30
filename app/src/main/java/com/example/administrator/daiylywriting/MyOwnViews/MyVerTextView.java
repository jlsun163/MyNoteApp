package com.example.administrator.daiylywriting.MyOwnViews;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/1/10.
 */
public class MyVerTextView extends LinearLayout {


    public MyVerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        this.context=context;
    }
    private String text;
    private Context context;
    private int color;
    private int size;

    public MyVerTextView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        this.context=context;
    }


    public void setText(String text)
    {
        this.text=text;
        addText();
    }

    private void addText()
    {
        removeAllViews();
        if(text!=null)
        {
            char[] chara=text.toCharArray();
            for(int i=0;i<chara.length;i++)
            {
                TextView oneText=new TextView(context);
                oneText.setTextColor(color);
                TextPaint textPaint =oneText.getPaint();
                textPaint.setFakeBoldText(true);
                oneText.setText(text.substring(i, i+1));
                if(size>0)
                {
                    oneText.setTextSize(size);
                }
                addView(oneText);
            }
        }

    }
    public void setTextColor(int color)
    {
        this.color=color;
    }
    public void setTextSize(int size)
    {
        this.size=size;
    }


}