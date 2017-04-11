package com.ng.yandextranslate.model.db.wrappers;

import android.database.Cursor;

/**
 * Created by NG on 11.04.17.
 */

public class LanguagePairCursorWrapper extends BaseCursorWrapper{
    public LanguagePairCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override
    public Object getData() {
        return null;
    }
}
