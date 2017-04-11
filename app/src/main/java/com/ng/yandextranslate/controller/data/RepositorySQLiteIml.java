package com.ng.yandextranslate.controller.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.ng.yandextranslate.model.db.table.HistoryShema.*;

import com.ng.yandextranslate.model.db.wrappers.BaseCursorWrapper;
import com.ng.yandextranslate.model.db.wrappers.HistoryCursorWrapper;
import com.ng.yandextranslate.model.pojo.HistoryData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by NG on 11.04.17.
 */

public class RepositorySQLiteIml implements Repository {

    private static final String TAG = RepositorySQLiteIml.class.getSimpleName();

    private SQLiteDatabase mDataBase;
    private DBHelper mHelper;
    private AtomicInteger counter = new AtomicInteger();

    public RepositorySQLiteIml(Context context) {
        mHelper = new DBHelper(context);
        mDataBase = mHelper.getWritableDatabase();
    }

    //todo we need for this?
    @Override
    public void open() {
        counter.incrementAndGet();
        mDataBase = mHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        if (counter.decrementAndGet() == 0) {
            mDataBase.close();
        }
    }

    private ContentValues getContentValues(HistoryData data) {
        ContentValues content = new ContentValues();

        content.put(HistoryTable.Cols.TEXT_FROM, data.getOriginalText());
        content.put(HistoryTable.Cols.TEXT_TO, data.getTranslateText());
        content.put(HistoryTable.Cols.PAIR, data.getLanguagePair().getLangPairStringValue());
        content.put(HistoryTable.Cols.FAVORITE, data.isFavorite() ? 1 : 0);
        content.put(HistoryTable.Cols.TIME, data.getTime());

        return content;
    }

    @Override
    public void addHistory(HistoryData data) {
        Log.d(TAG, "addHistory. data: " + data);

        ContentValues content = getContentValues(data);

        mDataBase.insert(HistoryTable.NAME, null, content);
    }

    @Override
    public List<HistoryData> getAllHistories() {
        Log.d(TAG, "getAllHistories");

        List<HistoryData> list = new ArrayList<>();
        BaseCursorWrapper cursor = queryData(HistoryTable.NAME, null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                list.add((HistoryData) cursor.getData());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        Log.d(TAG, "getAllHistories. return list size: " + list.size());

        return list;
    }

    private BaseCursorWrapper queryData(String tableName, String whereClause, String[] whereArgs) {

        Cursor cursor = mDataBase.query(
                HistoryTable.NAME, //table name
                null,                                            //columns
                whereClause,                                     //selection
                whereArgs,                                       //selection args[]
                null,                                            //group by
                null,                                            //having
                null                                            //order by
                );

        return new HistoryCursorWrapper(cursor);
    }

    @Override
    public List<HistoryData> getFavoriteHistories() {
        return null;
    }

    @Override
    public void deleteHistoryData(int id) {

    }

    @Override
    public void deleteAllHistoryData() {

    }

    @Override
    public long getDataCount(String tableName) {
        return DatabaseUtils.queryNumEntries(mDataBase, tableName);
    }
}
