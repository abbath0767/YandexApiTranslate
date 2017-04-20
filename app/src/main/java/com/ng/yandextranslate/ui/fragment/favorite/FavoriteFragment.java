package com.ng.yandextranslate.ui.fragment.favorite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.presentation.contract.favorite.FavoriteContract;
import com.ng.yandextranslate.presentation.implementation.favorite.DaggerFavoriteComponent;
import com.ng.yandextranslate.presentation.implementation.favorite.FavoriteModule;
import com.ng.yandextranslate.ui.fragment.BaseFragment;

import butterknife.ButterKnife;

public class FavoriteFragment extends BaseFragment implements FavoriteContract.View {



    public static Fragment newInstance(Bundle args) {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        ButterKnife.bind(this, rootView);

        DaggerFavoriteComponent.builder()
                .appComponent(App.getAppComponent())
                .favoriteModule(new FavoriteModule(this))
                .build().inject(this);

        return rootView;
    }
}
