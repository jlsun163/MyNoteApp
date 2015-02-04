package com.example.administrator.daiylywriting.FragmentPage.Fragment_Main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Administrator on 2014/12/26.
 */
public class Fragment_Charts extends BaseFragment {
    private static int SEVENDAY=7;
    private static int TODAY=6;
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
    private com.example.administrator.daiylywriting.MyChartsView.PieChartTwo mBookPieChart;
    private Handler handler =new Handler();
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
        if (greenDaoService.getAllDateBigData().size()>0) {
            mBookPieChart.initPieData(getSevenDays());
        }
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
        int totalNumbers = 0;
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
                System.out.println(100-pieTotal+"today");
            }else {
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
 *The ChartDivGroupButton Interactions
 */
      private void mChartsRadioInteractionsSet(View view){
          view.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  initMChartsColor();
                switch (v.getId()){
                    case R.id.chartsRadio_DailyNumbersDiv:
                       pieChart.setTitle("字数统计");
                        mBookPieChart.invalidate();
                        mChartsRadio_DailyNumbersText.setTextColor(Color.parseColor("#49dafa"));
                        break;
                    case R.id.chartsRadio_ClickDiv:
                        pieChart.setTitle("点击统计");
//                         initSearchWeb();
                        getActivity().startService(new Intent(getActivity(),SearchBookStatus.class));
//                        getActivity().stopService(new Intent(getActivity(),SearchBookStatus.class));
//                        mBookPieChart.invalidate();
                        mChartsRadio_ClickText.setTextColor(Color.parseColor("#49dafa"));
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

    private void initSearchWeb() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://www.qidian.com/Book/3347952.aspx").timeout(15000).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(doc);
                        if (doc!=null) {
                        Element linkName = doc.select("h1[itemprop]").first();
                        Element linkNumber = doc.select("span[itemprop$=wordCount]").first();
                        Element linkStatus = doc.select("span[itemprop$=updataStatus]").first();
                        Element linkClick = doc.select("span[itemprop$=totalClick]").first();
                        Element linkRecommend = doc.select("span[itemprop$=totalRecommend]").first();
                        SomeStaticThing.toastSomthing(getActivity(),"网络端获取可得:"+linkName.text()+linkNumber.text()+"字"+linkStatus.text()+linkClick.text()+"点击"+linkRecommend.text()+"推荐");
                        }
                    }
                });

            }
        }).start();


    }

    private void initMChartsColor(){
        mChartsRadio_DailyNumbersText.setTextColor(Color.GRAY);
        mChartsRadio_ClickText.setTextColor(Color.GRAY);
        mChartsRadio_CollectionText.setTextColor(Color.GRAY);
    }

}
