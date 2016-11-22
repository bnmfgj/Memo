package com.jkxy.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Db extends SQLiteOpenHelper {
    public Db(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE note(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time INTEGER NOT NULL," +
                "description TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
