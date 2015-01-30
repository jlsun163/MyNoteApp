package com.example.administrator.daiylywriting.MyOwnViews;

/**
 * Created by Administrator on 2015/1/27.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/1/27.
 */

    /**
     * Created by Administrator on 2014/10/19.
     */
    public  class LineEditText extends EditText {
        // 画笔 用来画下划线
        private Paint paint;
        public LineEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.parseColor("#f0f0f0"));
            // 开启抗锯齿 较耗内存
            paint.setAntiAlias(true);
        }

        public void setColor(int color){

            paint.setColor(Color.RED);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // 得到总行数
            int lineCount = getLineCount();
            if (lineCount<40){
                lineCount=40;
            }
            // 得到每行的高度
            int lineHeight = getLineHeight();
            // 根据行数循环画线
            for (int i = 0; i < lineCount; i++) {
                int lineY = (i + 1) * lineHeight;
                canvas.drawLine(0, lineY, this.getWidth(), lineY, paint);
            }
        }

}
