package com.leafoct.myrubbishclassify;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class DownloadImage extends Service {
    public DownloadImage() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        downaload d=new downaload();
        d.start();
        return super.onStartCommand(intent, flags, startId);
    }
    class downaload extends Thread{
        @Override
        public void run() {
            String url = "https://img3.gelbooru.com/images/33/c9/33c9d50eb782ba08b52de1349b67b102.png";
            //创建下载任务,downloadUrl就是下载链接
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            //指定下载路径和下载文件名
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "Yuyuko.png");
            //获取下载管理器
            DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            //将下载任务加入下载队列，否则不会进行下载
            downloadManager.enqueue(request);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
