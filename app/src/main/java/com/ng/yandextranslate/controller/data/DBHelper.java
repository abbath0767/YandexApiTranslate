package com.ng.yandextranslate.controller.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.ng.yandextranslate.model.db.table.HistoryShema.*;
import static com.ng.yandextranslate.model.db.table.LanguageShema.*;


/**
 * Created by NG on 11.04.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    
    private static final String TAG = DBHelper.class.getSimpleName();
    
    private static final String NAME = "history_log";
    private static final int VERSION = 1;
    
    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        
        initTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onCreate");
        //todo nothing?..
    }

    private void initTables(SQLiteDatabase db) {
        Log.d(TAG, "initTables");
        db.execSQL(SQLCommand.CREATE_TABLE_HISTORY);
        db.execSQL(SQLCommand.CREATE_TABLE_LANG_PAIR);
        db.execSQL(SQLCommand.CREATE_TABLE_LANG_TRAN);
    }

    private static final class SQLCommand {
        private static final String CREATE_TABLE_HISTORY = "CREATE TABLE "
                + HistoryTable.NAME + "("
                + HistoryTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + HistoryTable.Cols.LANG_FROM + " TEXT,"
                + HistoryTable.Cols.LANG_TO + " TEXT,"
                + HistoryTable.Cols.TEXT_FROM + " TEXT,"
                + HistoryTable.Cols.TEXT_TO + " TEXT,"
                + HistoryTable.Cols.TIME + " INTEGER,"
                + HistoryTable.Cols.FAVORITE + " INTEGER"
                + ")";

        private static final String CREATE_TABLE_LANG_PAIR = "CREATE TABLE "
                + LanguageTablePair.NAME + "("
                + LanguageTablePair.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LanguageTablePair.Cols.PAIR + " TEXT"
                + ")";

        private static final String CREATE_TABLE_LANG_TRAN = "CREATE TABLE "
                + LanguageTableLangs.NAME + "("
                + LanguageTableLangs.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LanguageTableLangs.Cols.DIR + " TEXT,"
                + LanguageTableLangs.Cols.TRAN + " TEXT"
                + ")";
    }
}
