package com.cinnamint.cotidiano;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class WordDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "word.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public WordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        this.database = getWritableDatabase();
    }

    public void close() {
        if(database != null) {
            this.database.close();
        }
    }



    public List<Words> getEveryWordByDate(long id) {
        List<Words> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT WORD_TEXT, WORD_DEFINITION FROM word WHERE WORD_ID <= ?", new String[] {Long.toString(id)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String text = cursor.getString(0);
            String def = cursor.getString(1);
            Words targetWord = new Words(text, def);
            list.add(targetWord);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
