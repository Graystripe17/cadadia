//package com.cinnamint.cotidiano;
//
//
//import android.app.PendingIntent;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DatabaseAccess {
//
//    private SQLiteOpenHelper openHelper;
//    private SQLiteDatabase database;
//    private static DatabaseAccess instance;
//
//    private DatabaseAccess(Context context) {
//        this.openHelper = new WordDatabase(context);
//    }
//
//    public static DatabaseAccess getInstance(Context context) {
//        if(instance == null) {
//            instance = new DatabaseAccess(context);
//        }
//        return instance;
//    }
//
//    public void open() {
//        this.database = openHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        if(database != null) {
//            this.database.close();
//        }
//    }
//
//    public List<Words> getAllWords() {
//        List<Words> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM word", null);
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()) {
//            Words targetWord = new Words();
//            targetWord.setText(cursor.getString(1));
//            targetWord.setDefinition(cursor.getString(2));
//            list.add(targetWord);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }
//
//    public List<Words> getEveryWordByDate() {
//        List<Words> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT WORD_TEXT, WORD_DEFINITION FROM word WHERE WORD_ID <=" + 5, null);
//        cursor.moveToFirst();
//        while(!cursor.isAfterLast()) {
//            String text = cursor.getString(1);
//            String def = cursor.getString(2);
//            Words targetWord = new Words(text, def);
//            list.add(targetWord);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }
//
//}
