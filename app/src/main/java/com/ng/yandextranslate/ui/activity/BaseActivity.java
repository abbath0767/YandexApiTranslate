package com.ng.yandextranslate.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.ui.fragment.favorite.FavoriteFragment;
import com.ng.yandextranslate.ui.fragment.history.HistoryFragment;
import com.ng.yandextranslate.ui.fragment.traslate.TranslateFragment;
import com.ng.yandextranslate.util.DrawerFragmentEnum;
import com.ng.yandextranslate.util.DrawerItemClickListener;

/**
 * Created by NG on 15.03.17.
 */

public class BaseActivity extends AppCompatActivity implements DrawerItemClickListener.OnDrawerItemClickListener {

    public static final String TAG = BaseActivity.class.getSimpleName();

    //ActionBar and Drawer
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.drawer_list_view)
    ListView mDrawerListView;
    @BindArray(R.array.drawer_items)
    String[] mDrawerStrings;

    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle;
    private String mDrawerTitle;
    private DrawerFragmentEnum currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initDrawer();

        //setup icons for toggle
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        setFragment(DrawerFragmentEnum.TRANSLATION.getDeclaredPosition());
        currentFragment = DrawerFragmentEnum.TRANSLATION;
    }

    private void initDrawer() {
        mDrawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mDrawerStrings));
        //set first item checked
        setDrawerActivePosition(0);

        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener(this));

        mDrawerTitle = getResources().getString(R.string.open_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
                closeKeyboard();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
                closeKeyboard();
            }
        };

        //todo deprecated?
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void closeKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onDrawerItemClick(int position) {
        mDrawerLayout.closeDrawer(mDrawerListView);

        if (currentFragment.getDeclaredPosition() == position) {
            return;
        }

        Log.d(TAG, "onDrawerClick. drawerPosition: " + position);

        setFragment(position);
        setDrawerActivePosition(position);
    }

    void setFragment(int drawerPosition) {
//        DrawerFragmentEnum enumElement = DrawerFragmentEnum.values()[drawerPosition];
        currentFragment = DrawerFragmentEnum.values()[drawerPosition];
        Fragment fragment = null;

        switch (currentFragment) {
            case TRANSLATION: {
                fragment = getTranslateFragment();
                break;
            }
            case HISTORY: {
                fragment = getHistoryFragment();
                break;
            }
            case FAVOURITES: {
                fragment = getFavoriteFragment();
                break;
            }
            case ABOUT: {
                //todo about
                break;
            }
            default:
                return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_frame_layout, fragment)
                .commit();
    }

    private void setDrawerActivePosition(int position) {
        setTitle(mDrawerStrings[position]);
        mDrawerListView.setItemChecked(position, true);
    }

    private void setTitle(String title) {
        mTitle = title;
        getSupportActionBar().setTitle(title);
    }

    private Fragment getTranslateFragment() {
        //todo mb change this?
        return TranslateFragment.newInstance(null);
    }

    private Fragment getHistoryFragment() {
        return HistoryFragment.newInstance(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Fragment getFavoriteFragment() {
        return FavoriteFragment.newInstance(null);
    }
}
