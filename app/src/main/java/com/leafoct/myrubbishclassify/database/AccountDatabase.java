package com.leafoct.myrubbishclassify.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDatabase extends SQLiteOpenHelper {
    private static final String create_adb="create table account ("+
            "id integer primary key autoincrement,"+
            "account text," +
            "password text)";
    private Context mContext;

    public AccountDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_adb);
        ContentValues root=new ContentValues();
//        初始化默认账户密码
//        老实说我的ubuntu和mysql等机密账户全都是用这个密码...
        root.put("account","root");
        root.put("password","root");
        db.insert("account",null,root);
        root.clear();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
