package com.ng.yandextranslate.model.db.table;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryShema {

    public static class HistoryTable {
        public static final String NAME = "history_moment";

        public static class Cols {
            public static final String ID = "id";
            public static final String TEXT_FROM = "text_from";
            public static final String TEXT_TO = "text_to";
            public static final String PAIR = "lang_pair";
            public static final String TIME = "time";
            public static final String FAVORITE = "favorite";
        }
    }
}
