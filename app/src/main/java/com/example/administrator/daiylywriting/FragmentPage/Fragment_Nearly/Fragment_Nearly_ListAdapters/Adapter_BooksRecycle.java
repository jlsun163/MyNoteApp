package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.BooksVaules;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.BooksSqilte.NowHappents;
import com.example.administrator.daiylywriting.MyOwnViews.MyDialog;
import com.example.administrator.daiylywriting.MyOwnViews.MyVerTextView;
import com.example.administrator.daiylywriting.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2015/1/3.
 */
public class Adapter_BooksRecycle extends RecyclerView.Adapter<Adapter_BooksRecycle.BookViewHolder> {
    ArrayList<BooksVaules> bookModels = new ArrayList<>();
    private List<CardView> selectedViews = new LinkedList<>();
    private Context mContext;
    private static int CREAT_NEW_BOOK = 0;
    private static int CREAT_NEW_CHAPTER = 1;
    private GreenDaoService greenDaoService;
    private RecyclerView chapterRecycleView;
    private TextView nameTextView;
    private View addChapterView;

    public Adapter_BooksRecycle(Context mContext, ArrayList<BooksVaules> bookModels, RecyclerView chapterRecycleView) {
        super();
        this.mContext = mContext;
        this.bookModels = bookModels;
        this.chapterRecycleView = chapterRecycleView;
/**
 *load books from sqlite by greenDao
 */
        greenDaoService = GreenDaoService.getGreenDaoService(mContext);
        bookModels.addAll(greenDaoService.loadAllBookVaules());


        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addChapterView = layoutInflater.inflate(R.layout.div_newchapteradd, null);
        nameTextView = (TextView) addChapterView.findViewById(R.id.textView_AddNewChapter);
        addChapterView.setVisibility(View.GONE);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_bookcard, viewGroup, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder viewHolder, final int i) {
        if (i == (bookModels.size())) {
            viewHolder.bookNameText.setTextSize(8);
            viewHolder.bookNameText.setTextColor(Color.GRAY);
            viewHolder.bookNameText.setText("+添加新书+");
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            viewHolder.bookNameText.setLayoutParams(params);
            viewHolder.bookDiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createDialogToAddChapter(null, null, CREAT_NEW_BOOK);
                }
            });
        } else {
            viewHolder.bookNameText.setTextColor(Color.BLACK);
            viewHolder.bookNameText.setTextSize(8);
            viewHolder.bookNameText.setText(bookModels.get(i).getBookName());
            viewHolder.bookDiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    setSelectorList(viewHolder.cardView);
                    YoYo.with(Techniques.Wobble).duration(700).playOn(viewHolder.cardView);
                    initChapterListView(bookModels.get(i));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        /**
         *  It need one more length for adapter ,because the first item is used to be the addPart!
         */
        return bookModels.size() + 1;
    }

    // 重写的自定义ViewHolder
    public static class BookViewHolder
            extends RecyclerView.ViewHolder {
        private MyVerTextView bookNameText;
        private RelativeLayout bookDiv;
        private CardView cardView;

        public BookViewHolder(View v) {
            super(v);
            bookNameText = (MyVerTextView) v.findViewById(R.id.textNameShow);
            bookDiv = (RelativeLayout) v.findViewById(R.id.bookApparence);
            cardView = (CardView) v.findViewById(R.id.cardview);
        }
    }

    public void initChapterListView(final BooksVaules booksVaules) {
        final String bookKey = booksVaules.getBookKey();
        final List<Charpters> charpterses = new LinkedList<>();
        charpterses.clear();
        charpterses.addAll(greenDaoService.getChaptersOfBook(bookKey));
        System.out.println(charpterses.size());
        final Adapter_ChapterRecycle chapterRecycleAdapter = new Adapter_ChapterRecycle(mContext, charpterses, booksVaules.getBookName());
        if (charpterses.size() > 0) {
            addChapterView.setVisibility(View.VISIBLE);
            chapterRecycleView.setAdapter(chapterRecycleAdapter);
        } else {
            addChapterView.setVisibility(View.VISIBLE);
            chapterRecycleView.setAdapter(chapterRecycleAdapter);
            createDialogToAddChapter(chapterRecycleAdapter, bookKey, CREAT_NEW_CHAPTER);
        }

    }

    //无脑选中大法
 /*   private void setSelectorList(CardView view) {
        if (selectedViews.size() == 0) {
            selectedViews.add(view);
            view.setCardBackgroundColor(Color.parseColor("#2d64b3"));
        } else {
            selectedViews.get(0).setCardBackgroundColor(Color.WHITE);
            selectedViews.remove(0);
            selectedViews.add(view);
            view.setCardBackgroundColor(Color.parseColor("#2d64b3"));
        }
    }*/

    private void createDialogToAddChapter(final Adapter_ChapterRecycle chapterAdapter, final String bookkey, final int method) {
        MyDialog myDialog = new MyDialog();
        final Dialog dialog = myDialog.createDialog(mContext, R.style.popupDialog);
        Window window = dialog.getWindow();
        final EditText bookNameVaule = (EditText) window.findViewById(R.id.dialog_Vaule);
        final TextView okTextView = (TextView) window.findViewById(R.id.dialog_confirm);
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (method == CREAT_NEW_CHAPTER) {
                    addChapterToDataBase(bookkey, bookNameVaule.getText().toString(), chapterAdapter);
                } else {
                    addBookToDataBase(bookNameVaule.getText().toString());
                }
                dialog.dismiss();
            }
        });
        okTextView.setClickable(false);
        if (method == CREAT_NEW_BOOK) {
            bookNameVaule.setHint("为小说想个名字吧！");
        }
        bookNameVaule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    StringTokenizer st1 = new StringTokenizer(s.toString());
                    String str = "";
                    while (st1.hasMoreTokens()) {
                        str = str + st1.nextToken(" ");
                    }
                    str = str.trim();
                    if (str.length() > 0) {
                        okTextView.setBackgroundResource(R.drawable.buttonrandiusbordergreen);
                        okTextView.setTextColor(Color.WHITE);
                        okTextView.setClickable(true);
                    } else {
                        okTextView.setBackgroundResource(R.drawable.buttonradiusborder);
                        okTextView.setTextColor(Color.parseColor("#444444"));
                        okTextView.setClickable(false);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

    /**
     * to refresh and add the item
     */
    private void addBookItem(BooksVaules booksNameVaules, int position) {
        bookModels.add(position, booksNameVaules);
        notifyItemInserted(position);
    }

    private void addBookToDataBase(String bookName) {
        List<BooksVaules> booksVauleses = new LinkedList<BooksVaules>();
        BooksVaules booksVaules = new BooksVaules();
        booksVaules.setBookName(bookName);
        booksVaules.setBookVauleNumbers(0);
        booksVaules.setBookCharpterNumbers(0);
        booksVaules.setBookModel("");
        booksVaules.setId(Long.valueOf(greenDaoService.loadAllBookVaules().size()));
        SimpleDateFormat timeKey = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String timeKeyVaule = timeKey.format(date);
        booksVaules.setBookKey(bookName + timeKeyVaule);
        booksVaules.setIsDeleted(false);
        booksVauleses.add(booksVaules);
        List<NowHappents> nowHappentses = new LinkedList<>();
        if (greenDaoService.loadAllBookVaules().size() == 0) {
            NowHappents nowHappents = new NowHappents();
            nowHappents.setId(Long.valueOf(greenDaoService.loadAllNews().size()));
            nowHappents.setNews("恭喜您,成功创造了第一部小说《" + bookName + "》" + "<br>" + "祝君好运——Fighting!!<br>" + "经验值+100!");
            nowHappents.setTimes(timeKeyVaule);
            nowHappentses.add(nowHappents);
            greenDaoService.saveOrReplacNews(nowHappentses);
        } else {
            NowHappents nowHappents = new NowHappents();
            nowHappents.setId(Long.valueOf(greenDaoService.loadAllNews().size()));
            nowHappents.setNews("恭喜您,成功创造了《" + bookName + "》" + "<br>" + "祝君好运——Fighting!!<br>" + "经验值+60!");
            nowHappents.setTimes(timeKeyVaule);
            nowHappentses.add(nowHappents);
            greenDaoService.saveOrReplacNews(nowHappentses);
        }
        greenDaoService.saveBookLists(booksVauleses);
        addBookItem(booksVaules, bookModels.size());
    }

    private void addChapterToDataBase(String bookkey, String chapterNameVaule, Adapter_ChapterRecycle chapterAdapter) {
        List<Charpters> mCharpterses = new LinkedList<Charpters>();
        mCharpterses.clear();
        Charpters charpters = new Charpters();
        charpters.setBookKey(bookkey);
        charpters.setCharpterName(chapterNameVaule);
        SimpleDateFormat timeKey = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        String timeKeyVaule = timeKey.format(date);
        charpters.setCharpterKey(chapterNameVaule + timeKeyVaule);
        charpters.setChapterVauleNumbers(0);
        charpters.setCharpterVaules("");
        charpters.setCreateTime(timeKeyVaule);
        mCharpterses.add(charpters);
        greenDaoService.saveOrReplacChapters(mCharpterses);
        chapterAdapter.addItem(charpters, 0);
    }
}
