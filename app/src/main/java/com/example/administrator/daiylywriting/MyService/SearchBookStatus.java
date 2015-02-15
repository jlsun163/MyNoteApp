package com.example.administrator.daiylywriting.MyService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2015/2/1.
 */
public class SearchBookStatus extends Service {
    private Handler handler = new Handler();
    private Document doc = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSearchWeb();
        SomeStaticThing.toastSomthing(getApplication(), "create ");

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        SomeStaticThing.toastSomthing(getApplication(), "START ");
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
        // return START_REDELIVER_INTENT;
    }
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        SomeStaticThing.toastSomthing(getApplication(), "START ");
////        initSearchWeb();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SomeStaticThing.toastSomthing(getApplication(), "Kill ");
    }


    private void initSearchWeb() {


        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    doc = null;
                    doc = Jsoup.connect("http://www.qidian.com/Book/3381218.aspx").timeout(15000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (doc != null) {
                            Element linkName = doc.select("h1[itemprop]").first();
                            Element linkNumber = doc.select("span[itemprop$=wordCount]").first();
                            Element linkStatus = doc.select("span[itemprop$=updataStatus]").first();
                            Element linkClick = doc.select("span[itemprop$=totalClick]").first();
                            Element linkRecommend = doc.select("span[itemprop$=totalRecommend]").first();
                            GreenDaoService greenDaoService = GreenDaoService.getGreenDaoService(SearchBookStatus.this);
                            SimpleDateFormat timeKey = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                            SimpleDateFormat timeDayKey = new SimpleDateFormat("yyyy/MM/dd");
                            SimpleDateFormat timeHourKey = new SimpleDateFormat("HH");
                            Date date = new Date();
                            String timeHourVaule = timeHourKey.format(date);
                            String timeDayVaule = timeDayKey.format(date);
                            String timeKeyVaule = timeKey.format(date);
                            WebData webData = new WebData();
                            webData.setBookName(linkName.text().toString());
                            webData.setTimeDate(timeKeyVaule);
                            webData.setTimeHour(Integer.parseInt(timeHourVaule));
                            webData.setTimeDay(timeDayVaule);
                            webData.setClickNumber(Integer.valueOf(linkClick.text().toString()));
                            webData.setLikeNumber(Integer.valueOf(linkRecommend.text().toString()));
                            greenDaoService.saveOrReplacWebData(webData);
                            SomeStaticThing.toastSomthing(SearchBookStatus.this, "网络端获取可得:" + linkName.text() + linkNumber.text() + "字" + linkStatus.text() + linkClick.text() + "点击" + linkRecommend.text() + "推荐");
                            Intent intent=new Intent(SearchBookStatus.this,SearchBookStatus.class);
                            stopService(intent);
                        } else {

                        }
                    }
                });
            }
        }).start();
    }

}
