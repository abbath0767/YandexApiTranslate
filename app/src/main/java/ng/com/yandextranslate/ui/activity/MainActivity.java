package ng.com.yandextranslate.ui.activity;

import android.os.Bundle;

import ng.com.yandextranslate.util.DrawerFragmentEnum;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFragment(DrawerFragmentEnum.TRANSLATION.getDeclaredPosition());
    }
}
