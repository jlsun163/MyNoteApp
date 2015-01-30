package com.example.administrator.daiylywriting.MyOwnViews;

/**
 * Created by Administrator on 2014/12/20.
 */
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class RuledLineEditText extends EditText
{
    private Paint a;
    private Rect b;
    private boolean c;
    private int d;

    public RuledLineEditText(Context paramContext)
    {
        super(paramContext);
        a(paramContext);
    }

    public RuledLineEditText(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        a(paramContext);
    }

    public RuledLineEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        a(paramContext);
    }

    private void a(Context paramContext)
    {

        if (true)
        {
            this.a = new Paint();
            this.b = new Rect();

            this.a.setColor(Color.rgb(68, 68, 68));
        }
        while (true)
        {
            this.a.setColor( Color.rgb(68, 68, 68));
            return;

        }
    }

    protected void onDraw(Canvas paramCanvas)
    {
        int i=0;
        int j = 0;
        int k = 0;
        if (true)
        {
            getLineCount();
            if (this.d == 0)
            {
                String str = getText().toString();
                setText("\n\n\n");
                this.d = (getLineBounds(1, this.b) - getLineBounds(0, this.b));
                setText(str);
            }
            if (this.d != 0)
            {
                i = getMeasuredHeight() / this.d;
                j = 1 + getLineBounds(0, this.b);
                k = 0;
            }
        }
        int i1;
        for (int l = j; ; l = i1)
        {
            if (k >= i)
            {
                super.onDraw(paramCanvas);
                return;
            }
            paramCanvas.drawLine(this.b.left, l, this.b.right, l, this.a);
            i1 = l + this.d;
            ++k;
        }
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        try
        {
            boolean bool = super.onTouchEvent(paramMotionEvent);
            return bool;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
        }
        return false;
    }
}