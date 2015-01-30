package com.example.administrator.daiylywriting.MyOwnViews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.administrator.daiylywriting.R;

/**
 * Created by Administrator on 2015/1/18.
 */
public class MyDialog {
    private Context context;
    private int style;

    public Dialog createDialog(Context context, int style) {
        this.context = context;
        this.style = style;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout dialogView = (LinearLayout) inflater.inflate(R.layout.dialog_edit, null);
        final Dialog customDialog = new Dialog(context, style);

        WindowManager.LayoutParams localLayoutParams = customDialog.getWindow().getAttributes();
        localLayoutParams.gravity = Gravity.BOTTOM | Gravity.LEFT;

        int screenWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        dialogView.setMinimumWidth(screenWidth);
        // dialogView.setMinimumHeight(10);

        customDialog.onWindowAttributesChanged(localLayoutParams);
        customDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        );
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.setContentView(dialogView);
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                customDialog.show();
                InputMethodManager imm = (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return customDialog;
    }

}
