package com.cinnamint.cotidiano;


import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class WordDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "word.db";
    private static final int DATABASE_VERSION = 1;

    public WordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



}
