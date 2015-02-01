package com.example.administrator.daiylywriting.MyDailyTools;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;
/**
 * Created by Administrator on 2015/2/1.
 */
public class Push_NewTxT {
        private Context context;
        /** SD卡是否存在**/
        private boolean hasSD = false;
        /** SD卡的路径**/
        private String SDPATH;
        /** 当前程序包的路径**/
        private String FILESPATH;
        public Push_NewTxT(Context context) {
            this.context = context;
            hasSD = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED);
            SDPATH = Environment.getExternalStorageDirectory().getPath();
            FILESPATH = this.context.getFilesDir().getPath();
//            makeBookDirectory( );
        }
    // 生成文件
    public void makeFilePath(String filePath, String fileName) {
        makeBookDirectory( );
        makeRootDirectory(filePath);
    }

    /**
     * 写入内容到SD卡中的txt文本中
     * str为内容
     */
    public void writeSDFile(String str,String fileName,String bookName)
    {
        //SDcard路径在2.2以前是/sdcard，在2.2以上版本中是/mnt/sdcard，最好采用下面的方式灵活获取，适用于所有版本
        File file = new File(SDPATH+"/"+"天天码字的小说"+"/"+bookName+"/",fileName+".txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            //写入数据
           String newstr =str.replaceAll("\n","\r\n");
            try {
                System.out.println(newstr);
                fos.write(newstr.getBytes());
                //关闭输出流
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 生成文件夹
    private   void makeBookDirectory( ) {
        File file = null;
        try {
            file = new File(SDPATH + "/" + "天天码字的小说" + "/");
            if (!file.exists()) {
                file.canWrite();
                file.canRead();
                file.canExecute();
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
            // 生成文件夹
    public  void makeRootDirectory(String filePath) {
        File file = null;

        try {
            file = new File(SDPATH+"/"+"天天码字的小说"+"/"+filePath+"/");
            if (!file.exists()) {
                file.canWrite();
                file.canRead();
                file.canExecute();
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
        public String getFILESPATH() {
            return FILESPATH;
        }
        public String getSDPATH() {
            return SDPATH;
        }
        public boolean hasSD() {
            return hasSD;
        }
    }
