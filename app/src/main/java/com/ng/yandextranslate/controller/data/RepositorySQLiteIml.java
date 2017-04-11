package com.ng.yandextranslate.controller.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by NG on 11.04.17.
 */

public class RepositorySQLiteIml implements Repository {

    private static final String TAG = RepositorySQLiteIml.class.getSimpleName();

    private SQLiteDatabase mDataBase;
    private DBHelper mHelper;

    public RepositorySQLiteIml(Context context) {
        mHelper = new DBHelper(context);
        mDataBase = mHelper.getWritableDatabase();
    }
}
