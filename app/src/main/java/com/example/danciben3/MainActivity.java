package com.example.danciben3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDatebaseHelper dbHelper;
    private List<Items> word = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatebaseHelper(this, "BookStore.db", null, 2);
        Button bt_search=(Button)findViewById(R.id.search);
        final EditText input=(EditText)findViewById(R.id.input);
        Button button=(Button)findViewById(R.id.test);
        Button button1=(Button)findViewById(R.id.scb) ;
        Button help=(Button)findViewById(R.id.help);
        Button search1=(Button)findViewById(R.id.search1);
        Button search2=(Button)findViewById(R.id.search2);

        //帮助
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,help.class);
                startActivity(intent);
            }
        });

        //模糊查询
        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,search2.class);
                startActivity(intent);
            }
        });

        //单词列表
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,line.class);
                startActivity(intent);
            }
        });

        //生词本
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });

        //增加，删除，更改
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        //查询
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();

                String w=input.getText().toString();
                String[] columns=new String[]{"word","mean","eg"};
                String[] args=new String[1];
                args[0]=w;
                Cursor cursor=db.query("Book",columns,"word = ?",args,null,null,null);
                word.clear();
                if (cursor.moveToFirst()){
                    do {
                        String word1=cursor.getString(cursor.getColumnIndex("word"));
                        String mean=cursor.getString(cursor.getColumnIndex("mean"));
                        String eg=cursor.getString(cursor.getColumnIndex("eg"));
                        word.add(new Items(word1,mean,eg));
                    }while (cursor.moveToNext());
                }
                cursor.close();
                ItemsAdapter adapter = new ItemsAdapter(MainActivity.this, R.layout.word_item, word);
                ListView listView = (ListView) findViewById(R.id.ListView);
                listView.setAdapter(adapter);
}
        });
    }

    public void initword()
    {
        word.clear();
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Book",null);
        while (cursor.moveToNext())
        {
            String word1=cursor.getString(cursor.getColumnIndex("word"));
            String mean=cursor.getString(cursor.getColumnIndex("mean"));
            String eg=cursor.getString(cursor.getColumnIndex("eg"));
            word.add(new Items(word1,mean,eg));
        }
        cursor.close();

    }

    public void show1()
    {
        initword();
        ItemsAdapter adapter = new ItemsAdapter(MainActivity.this, R.layout.word_item, word);
        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(adapter);
    }
}
