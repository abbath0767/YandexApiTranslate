package com.ng.yandextranslate.model.db.wrappers;

import android.database.Cursor;

import static com.ng.yandextranslate.model.db.table.HistoryShema.*;
import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.model.pojo.LanguagePair;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryCursorWrapper extends BaseCursorWrapper<HistoryData> {

    public HistoryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public HistoryData getData() {
        int key = getInt(getColumnIndex(HistoryTable.Cols.ID));
        String pair = getString(getColumnIndex(HistoryTable.Cols.PAIR));
        String originalText = getString(getColumnIndex(HistoryTable.Cols.TEXT_FROM));
        String translateText = getString(getColumnIndex(HistoryTable.Cols.TEXT_TO));
        long time = getLong(getColumnIndex(HistoryTable.Cols.TIME));

        return new HistoryData(key, new LanguagePair(pair), originalText, translateText, time);
    }
}
