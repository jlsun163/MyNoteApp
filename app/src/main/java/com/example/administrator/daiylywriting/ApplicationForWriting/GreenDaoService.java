package com.example.administrator.daiylywriting.ApplicationForWriting;

import android.content.Context;

import com.example.administrator.daiylywriting.BooksSqilte.BigData;
import com.example.administrator.daiylywriting.BooksSqilte.BigDataDao;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaulesDao;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.BooksSqilte.CharptersDao;
import com.example.administrator.daiylywriting.BooksSqilte.DaoSession;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappentsDao;
import com.example.administrator.daiylywriting.BooksSqilte.WebData;
import com.example.administrator.daiylywriting.BooksSqilte.WebDataDao;
import com.example.administrator.daiylywriting.FragmentPage.Fragment_Charts.ChartsData.WebBookDatas;

import java.util.List;

/**
 * Created by Administrator on 2015/1/6.
 */
public class GreenDaoService {
    private static final String TAG=GreenDaoService.class.getSimpleName();
    private static GreenDaoService greenDaoService;
    private static Context appContext;
    private DaoSession daoSession;
    private BooksVaulesDao booksVaulesDao;
    private NowHappentsDao nowHappentsDao;
    private CharptersDao charptersDao;
    private BigDataDao bigDataDao;
    private WebDataDao webDataDao;
    private GreenDaoService(){}
    public static GreenDaoService getGreenDaoService(Context context){
        if (greenDaoService==null){
            greenDaoService =new GreenDaoService();
            if (appContext==null){
                appContext=context.getApplicationContext();
            }
            greenDaoService.daoSession=GreenDaoApplication.getDaoSession(context);
            //书名的Service
            greenDaoService.booksVaulesDao=greenDaoService.daoSession.getBooksVaulesDao();
            //动态的Service
            greenDaoService.nowHappentsDao=greenDaoService.daoSession.getNowHappentsDao();
            //章节的Servive
            greenDaoService.charptersDao=greenDaoService.daoSession.getCharptersDao();
            greenDaoService.bigDataDao=greenDaoService.daoSession.getBigDataDao();
            greenDaoService.webDataDao=greenDaoService.daoSession.getWebDataDao();
        }
        return greenDaoService;
    }

    /**
     * ***************************BOOKS_VAULE START**************************************
     */

    public BooksVaules loadBooksVaule(Long id){
        return booksVaulesDao.load(id);
    }

    public List<BooksVaules> loadAllBookVaules(){
        return booksVaulesDao.queryBuilder().where(BooksVaulesDao.Properties.IsDeleted.eq(false)).list();
    }
    /**
     * query list with where clause
     * ex: begin_date_time >= ? AND end_date_time <= ?
     * @param where where clause, include 'where' word
     * @param params query parameters
     * @return
     */

    public List<BooksVaules> queryBook(String where, String... params){
        return booksVaulesDao.queryRaw(where, params);
    }
    public List<BooksVaules> getOneBook(String BookKeyStr){
        return booksVaulesDao.queryBuilder().where(BooksVaulesDao.Properties.BookKey.eq(BookKeyStr)).list();
    }
    public void deleteAllBookvaules(){
        booksVaulesDao.deleteAll();
    }
    /**
     * insert or update noteList use transaction
     * @param list
     */
    public void saveBookLists(final List<BooksVaules> list){
        if(list == null || list.isEmpty()){
            return;
        }
        booksVaulesDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    BooksVaules booksVaules = list.get(i);
                    booksVaulesDao.insertOrReplace(booksVaules);
                }
            }
        });

    }
    /**
     * ***************************BOOKS_VAULE END**************************************
     */

    /**
     * ***************************NOW_HAPPENS START**************************************
     */
            public List<NowHappents> loadAllNews(){
                return nowHappentsDao.loadAll();
            }
    /**
     * insert or update noteList use transaction
     * @param list
     */
    public void saveOrReplacNews(final List<NowHappents> list){
        if(list == null || list.isEmpty()){
            return;
        }
        nowHappentsDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<list.size(); i++){
                    NowHappents nowHappents = list.get(i);
                    nowHappentsDao.insertOrReplace(nowHappents);
                }
            }
        });

    }
    /**
     * ***************************NOW_HAPPENS START**************************************
     */


    /**
     * ***************************Chpaters START**************************************
     */

    /**
     * query list with where clause
     * ex: begin_date_time >= ? AND end_date_time <= ?
     * @param where where clause, include 'where' word
     * @param params query parameters
     * @return
     */

    public List<Charpters> queryChapter(String where, String... params){
        return charptersDao.queryRaw(where, params);
    }

     public List<Charpters> getChaptersOfBook(String BookKeyStr){
         return charptersDao.queryBuilder().where(CharptersDao.Properties.BookKey.eq(BookKeyStr)).where(CharptersDao.Properties.IsDeleted.eq(false)). list();
     }
    public List<Charpters> getTheOnlyChapters(String ChapterKey){
        return charptersDao.queryBuilder().where(CharptersDao.Properties.CharpterKey.eq(ChapterKey)).list();
    }
    /**
     * insert or update noteList use transaction
     * @param list
     */
    public void saveOrReplacChapters(final List<Charpters> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        charptersDao.getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    Charpters charpters = list.get(i);
                    charptersDao.insertOrReplace(charpters);
                }
            }
        });
    }


    public void upDateChapters(Charpters... entities){
        charptersDao.updateInTx(entities);
    }
    /**
     * ***************************Chapters  END**************************************
     */

//    /*存入当天字数*/
//    public void saveOrReplacBigData(final List<BigData> list) {
//        if (list == null || list.isEmpty()) {
//            return;
//        }
//        bigDataDao.getSession().runInTx(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < list.size(); i++) {
//                    BigData bigData = list.get(i);
//                    bigDataDao.insertOrReplace(bigData);
//                }
//            }
//        });
//    }


        /*存入当天字数*/
    public void saveOrReplacBigData(BigData bigData) {
                    bigDataDao.insertOrReplace(bigData);
    }

    public List<BigData> getOneDateBigData(String dateKey){
        return bigDataDao.queryBuilder().where(BigDataDao.Properties.TimeDate.eq(dateKey)).list();
    }

    public List<BigData> getAllDateBigData(){
        return bigDataDao.loadAll();
    }

  /*save the data get from web*/
  public void saveOrReplacWebData(WebData webData) {
      webDataDao.insertOrReplace(webData);
  }

  public List<WebData> getAllWebData(){
      return webDataDao.loadAll();
  }

  public List<WebData> getDayWebData(String dateKey){
      return webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).list();
  }
  public WebBookDatas getParticularWebData(String dateKey){
      WebBookDatas webBookDatas =new WebBookDatas();
      webBookDatas.setWebData0to9(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(0,8)).list());
      webBookDatas.setWebData9to11(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(9,10)).list());
      webBookDatas.setWebData11to13(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(11,12)).list());
      webBookDatas.setWebData13to15(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(13,14)).list());
      webBookDatas.setWebData15to18(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(15,17)).list());
      webBookDatas.setWebData18to20(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(18,19)).list());
      webBookDatas.setWebData20to22(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(20,21)).list());
      webBookDatas.setWebData22to24(webDataDao.queryBuilder().where(WebDataDao.Properties.TimeDay.eq(dateKey)).where(WebDataDao.Properties.TimeHour.between(22,23)).list());
      return webBookDatas;
  }

}
