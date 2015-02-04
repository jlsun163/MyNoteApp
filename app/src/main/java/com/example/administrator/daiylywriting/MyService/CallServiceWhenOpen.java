package com.example.administrator.daiylywriting.MyService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/2/1.
 */
public class CallServiceWhenOpen extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context,SearchBookStatus.class);
        context.startService(service);
    }
}
