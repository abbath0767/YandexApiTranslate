package com.ng.yandextranslate.model.db.table;

/**
 * Created by NG on 11.04.17.
 */

public class LanguageShema {
    public static class LanguageTablePair {
        public static final String NAME = "language_variant_pair";

        public static class Cols {
            public static final String ID = "id";
            public static final String PAIR = "pair";

        }
    }

    public static class LanguageTableLangs {
        public static final String NAME = "language_variants_tarn";

        public static class Cols {
            public static final String ID = "id";
            public static final String DIR = "dir";
            public static final String TRAN = "tran";
        }
    }
}
