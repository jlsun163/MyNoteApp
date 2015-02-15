package com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData.WebBookDatas;
import com.example.administrator.daiylywriting.MyChartsView.MySplineChart;
import com.example.administrator.daiylywriting.MyChartsView.PieChartTwo;
import com.example.administrator.daiylywriting.MyChartsView.PieDateStruct;
import com.example.administrator.daiylywriting.MyOwnViews.BaseFragment;
import com.example.administrator.daiylywriting.MyService.CallServiceWhenOpen;
import com.example.administrator.daiylywriting.MyService.SearchBookStatus;
import com.example.administrator.daiylywriting.OtherActivity.SomeStaticThing;
import com.example.administrator.daiylywriting.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Charts extends BaseFragment {
    private static int SEVENDAY = 7;
    private static int TODAY = 6;
    private PieChart pieChart;
    private List<PieData> chartData = new LinkedList<>();
    private GreenDaoService greenDaoService;
    private RelativeLayout mChartsRadio_DailyNumbersDiv;
    private TextView mChartsRadio_DailyNumbersImg;
    private TextView mChartsRadio_DailyNumbersText;
    private RelativeLayout mChartsRadio_ClickDiv;
    private TextView mChartsRadio_ClickImg;
    private TextView mChartsRadio_ClickText;
    private RelativeLayout mChartsRadio_CollectionDiv;
    private TextView mChartsRadio_CollectionImg;
    private TextView mChartsRadio_CollectionText;
    private LinearLayout mChartsDiv;
    private MySplineChart webDataChart;
    private com.example.administrator.daiylywriting.MyChartsView.PieChartTwo mBookPieChart;
    private Handler handler = new Handler();
    private Document doc = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }

    @Override
    public void findViews() {
        mChartsRadio_DailyNumbersDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_DailyNumbersDiv);
        mChartsRadio_DailyNumbersImg = (TextView) getActivity().findViewById(R.id.chartsRadio_DailyNumbersImg);
        mChartsRadio_DailyNumbersText = (TextView) getActivity().findViewById(R.id.chartsRadio_DailyNumbersText);
        mChartsRadio_ClickDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_ClickDiv);
        mChartsRadio_ClickImg = (TextView) getActivity().findViewById(R.id.chartsRadio_ClickImg);
        mChartsRadio_ClickText = (TextView) getActivity().findViewById(R.id.chartsRadio_ClickText);
        mChartsRadio_CollectionDiv = (RelativeLayout) getActivity().findViewById(R.id.chartsRadio_CollectionDiv);
        mChartsRadio_CollectionImg = (TextView) getActivity().findViewById(R.id.chartsRadio_CollectionImg);
        mChartsRadio_CollectionText = (TextView) getActivity().findViewById(R.id.chartsRadio_CollectionText);
        mChartsDiv = (LinearLayout) getActivity().findViewById(R.id.chartsDiv);
//        webDataChart= (MySplineChart) getActivity().findViewById(R.id.webDataChart);
        mBookPieChart = (com.example.administrator.daiylywriting.MyChartsView.PieChartTwo) getActivity().findViewById(R.id.bookPieChart);
    }

    @Override
    public void getData() {
        greenDaoService = GreenDaoService.getGreenDaoService(getActivity());
        pieChart = mBookPieChart.getChart();
    }


    @Override
    public void showContent() {

        //设置图标数据
        if (greenDaoService.getAllDateBigData().size() > 0) {
            mBookPieChart.initPieData(getSevenDays());
        }
//        initSplineChartsForWebBook();
    }

    @Override
    public void setInteract() {
        mChartsRadioInteractionsSet(mChartsRadio_DailyNumbersDiv);
        mChartsRadioInteractionsSet(mChartsRadio_ClickDiv);
        mChartsRadioInteractionsSet(mChartsRadio_CollectionDiv);
    }

    @Override
    public void reStartInit() {
        //设置图标数据
        mBookPieChart.initPieData(getSevenDays());
    }


    /**
     * Get seven DayVaules
     */
    private List<PieDateStruct> getSevenDays() {
        int totalNumbers = 1;
        List<Integer> dayNumbers = new LinkedList<>();
        List<PieDateStruct> pieDateStructs = new LinkedList<>();
        for (int i = 0; i < SEVENDAY; i++) {
            //Every *24*60*60*1000 is one day
            Date today = new Date(new Date().getTime() - i * 24 * 60 * 60 * 1000);
            SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
            String timeToday = matter.format(today);
            PieDateStruct pieDateStruct = new PieDateStruct();
            if (i == 0) {
                pieDateStruct.setPieDataName("今天");
            } else {
                pieDateStruct.setPieDataName(timeToday);
            }
            pieDateStructs.add(0, pieDateStruct);
            /**
             * get Total number in the last seven days
             */
            if (greenDaoService.getOneDateBigData(timeToday).size() > 0) {
                dayNumbers.add(0, greenDaoService.getOneDateBigData(timeToday).get(0).getOneDayNumber());
                totalNumbers = totalNumbers + greenDaoService.getOneDateBigData(timeToday).get(0).getOneDayNumber();
            } else {
                totalNumbers = totalNumbers + 0;
                dayNumbers.add(0, 0);
            }
        }
        /**
         * the percent vaule of the pie chart
         */
        int pieTotal = 0;
        for (int i = 0; i < 7; i++) {


            if (i == TODAY) {
                pieDateStructs.get(i).setPieDateNumber(100 - pieTotal);
                System.out.println(100 - pieTotal + "today");
            } else {
                if (totalNumbers == 0) {
                    totalNumbers = 1;
                }
                pieDateStructs.get(i).setPieDateNumber(dayNumbers.get(i) * 100 / totalNumbers);
                if (dayNumbers.get(i) * 100 / totalNumbers == 0) {
                    pieDateStructs.get(i).setPieDateNumber(0);
                } else {
                    pieTotal = pieTotal + dayNumbers.get(i) * 100 / totalNumbers;
                }

            }

            pieDateStructs.get(i).setPieDateVaule(dayNumbers.get(i) + "字:(" + pieDateStructs.get(i).getPieDateNumber() + "%)");
            System.out.println(dayNumbers.get(i) + "字:(" + pieDateStructs.get(i).getPieDateNumber() + "%)");
        }
        return pieDateStructs;
    }

    /**
     * The ChartDivGroupButton Interactions
     */
    private void mChartsRadioInteractionsSet(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMChartsColor();
                switch (v.getId()) {
                    case R.id.chartsRadio_DailyNumbersDiv:
                        pieChart.setTitle("字数统计");
                        mBookPieChart.invalidate();
                        mChartsRadio_DailyNumbersText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                    case R.id.chartsRadio_ClickDiv:
                        startAlarmService();
                        break;
                    case R.id.chartsRadio_CollectionDiv:
                        pieChart.setTitle("收藏统计");
                        mBookPieChart.invalidate();
                        mChartsRadio_CollectionText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                }

            }
        });
    }


    private void initMChartsColor() {
        mChartsRadio_DailyNumbersText.setTextColor(Color.GRAY);
        mChartsRadio_ClickText.setTextColor(Color.GRAY);
        mChartsRadio_CollectionText.setTextColor(Color.GRAY);
    }

    private void startAlarmService() {
        PendingIntent pend = PendingIntent.getService(getActivity(), 0,
                new Intent(getActivity(), SearchBookStatus.class), 0);
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        long firstTime = SystemClock.elapsedRealtime();
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 60 * 1000 * 120, pend);
        SomeStaticThing.toastSomthing(getActivity(), "开启点击");
//        Intent intent = new Intent(getActivity(), SearchBookStatus.class);
//        // TODO Add extras if required.
//        getActivity().startService(intent);
    }

    /**
     * The chats for webBook
     */
    private void initSplineChartsForWebBook() {
        SplineChart chart = webDataChart.getChart();
        LinkedList<SplineData> chartData = new LinkedList<>();
        chartData.clear();
        SimpleDateFormat timeDayKey = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String timeKeyVaule = timeDayKey.format(date);
        List<WebData> webDatas = greenDaoService.getDayWebData(timeKeyVaule);
//    WebBookDatas webBookDatas=greenDaoService.getParticularWebData(timeKeyVaule);
//////线1的数据集
        List<PointD> linePoint1 = new ArrayList<PointD>();
        for (WebData webData : webDatas) {
            linePoint1.add(new PointD(webData.getTimeHour(), webData.getClickNumber()));
        }


//    if (webBookDatas.getWebData0to9().size()>0) {
//        linePoint1.add(new PointD(0d, webBookDatas.getWebData15to18().get(webBookDatas.getWebData15to18().size()-1).getClickNumber()-webBookDatas.getWebData15to18().get(0).getClickNumber()));
//    }else {
//        linePoint1.add(new PointD(0d, 0d));
//    }
//    linePoint1.add(new PointD(15d, 135d));
//    linePoint1.add(new PointD(25d, 425d));
//    linePoint1.add(new PointD(36d, 535d));
//    if (webBookDatas.getWebData15to18().size()>1) {
//        linePoint1.add(new PointD(45d, webBookDatas.getWebData15to18().get(webBookDatas.getWebData15to18().size()-1).getClickNumber()-webBookDatas.getWebData15to18().get(0).getClickNumber()));
//    }else {
//        linePoint1.add(new PointD(45d, 0d));
//    }
//
//    linePoint1.add(new PointD(55d, 755d));
//    linePoint1.add(new PointD(67d, 865d));
//
//
//    linePoint1.add(new PointD(75d, 975d));
//        linePoint1.add(new PointD(82d, 85d));
//        linePoint1.add(new PointD(90d, 60d));
//        linePoint1.add(new PointD(96d, 68d));

        SplineData dataSeries1 = new SplineData("线一", linePoint1,
                Color.rgb(54, 141, 238));
        //把线弄细点
        dataSeries1.getLinePaint().setStrokeWidth(2);
        dataSeries1.setLabelVisible(true);
        chartData.add(dataSeries1);
        webDataChart.initChartView();
        chart.setDataSource(chartData);
        //坐标系
        //数据轴最大值
        chart.getDataAxis().setAxisMax(webDatas.get(webDatas.size() - 1).getClickNumber() + 1000);
        chart.getDataAxis().setAxisMin(webDatas.get(0).getClickNumber());
        //chart.getDataAxis().setAxisMin(0);
        //数据轴刻度间隔
        chart.getDataAxis().setAxisSteps((webDatas.get(webDatas.size() - 1).getClickNumber() - webDatas.get(0).getClickNumber()) / 10);


        //标签轴最大值
        chart.setCategoryAxisMax(24);
        //标签轴最小值
        chart.setCategoryAxisMin(0);
        webDataChart.postInvalidate();
    }


}
