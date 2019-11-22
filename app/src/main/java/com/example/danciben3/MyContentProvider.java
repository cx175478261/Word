package com.example.danciben3;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final int DIR=0;
    public static final int ITEM=1;
    public static final String AUTHORITY="com.example.danciben3.provider";
    private static UriMatcher uriMatcher;
    private MyDatebaseHelper dbHelper;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"Book",DIR);
        uriMatcher.addURI(AUTHORITY,"Book/#", ITEM);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int del=0;
        switch (uriMatcher.match(uri)){
            case DIR:
            {
                del=db.delete("Book",selection,selectionArgs);
            }
            break;
            case ITEM:
            {
                String id=uri.getPathSegments().get(1);
                del=db.delete("Book","id=?",new String[]{id});

            }
            break;
            default:
                break;
        }
        return del;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri))
        {
            case DIR:
                return "vnd.android.cursor.dir/vnd.com.example.danciben3.provider.Book";
            case ITEM:
                return "vnd.android.cursor.item/vnd.com.example.danciben3.provider.Book";
            default:
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Uri uriReturn=null;
        long newId=db.insert("Book",null,values);
        uriReturn=Uri.parse("content://"+AUTHORITY+"/Book/"+newId);
        return uriReturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper=new MyDatebaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri))
        {
            case DIR:
                cursor=db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case ITEM:
                String id=uri.getPathSegments().get(1);
                cursor=db.query("Book",projection,"id=?",new String[]{id},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int update=0;
        switch (uriMatcher.match(uri))
        {
            case DIR:
                update=db.update("Book",values,selection,selectionArgs);
                break;
            case ITEM:
                String id=uri.getPathSegments().get(1);
                update=db.update("Book",values,"id=?",new String[]{id});
                break;
            default:
                break;
        }
        return update;
    }
}
