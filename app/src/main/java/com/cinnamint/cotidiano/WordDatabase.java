package com.cinnamint.cotidiano;


import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class WordDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "word.db";
    private static final String TABLE_WORD = "word";
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
        Log.d(MainActivity.TAG, "getEveryWordByDate count " + id);

        List<Words> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT WORD_TEXT, WORD_DEFINITION FROM " + TABLE_WORD + " WHERE WORD_ID <= ?", new String[] {Long.toString(id)});
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

    public long getWordRowCount() {
        return DatabaseUtils.queryNumEntries(database, TABLE_WORD);
    }

    public Words getTodayWord(long id) {
        Cursor cursor = database.rawQuery("SELECT WORD_TEXT, WORD_DEFINITION FROM " + TABLE_WORD + " WHERE WORD_ID = ? LIMIT 1", new String[] {Long.toString(
                                                                                                                                                                ((id-1) % getWordRowCount()) + 1
                                                                                                                                                            )
                                                                                                                                                        });
        Log.d(MainActivity.TAG, "getTodayWord count " + id);

        cursor.moveToFirst();
        if(cursor.isAfterLast()) {
            return new Words("ERROR", "Please report this to the developer");
        }
        String text = cursor.getString(0);
        String def = cursor.getString(1);
        cursor.close();
        return new Words(text, def);
    }

}
