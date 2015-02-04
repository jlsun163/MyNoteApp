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

/**
 * Created by Administrator on 2015/2/1.
 */
public class SearchBookStatus extends Service {
    private Handler handler =new Handler();
    private Document doc = null;
    public static String SERVICE_NAME ="com.example.administrator.daiylywriting.MyService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public class LocalBinder extends Binder {
        SearchBookStatus  getService() {
            return SearchBookStatus.this;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("start");
        initSearchWeb();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }


    private void initSearchWeb() {
        System.out.println("start100");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    while (true) {
                        try {
                            doc=null;
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
                                  GreenDaoService greenDaoService=GreenDaoService.getGreenDaoService(SearchBookStatus.this);
                                    WebData webData=new WebData();
                                    webData.setBookName(linkName.text().toString());
                                    webData.setClickNumber(Integer.valueOf(linkClick.text().toString()));
                                    webData.setLikeNumber(Integer.valueOf(linkRecommend.text().toString()));
                                    greenDaoService.saveOrReplacWebData(webData);
                                    SomeStaticThing.toastSomthing(SearchBookStatus.this, "网络端获取可得:" + linkName.text() + linkNumber.text() + "字" + linkStatus.text() + linkClick.text() + "点击" + linkRecommend.text() + "推荐");
                                } else {

                                }
                            }
                        });
                        if (doc!=null){
                            break;

                        }
                    }
                    try {
                        Thread.sleep(1000 * 60 * 30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

}
