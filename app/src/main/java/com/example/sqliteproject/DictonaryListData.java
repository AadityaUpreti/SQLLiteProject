package com.example.sqliteproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DictonaryListData extends AppCompatActivity {

    ListView lstWord;
    Button btnSearch;
    EditText txtSearch;
    MyHelper myHelper=null;
    SQLiteDatabase sqLiteDatabase=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictonary_list_data);
        lstWord = findViewById(R.id.lstWord);
        txtSearch = findViewById(R.id.txtSearchWord);
        btnSearch = findViewById(R.id.btnSearch);
        loadWord();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord(txtSearch.getText().toString());
            }
        });

    }

    private void loadWord() {
        myHelper = new MyHelper(this);
sqLiteDatabase = myHelper.getWritableDatabase();
        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.getAllWords(sqLiteDatabase);
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            hashMap.put(wordList.get(i).getWordName(), wordList.get(i).getWordMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet()));
        lstWord.setAdapter(stringArrayAdapter);
    }

    private void searchWord(String word){
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();
        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetWordByName(word,sqLiteDatabase);
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            hashMap.put(wordList.get(i).getWordName(), wordList.get(i).getWordMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet()));
        lstWord.setAdapter(stringArrayAdapter);
    }
}
