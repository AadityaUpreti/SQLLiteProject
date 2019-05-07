package com.example.sqliteproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import helper.MyHelper;
import model.Word;

public class ShowMeaning extends AppCompatActivity {

    Button btnUpdate, btnDelete;
    EditText tvWord, tvMeaning;
    String wordExtra, meaningExtra;
    int wid;
    MyHelper myHelper = null;
    SQLiteDatabase sqLiteDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meaning);

        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);

        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            wid = bundle.getInt("wid");
            wordExtra = bundle.getString("word");
            meaningExtra = bundle.getString("meaning");
            tvMeaning.setText(meaningExtra);
            tvWord.setText(wordExtra +" id: "+wid);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordExtra = tvWord.getText().toString();
                meaningExtra = tvMeaning.getText().toString();
                Word word = new Word(wid, wordExtra, meaningExtra);
                myHelper.UpdateWord(word, sqLiteDatabase);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
