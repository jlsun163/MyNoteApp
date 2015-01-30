package com.example.administrator.daiylywriting.FragmentPage.Fragment_Nearly.Fragment_Nearly_ListAdapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.daiylywriting.ApplicationForWriting.GreenDaoService;
import com.example.administrator.daiylywriting.BooksSqilte.Charpters;
import com.example.administrator.daiylywriting.FragmentPage.EditActivity.EditMYActivity;
import com.example.administrator.daiylywriting.MyOwnViews.MyDialog;
import com.example.administrator.daiylywriting.R;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2015/1/17.
 */
public class Adapter_ChapterRecycle extends RecyclerView.Adapter<Adapter_ChapterRecycle.ChapterViewHolder>  {
    private Context activity;
    private GreenDaoService greenDaoService;
    private String bookName;
    List<Charpters> charpterses=new LinkedList<>();
    public Adapter_ChapterRecycle(Context activity, List<Charpters> charpterses, String bookName){
        this.activity=activity;
        this.charpterses=charpterses;
        this.bookName=bookName;
        Collections.reverse(charpterses);
        greenDaoService =GreenDaoService.getGreenDaoService(activity);
    }
    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holer = null;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_chaptercard, viewGroup, false);
        return new ChapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ChapterViewHolder holder, final int position) {
        //判断添加文章的头部
        if (position==0){
            holder.chapterAddDiv.setVisibility(View.VISIBLE);
            holder.bookNameText.setText("《" + bookName + "》");

        }else {
            holder.chapterAddDiv.setVisibility(View.GONE);
        }
        /**
         * set The vaule for the list of chapters
         */
        holder.chapterCreateTime.setText(charpterses.get(position).getCreateTime());
        if (charpterses.get(position).getCharpterVaules().length()>0){
            holder.chapterVaule.setText(charpterses.get(position).getCharpterVaules());
        }
        holder.chapterVauleNumber.setText(charpterses.get(position).getChapterVauleNumbers().toString()+"字");
        holder.chapterAddDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogToAddChapter(charpterses.get(position).getBookKey());
            }
        });
        holder.bookChapterName.setText(charpterses.get(position).getCharpterName());

        /**
         * the delete button
         */
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * edit button
         */
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, EditMYActivity.class);
                intent.putExtra("bookkey",charpterses.get(position).getBookKey());
                intent.putExtra("chapterkey",charpterses.get(position).getCharpterKey());

                activity.startActivity(intent);
            }
        });
    }
    public void addItem(Charpters charpters, int position) {
        charpterses.add(position, charpters);
        notifyItemInserted(position); //Attention!
    }
    public void update( int position) {
        notifyItemChanged(position); //Attention!
    }

    @Override
    public int getItemCount() {
        return charpterses.size();
    }

    // 重写的自定义ViewHolder
    public static class ChapterViewHolder
            extends RecyclerView.ViewHolder
    {
        private TextView bookChapterName,bookNameText,deleteButton,pushUpButton,editButton,chapterCreateTime,chapterVauleNumber,chapterVaule;
        private RelativeLayout bookDiv;
        private RelativeLayout chapterAddDiv;
        public ChapterViewHolder(View v)
        {
            super(v);
            bookChapterName= (TextView) v.findViewById(R.id.textView_ChapterShow);
            chapterAddDiv= (RelativeLayout) v.findViewById(R.id.chapterAddForBook);
            bookNameText= (TextView) v.findViewById(R.id.chapterAddForBook_BookName);
            deleteButton= (TextView) v.findViewById(R.id.chapterCardButton_Delete);
            pushUpButton= (TextView) v.findViewById(R.id.chapterCardButton_PushOut);
            editButton= (TextView) v.findViewById(R.id.chapterCardButton_Edit);
            chapterCreateTime = (TextView) v.findViewById(R.id.chapterCreateTime);
            chapterVauleNumber = (TextView) v.findViewById(R.id.chapterNumbers);
            chapterVaule = (TextView) v.findViewById(R.id.chapterShortDetails);
        }
    }



    //添加章节弹窗
    private void createDialogToAddChapter(  final String bookkey){
        MyDialog myDialog =new MyDialog();
        final Dialog dialog = myDialog.createDialog(activity, R.style.popupDialog);
        Window window =dialog.getWindow();
        final EditText chapterNameVaule = (EditText) window.findViewById(R.id.dialog_Vaule);
        final TextView okTextView = (TextView) window.findViewById(R.id.dialog_confirm);
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Charpters> mCharpterses = new LinkedList<Charpters>();
                mCharpterses.clear();
                /**
                 *get the dateKey
                 */
                SimpleDateFormat timeKey=new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date date=new Date();
                String timeKeyVaule=timeKey.format(date);
                /**
                 *create new chapters
                 */
                Charpters charpters = new Charpters();
                charpters.setBookKey(bookkey);
                charpters.setCharpterName(chapterNameVaule.getText().toString());
                charpters.setCharpterKey(chapterNameVaule.getText().toString()+timeKeyVaule);
                charpters.setChapterVauleNumbers(0);
                charpters.setCharpterVaules("");
                charpters.setCreateTime(timeKeyVaule);
                mCharpterses.add(charpters);
                greenDaoService.saveOrReplacChapters(mCharpterses);
                /**
                 *tell the recycler to refresh items
                 */
                addItem(charpters, 0);
                update(1);
                dialog.dismiss();
            }
        });
        okTextView.setClickable(false);
        chapterNameVaule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    /**
                     * Split  space chars
                     */
                    StringTokenizer st1 = new StringTokenizer(s.toString());
                    String str = "";
                    while (st1.hasMoreTokens()) {
                        str = str + st1.nextToken(" ");
                    }
                    str = str.trim();

                    if (str.length()>0){
                        okTextView.setBackgroundResource(R.drawable.buttonrandiusbordergreen);
                        okTextView.setTextColor(Color.WHITE);
                        okTextView.setClickable(true);
                    }else {
                        okTextView.setBackgroundResource(R.drawable.buttonradiusborder);
                        okTextView.setTextColor(Color.parseColor("#444444"));
                        okTextView.setClickable(false);
                    }
                }
                catch(Exception e) {
                }
            }
        });

    }


}
