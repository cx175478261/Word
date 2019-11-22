package com.example.danciben3;

import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class line extends AppCompatActivity {
    private MyDatebaseHelper dbhelper;
    private List<line> word = new ArrayList<>();

    public line(String word1, String mean) {

    }


    //初始化
    public void initwords() {
        word.clear();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String s = "select * from WordsBook";
        Cursor cursor = db.rawQuery(s, null);
        if (cursor.moveToFirst()) {
            do {
                String word1 = cursor.getString(cursor.getColumnIndex("word"));
                String mean = cursor.getString(cursor.getColumnIndex("mean"));
                line book = new line(word1, mean);
                word.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.line);
        dbhelper = new MyDatebaseHelper(this, "BookStore.db", null, 2);

        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initwords();
                ItemsAdapter adapter = new ItemsAdapter(line.this, R.layout.word_item , word);
                ListView wor = findViewById(R.id.List_view);
                wor.setAdapter(adapter);


            }
        });
    }*/
}

