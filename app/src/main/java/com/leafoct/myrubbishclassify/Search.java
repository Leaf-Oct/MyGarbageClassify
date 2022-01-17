package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.leafoct.myrubbishclassify.database.SearchHistoryDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Search extends BaseActivity {
    private TextView back,clear_history,clear_input;
    private EditText edit;
    private ImageButton search;
    private SQLiteDatabase history;
    private ListView history_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        SearchHistoryDatabase shd=new SearchHistoryDatabase(this,"searchhistory.db",null,1);
        history=shd.getWritableDatabase();
        history_list=findViewById(R.id.search_history);
        initial();

    }
    private void initial(){
        back=findViewById(R.id.goback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clear_input=findViewById(R.id.clear_input);
        edit=findViewById(R.id.input_garbage_name);
        clear_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });
        clear_history=findViewById(R.id.clear_history);
        clear_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history.delete("history",null,null);
                onResume();
            }
        });
        search=findViewById(R.id.search_garbage);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit.getText().toString();
                ContentValues cv=new ContentValues();
                cv.put("keyword",name);
                history.insert("history",null,cv);
                cv.clear();
                onResume();

                sendKeyword sk=new sendKeyword(name,getBaseContext());
                sk.start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> aa=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new ArrayList<String>());
        Cursor c=history.query("history",new String[]{"keyword"},null,null,null,null,null);
        if(c.moveToFirst()){
            do{
                String temp=c.getString(c.getColumnIndex("keyword"));
                aa.add(temp);
            }while(c.moveToNext());
        }
        history_list.setAdapter(aa);
    }
}
class sendKeyword extends Thread{
    public String key;
    private Context con;
    public sendKeyword(String s,Context c){
        key=s;
        con=c;
    }
    @Override
    public void run() {
        URL u=null;
        String type=null;
        try {
            u=new URL("https://www.aicesu.cn/laji/?words="+ URLEncoder.encode(key,"utf-8"));
            HttpsURLConnection conn=(HttpsURLConnection)u.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream in=conn.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            int target_line=400;
            String line;
            while(--target_line!=0){
                br.readLine();
            }
            line=br.readLine();
            line=line.trim();
            if(line.charAt(0)=='<'){
                type=null;
            }
            else{
                type=line.split("<")[0].trim();

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        NotificationManager nm= (NotificationManager) con.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel("result","显示结果",NotificationManager.IMPORTANCE_HIGH);
            nc.setDescription("显示搜索垃圾的结果");
            nm.createNotificationChannel(nc);
            if(type!=null){
                Notification notify=new NotificationCompat.Builder(con).setContentTitle(key).setContentText(type).setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.icon).setChannelId("result").setAutoCancel(true).build();
                nm.notify(1,notify);

            }
            else{
                Intent i[]=new Intent[]{new Intent(con,WebSearch.class).putExtra("url",key)};
                PendingIntent pi=PendingIntent.getActivities(con,PendingIntent.FLAG_UPDATE_CURRENT,i,0);
                Log.d("keyword",key);
                Notification notify=new NotificationCompat.Builder(con).setContentTitle(key).setContentText("数据库没找到呢，尝试网页搜索看看").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.icon).setContentIntent(pi).setChannelId("result").setAutoCancel(true).build();
                nm.notify(1,notify);
                pi.cancel();
            }
        }
        else{
            if(type!=null){
                Notification notify=new NotificationCompat.Builder(con).setContentTitle(key).setContentText(type).setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.icon).setAutoCancel(true).build();
                nm.notify(1,notify);
            }
            else{
                Intent i=new Intent(con,WebSearch.class);
                i.putExtra("url",key);
                PendingIntent pi=PendingIntent.getActivities(con,PendingIntent.FLAG_UPDATE_CURRENT,new Intent[]{i},0);
                Notification notify=new NotificationCompat.Builder(con).setContentTitle(key).setContentText("数据库没找到呢，尝试网页搜索看看").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.icon).setContentIntent(pi).setAutoCancel(true).build();
                nm.notify(1,notify);
            }
        }

    }
}
