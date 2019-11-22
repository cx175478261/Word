package com.example.danciben3;



import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class hengping extends AppCompatActivity {
    public MyDatebaseHelper dbHelper;
    public SQLiteDatabase db=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hengping);

        dbHelper=new MyDatebaseHelper(this,"BookStore.db",null,2);
        Button add =(Button)findViewById(R.id.Bt1);
        Button del =(Button)findViewById(R.id.Bt3);
        Button change =(Button)findViewById(R.id.Bt4);
        Button add2=(Button)findViewById(R.id.add_scb);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                EditText ET1=(EditText)findViewById(R.id.ET1);
                EditText ET2=(EditText)findViewById(R.id.ET2);
                EditText ET3=(EditText)findViewById(R.id.ET3);
                String word=ET1.getText().toString();
                String mean=ET2.getText().toString();
                String eg=ET3.getText().toString();

                add3(word,mean,eg);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values=new ContentValues();
                EditText ET1=(EditText)findViewById(R.id.ET1);
                EditText ET2=(EditText)findViewById(R.id.ET2);
                EditText ET3=(EditText)findViewById(R.id.ET3);
                String word=ET1.getText().toString();
                String mean=ET2.getText().toString();
                String eg=ET3.getText().toString();

                add1(word,mean,eg);

            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                EditText ET1=(EditText)findViewById(R.id.ET1);
                EditText ET2=(EditText)findViewById(R.id.ET2);
                EditText ET3=(EditText)findViewById(R.id.ET3);
                String word=ET1.getText().toString();
                String mean=ET2.getText().toString();
                String eg=ET3.getText().toString();
                values.put("eg",eg);
                db.update("Book",values,"word = ?",new String[]{word});
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                EditText ET1=(EditText)findViewById(R.id.ET1);
                EditText ET2=(EditText)findViewById(R.id.ET2);
                EditText ET3=(EditText)findViewById(R.id.ET3);
                String word=ET1.getText().toString();
                String mean=ET2.getText().toString();
                String eg=ET3.getText().toString();

                db.delete("Book","word = ?",new String[]{word});
            }
        });



    }
    public void add1(String w,String s,String eg)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("word",w);
        values.put("mean",s);
        values.put("eg",eg);
        db.insert("Book",null,values);
    }

    public void add3(String w,String s,String eg)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("word1",w);
        values.put("mean1",s);
        values.put("eg1",eg);
        db.insert("Book2",null,values);
    }

}
