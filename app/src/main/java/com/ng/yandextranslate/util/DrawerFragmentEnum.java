package com.ng.yandextranslate.util;

/**
 * Created by NG on 16.03.17.
 */

public enum DrawerFragmentEnum {
    TRANSLATION(0),
    HISTORY(1),
    FAVOURITES(2),
    ABOUT(3);

    private final int position;

    DrawerFragmentEnum(int position) {
        this.position = position;
    }

    public int getDeclaredPosition() {
        return this.position;
    }
}
