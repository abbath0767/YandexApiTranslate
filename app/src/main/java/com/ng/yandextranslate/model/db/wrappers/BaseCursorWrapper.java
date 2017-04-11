package com.ng.yandextranslate.model.db.wrappers;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by NG on 11.04.17.
 */

public abstract class BaseCursorWrapper<T> extends CursorWrapper {

    public BaseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public abstract T getData();
}
