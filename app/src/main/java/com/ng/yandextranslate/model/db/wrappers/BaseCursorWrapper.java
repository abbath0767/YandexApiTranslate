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

    public static final class NullableCursorWrapper extends BaseCursorWrapper {

        private static NullableCursorWrapper instance;

        public static NullableCursorWrapper getInstance() {
            if (instance == null) {
                instance = new NullableCursorWrapper();
            }
            return instance;
        }

        private NullableCursorWrapper() {
            super(null);
        }

        @Override
        public String getData() {
            return "NOT EXISTS A TABLE";
        }
    }
}
