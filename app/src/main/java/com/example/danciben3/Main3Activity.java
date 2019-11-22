package com.example.danciben3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private  MyDatebaseHelper dbHelper;
    private List<Items> wordm = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button bt_search=(Button)findViewById(R.id.search);
        dbHelper = new MyDatebaseHelper(this, "BookStore.db", null, 2);
        final EditText shurukuang=(EditText)findViewById(R.id.shurukuang);
        Button button=(Button)findViewById(R.id.zjm);
        Button button3=(Button)findViewById(R.id.test);

        //修改、删除
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Main3Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });

        //主界面
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();

                String w=shurukuang.getText().toString();
                String[] columns=new String[]{"word1","mean1","eg1"};
                String[] args=new String[1];
                args[0]=w;
                Cursor cursor=db.query("Book2",columns,"word1 = ?",args,null,null,null);
                wordm.clear();
                if (cursor.moveToFirst()){
                    do {
                        String word1=cursor.getString(cursor.getColumnIndex("word1"));
                        String mean=cursor.getString(cursor.getColumnIndex("mean1"));
                        String eg=cursor.getString(cursor.getColumnIndex("eg1"));
                        wordm.add(new Items(word1,mean,eg));
                    }while (cursor.moveToNext());
                }
                cursor.close();
                ItemsAdapter adapter = new ItemsAdapter(Main3Activity.this, R.layout.word_item, wordm);
                ListView listView = (ListView) findViewById(R.id.ListView1);
                listView.setAdapter(adapter);
            }
        });
    }


    public void initword2()
    {
        wordm.clear();
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Book2",null);
        while (cursor.moveToNext())
        {
            String word1=cursor.getString(cursor.getColumnIndex("word1"));
            String mean=cursor.getString(cursor.getColumnIndex("mean1"));
            String eg=cursor.getString(cursor.getColumnIndex("eg1"));
            wordm.add(new Items(word1,mean,eg));

        }
        cursor.close();
    }

    public void show2()
    {
        initword2();
        ItemsAdapter adapter = new ItemsAdapter(Main3Activity.this, R.layout.word_item, wordm);
        ListView listView1 = (ListView) findViewById(R.id.ListView1);
        listView1.setAdapter(adapter);
    }

}
