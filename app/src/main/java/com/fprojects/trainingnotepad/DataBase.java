package com.fprojects.trainingnotepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super (context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Contact(id integer primary key autoincrement, workoutStart INTEGER default 0, workoutEnd INTEGER default 0,  date TEXT default 0, myWeight REAL default 0, exercise TEXT default 0, exerciseWeight REAL default 0, approaches INTEGER default 0, repeats INTEGER default 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}