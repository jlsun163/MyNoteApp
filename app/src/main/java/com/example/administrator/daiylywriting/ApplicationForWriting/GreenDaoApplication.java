package com.example.administrator.daiylywriting.ApplicationForWriting;

import android.app.Application;
import android.content.Context;

import com.example.administrator.daiylywriting.BooksSqilte.DaoMaster;
import com.example.administrator.daiylywriting.BooksSqilte.DaoSession;


/**
 * Created by Administrator on 2015/1/6.
 */
public class GreenDaoApplication extends Application {
    private static GreenDaoApplication mApplication;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mApplication == null) {
            mApplication = this;
        }
    }
/**
 * 获得DaoMaster对象
 */
     public static DaoMaster getDaoMaster(Context context){
           if (daoMaster==null){
               DaoMaster.OpenHelper helper= new DaoMaster.DevOpenHelper(context,"DailyDB",null);
               daoMaster=new DaoMaster(helper.getWritableDatabase());
           }
         return daoMaster;
     }

    /**
     * 获取DaoSession
     */
      public static DaoSession getDaoSession(Context context){
          if (daoSession==null){
              if (daoMaster==null){
                  daoMaster=getDaoMaster(context);
              }
              daoSession=daoMaster.newSession();
          }
            return daoSession;
      }
}