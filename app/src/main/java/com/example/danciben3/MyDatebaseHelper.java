package com.example.danciben3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatebaseHelper extends SQLiteOpenHelper {
    public static  final String CREATE_BOOK="create table Book("
            +"id integer primary key autoincrement,"
            +"word text,"
            +"mean text,"
            +"eg text)";
    public static  final String CREATE_BOOK2="create table Book2("
            +"id1 integer primary key autoincrement,"
            +"word1 text,"
            +"mean1 text,"
            +"eg1 text)";
    private Context mContext;
    public MyDatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_BOOK2);


    }
    @Override
    public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Book2");
        onCreate(db);
    }
}
