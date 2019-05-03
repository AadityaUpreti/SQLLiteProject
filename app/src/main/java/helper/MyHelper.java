package helper;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Word;

public class MyHelper extends SQLiteOpenHelper {

    private static final String databaseName = "Dictonarydb";
    private static final String tblWord = "tblWord";
    private static final int dbVersion = 1;
    private static final String WordId = "WordId";
    private static final String Word = "Word";
    private static final String Meaning = "Meaning";


    public MyHelper(Context context) {
        super(context, databaseName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tblWord +
                "(" +
                WordId + " Integer PRIMARY KEY AUTOINCREMENT ," +
                Word + " TEXT," + Meaning + " TEXT" + ")";
        db.execSQL(query);
    }

    public long InsertData(String word, String meaning, SQLiteDatabase db) {
        long id;

        ContentValues contentValues = new ContentValues();
        contentValues.put(Word, word);
        contentValues.put(Meaning, meaning);
        id = db.insert(tblWord, null, contentValues);
        return id;
    }

    public List<Word> getAllWords(SQLiteDatabase db) {
        List<Word> words = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tblWord", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                words.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }

        return words;
    }

    public List<Word> getAllWordss(SQLiteDatabase db) {
        List<Word> words = new ArrayList<>();
        String column[]={WordId,Word,Meaning};
        Cursor cursor = db.query(tblWord, column,null,null,null,null,null,null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                words.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }

        return words;
    }

    public List<Word>GetWordByName(String word,SQLiteDatabase db){
        List<Word> words = new ArrayList<>();
        Cursor cursor= db.rawQuery("select * from tblWord where word '" + word+""+"%'", null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                words.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));

            }
        }
        return words;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
