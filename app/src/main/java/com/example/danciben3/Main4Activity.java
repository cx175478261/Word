package com.example.danciben3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {
    public MyDatebaseHelper dbHelper;
    public SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        dbHelper=new MyDatebaseHelper(this,"BookStore.db",null,2);
        Button del =(Button)findViewById(R.id.add_scb);
        Button change =(Button)findViewById(R.id.Bt3);
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
                values.put("eg1",eg);
                db.update("Book2",values,"word1 = ?",new String[]{word});
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

                db.delete("Book2","word1 = ?",new String[]{word});
            }
        });


    }

}
