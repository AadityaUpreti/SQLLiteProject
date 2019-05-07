package com.example.sqliteproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DictonaryListData extends AppCompatActivity {

    ListView lstWord;
    Button btnSearch;
    EditText txtSearch;
    MyHelper myHelper = null;
    SQLiteDatabase sqLiteDatabase = null;
    HashMap<String, String> hashMap;

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
        lstWord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String wordValue = parent.getItemAtPosition(position).toString();
                String meaning = hashMap.get(wordValue);
                int wid =position;
                Intent intent = new Intent(DictonaryListData.this, ShowMeaning.class);
                intent.putExtra("wid", wid);
                intent.putExtra("word", wordValue);
                intent.putExtra("meaning", meaning);
                startActivity(intent);

                Toast.makeText(DictonaryListData.this, meaning, Toast.LENGTH_LONG).show();

            }
        });


    }

    private void loadWord() {
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();

        wordList = myHelper.getAllWords(sqLiteDatabase);
        hashMap = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            hashMap.put(wordList.get(i).getWordName(), wordList.get(i).getWordMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet()));
        lstWord.setAdapter(stringArrayAdapter);
    }

    private void searchWord(String word) {
        myHelper = new MyHelper(this);
        sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();

        wordList = myHelper.GetWordByName(word, sqLiteDatabase);
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
