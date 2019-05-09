package com.example.sqliteproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnShow;
    EditText txtWord, txtMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMeaning = findViewById(R.id.txtMeaning);
        txtWord = findViewById(R.id.txtWord);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = myHelper.InsertData(txtWord.getText().toString(),
                        txtMeaning.getText().toString(), sqLiteDatabase);
                if (id > 0) {
                    clear();
                    Toast.makeText(MainActivity.this, "Word Saved", Toast.LENGTH_LONG).show();
                } else {
                    clear();
                    Toast.makeText(MainActivity.this, "Word Not Saved", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DictonaryListData.class);
                startActivity(i);
            }
        });
    }


    public void clear() {
        txtMeaning.setText("");
        txtWord.setText("");
    }
}
