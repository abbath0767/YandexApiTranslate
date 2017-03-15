package ng.com.yandextranslate.util;

/**
 * Created by NG on 16.03.17.
 */

public enum DrawerFragmentEnum {
    TRANSLATION(0),
    HISTORY(1),
    ABOUT(2);

    private final int position;

    DrawerFragmentEnum(int position) {
        this.position = position;
    }

    public int getDeclaredPosition() {
        return this.position;
    }
}
