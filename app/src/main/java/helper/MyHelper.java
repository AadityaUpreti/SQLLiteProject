package helper;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        String column[] = {WordId, Word, Meaning};
        Cursor cursor = db.query(tblWord, column, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                words.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }
        }

        return words;
    }

    public List<Word> GetWordByName(String word, SQLiteDatabase db) {
        List<Word> words = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from tblWord where word = '" + word + "%'", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                words.add(new Word(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));

            }
        }
        return words;
    }

    public static int getWordId(Word wordPass,SQLiteDatabase db) {
        System.out.println(wordPass.getWordName());
        Cursor cursor = db.rawQuery("select WordId from tblWord where word ='" + wordPass.getWordName() + "'", null);
        if (cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                System.out.println(cursor.getInt(0));
                return cursor.getInt(0);
            }
        }
        return 0;

    }


    public void UpdateWord(Word word, SQLiteDatabase db) {
        try {
            System.out.println("Update"+word.getWordId() + " " + word.getWordName() + " " + word.getWordMeaning());
            db = getWritableDatabase();
            String qry = "UPDATE " + tblWord + " SET " + Word + "='" + word.getWordName() + "'  sWHERE " +
                    WordId + "= ?";
            db.execSQL(qry, new String[]{String.valueOf(word.getWordId())});

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public int UpdateWords(Word word, SQLiteDatabase db) {
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Word, word.getWordName());
            values.put(Meaning, word.getWordMeaning());
            return db.update(tblWord, values, WordId + "=?", new String[]{
                    String.valueOf(word.getWordId())
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return 0;
        }
    }

    public void deleteWord(Word word) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(tblWord, WordId + " =?", new String[]{String.valueOf(word.getWordId())});
            db.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
