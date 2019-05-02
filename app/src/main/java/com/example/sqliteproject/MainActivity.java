package com.example.sqliteproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText txtWord,txtMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMeaning = findViewById(R.id.txtMeaning);
        txtWord = findViewById(R.id.txtWord);
        btnAdd = findViewById(R.id.btnAdd);
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
    btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long id = myHelper.InsertData(txtWord.getText().toString(),
                    txtMeaning.getText().toString(),sqLiteDatabase);
            if(id>0){
                System.out.println("inserted");
            }else {
                System.out.println("not inserted");
            }
        }
    });
    }
}
