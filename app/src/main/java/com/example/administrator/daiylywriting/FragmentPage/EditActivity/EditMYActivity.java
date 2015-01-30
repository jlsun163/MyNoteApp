package com.example.administrator.daiylywriting.FragmentPage.EditActivity;

import android.content.Intent;
import android.os.HandlerThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BigData;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.MyOwnViews.BaseAcitivty;
import com.example.administrator.daiylywriting.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/28.
 */
public class EditMYActivity extends BaseAcitivty {
    private GreenDaoService greenDaoService;
    private Intent intent;
    private String chapterKey, bookKey, timeToday;
    private android.os.Handler handler = new android.os.Handler();
    private TextView chapterNumberText, chapterSaveText, speedText;
    private com.example.administrator.daiylywriting.MyOwnViews.LineEditText lineEditText;
    private int timeAddNumber, textAddNumber = 0;
    private ImageView menuButton;
    private Boolean Show = false;
    private ImageView rightButton, leftButton, webButton, noteButton;
    private SlidingDrawer slidingWeb, slidingModle;
    private WebView webView;
    private EditText modelEdit;
    private int TodayAddNumber;
    private List<String> theTextVauleTrace = new LinkedList<>();
    private int textChangeSign = 0;
    private int speed = 0;
    private int viewHeight = 0;
    private Boolean isFinished = false;
    private android.os.Handler speedHandler, saveHandler;
    private HandlerThread speedThread, saveThread;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit);
    }

    @Override
    public void findViews() {
        //webView for search data for books in Internet in the SlidingView
        slidingWeb = (SlidingDrawer) findViewById(R.id.slidingWeb);
        webView = (WebView) findViewById(R.id.editWebView);
        lineEditText = (com.example.administrator.daiylywriting.MyOwnViews.LineEditText) findViewById(R.id.articleEditView);

        /**
         *show div int the top of Acitivy layout like speed allChapterVauleNumbers and saving status
         */
        chapterNumberText = (TextView) findViewById(R.id.chapterVaule);
        chapterSaveText = (TextView) findViewById(R.id.savedTitle);
        speedText = (TextView) findViewById(R.id.speedVaule);

        /**
         * sliding page  for bookmodel
         */
        modelEdit = (EditText) findViewById(R.id.modelEdit);
        modelEdit.getBackground().setAlpha(200);
        slidingModle = (SlidingDrawer) findViewById(R.id.slidingModel);

/**
 *     A simple menu like pathMenu
 */
        menuButton = (ImageView) findViewById(R.id.menuAdd);
        menuButton.setAlpha(220);
        rightButton = (ImageView) findViewById(R.id.pathRight);
        leftButton = (ImageView) findViewById(R.id.pathLeft);
        webButton = (ImageView) findViewById(R.id.pathWeb);
        noteButton = (ImageView) findViewById(R.id.pathNote);
    }

    @Override
    public void getData() {
        //greenDaoSqlite
        greenDaoService = GreenDaoService.getGreenDaoService(getApplicationContext());
        /**
         *Intent vaule to get the chapterKey and bookKey for search the Vaule of corrently chapter
         */
        intent = getIntent();
        chapterKey = (String) intent.getSerializableExtra("chapterkey");
        bookKey = (String) intent.getSerializableExtra("bookkey");
        /**
         *       init BigData  for the data of the Writer ,Now it just hast daily writing Number
         */
        List<BigData> bigDatas = new LinkedList<>();
        Date today = new Date();
        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
        timeToday = matter.format(today);
        bigDatas = greenDaoService.getOneDateBigData(timeToday);
        if (bigDatas.size() == 0) {
            BigData bigData = new BigData();
            TodayAddNumber = 0;
            bigData.setOneDayNumber(TodayAddNumber);
            bigData.setTimeDate(timeToday);
            greenDaoService.saveOrReplacBigData(bigData);
        } else {
            TodayAddNumber = bigDatas.get(0).getOneDayNumber();
        }
    }

    @Override
    public void showContent() {
        /**
         *chapterVaule show
         */
        initThreads();
        chapterNumberText.setText(greenDaoService.getTheOnlyChapters(chapterKey).get(0).getChapterVauleNumbers().toString() + "字");
        lineEditText.setText(greenDaoService.getTheOnlyChapters(chapterKey).get(0).getCharpterVaules());
    }

    @Override
    public void setInteract() {
        chapterEditInteraction();
        menuAnimationAndInteraction();
        slidingInteraction();
    }


    private void initThreads() {
        speedThread = new HandlerThread("mySpeedThread");
        saveThread = new HandlerThread("mySaveThread");
        speedThread.start();
        saveThread.start();
        speedHandler = new android.os.Handler(speedThread.getLooper());
        saveHandler = new android.os.Handler(saveThread.getLooper());
        saveHandler.post(saveRunnable);
        speedHandler.post(speedRunable);
    }

    private void slidingInteraction() {
        /**
         *WebButton set
         */
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingWeb.toggle();
            }
        });
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingModle.toggle();
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // 在当前的webview中跳转到新的url
                return true;
            }
        });

    }


    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && slidingWeb.isOpened()) {
            slidingWeb.toggle();
            return true;
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void menuAnimationAndInteraction() {
        /**
         *the Right and Left RotateAnimation of mainMenuButton  for the MenuList Show of Hide
         */
        final Animation rIn = new RotateAnimation(0f, +45f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rIn.setDuration(100);
        rIn.setFillAfter(true);
        final Animation lIn = new RotateAnimation(+45f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        lIn.setDuration(100);
        lIn.setFillAfter(true);
/**
 *setParamLayout for buttons
 */
        setPara(rightButton);
        setPara(leftButton);
        setPara(webButton);
        setPara(noteButton);
        setPara(menuButton);

//    ButtonList  and animationList
        ImageView[] imageViews = {rightButton, leftButton, webButton, noteButton, rightButton, leftButton, webButton, noteButton};
        TranslateAnimation[] transformationpath = new TranslateAnimation[8];

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setAlpha(220);
            int a = i + 1;
            if (i < 4) {
                transformationpath[i] = getPlayHideAnimate(a, imageViews[i]);
            } else {
                if (i == 4) {
                    a = 1;
                } else if (i == 5) {
                    a = 2;
                } else if (i == 6) {
                    a = 3;
                } else {
                    a = 4;
                }
                transformationpath[i] = getPlayTheAnimate(a, imageViews[i]);
            }
        }

        final AnimationSet[] animationSets = new AnimationSet[8];
        for (int i = 0; i < animationSets.length; i++) {
            AnimationSet animations = new AnimationSet(true);
            animations.addAnimation(transformationpath[i]);
            animationSets[i] = animations;
        }
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHeight = v.getHeight();
                if (Show) {
                    v.startAnimation(lIn);
                    /**
                     * menu hide animation
                     */
                    rightButton.startAnimation(animationSets[0]);
                    leftButton.startAnimation(animationSets[1]);
                    webButton.startAnimation(animationSets[2]);
                    noteButton.startAnimation(animationSets[3]);
                } else {
                    v.startAnimation(rIn);
                    /**
                     * menu show animation
                     */
                    rightButton.startAnimation(animationSets[4]);
                    leftButton.startAnimation(animationSets[5]);
                    webButton.startAnimation(animationSets[6]);
                    noteButton.startAnimation(animationSets[7]);
                }
            }
        });
    }

    private void chapterEditInteraction() {
        lineEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textAddNumber = textAddNumber + count;
                TodayAddNumber = TodayAddNumber + count;
            }

            @Override
            public void afterTextChanged(Editable s) {
                chapterNumberText.setText(s.length() + "字");
                if (textChangeSign == 10) {
                    textChangeSign = 0;
                }
                if (theTextVauleTrace.size() < 10) {
                    theTextVauleTrace.add(s.toString());
                } else {
                    theTextVauleTrace.set(textChangeSign, s.toString());
                }

            }
        });
        modelEdit.setText(greenDaoService.getOneBook(bookKey).get(0).getBookModel());

    }


    private void setPara(View v) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
        params.bottomMargin = (int) (20);
        v.setLayoutParams(params);
    }

    private TranslateAnimation getPlayTheAnimate(final int n, final View v) {
        TranslateAnimation mShowAnimation = new TranslateAnimation(v.getLeft(), 0, v.getRight(), -n * (viewHeight + 20) - 80);
        mShowAnimation.setDuration(100);
        mShowAnimation.setFillAfter(false);
        mShowAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.clearAnimation();
                v.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
                params.bottomMargin = (int) (80 + 20 + (n - 1) * viewHeight + n * 20);
                v.setLayoutParams(params);
                Show = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return mShowAnimation;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFinished = true;
    }

    private TranslateAnimation getPlayHideAnimate(int n, final View v) {
        TranslateAnimation mHideAnimation = new TranslateAnimation(v.getLeft(), 0, v.getRight(), +80 + n * viewHeight);
        mHideAnimation.setDuration(100);
        mHideAnimation.setFillAfter(true);
        mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.clearAnimation();
                v.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
                params.bottomMargin = (int) (20);
                v.setLayoutParams(params);
                Show = false;
//                circleImageView.layout(circleImageView.getLeft(),0,circleImageView.getRight(),-100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        return mHideAnimation;
    }

    Runnable speedRunable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    if (isFinished) {
                        break;
                    }
                    Thread.sleep(1000);
                    timeAddNumber++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable saveRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (true) {
                    if (isFinished) {
                        break;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chapterSaveText.setText("保存中...");
                        }
                    });

                    Thread.sleep(2000);
                    updatechapter();
                    Thread.sleep(1000);
                    speed = textAddNumber * 3600 / timeAddNumber;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            speedText.setText(speed + "字/小时");
                        }
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void updatechapter() {
        List<BigData> bigDatas = greenDaoService.getOneDateBigData(timeToday);
        BigData bigData = bigDatas.get(0);
        bigData.setOneDayNumber(TodayAddNumber);
        greenDaoService.saveOrReplacBigData(bigData);
        Charpters charpters = greenDaoService.getTheOnlyChapters(chapterKey).get(0);
        System.out.println(charpters.getId() + "" + charpters.getCharpterKey() + "cicun" + lineEditText.getText().toString());
        charpters.setCharpterVaules(lineEditText.getText().toString());
        charpters.setChapterVauleNumbers(lineEditText.getText().length());
        greenDaoService.upDateChapters(charpters);
        List<BooksVaules> booksVauleses = new LinkedList<>();
        booksVauleses.clear();
        BooksVaules booksVaules = new BooksVaules();
        booksVaules = greenDaoService.getOneBook(bookKey).get(0);
        booksVaules.setBookModel(modelEdit.getText().toString());
        booksVauleses.add(booksVaules);
        greenDaoService.saveBookLists(booksVauleses);
        handler.post(new Runnable() {
            @Override
            public void run() {
                chapterSaveText.setText("已保存!");
            }
        });

    }
}
