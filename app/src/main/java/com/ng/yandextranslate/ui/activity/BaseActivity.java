package com.ng.yandextranslate.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.ui.fragment.traslate.TranslateFragment;
import com.ng.yandextranslate.util.DrawerFragmentEnum;
import com.ng.yandextranslate.util.DrawerItemClickListener;

/**
 * Created by NG on 15.03.17.
 */

public class BaseActivity extends AppCompatActivity implements DrawerItemClickListener.OnDrawerItemClickListener {

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
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        //todo deprecated?
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onDrawerItemClick(int position) {
        mDrawerLayout.closeDrawer(mDrawerListView);

        if (currentFragment.getDeclaredPosition() == position) {
            return;
        }

        setFragment(position);
        setDrawerActivePosition(position);
    }

    void setFragment(int drawerPosition) {
        DrawerFragmentEnum enumElement = DrawerFragmentEnum.values()[drawerPosition];
        Fragment fragment = null;

        switch (enumElement) {
            case TRANSLATION: {
                fragment = getTranslateFragment();
                break;
            }
            case HISTORY: {
                //todo history
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}