package com.ng.yandextranslate.presentation.implementation.favorite;

import com.ng.yandextranslate.presentation.contract.favorite.FavoriteContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NG on 20.04.17.
 */

@Module
public class FavoriteModule {

    private final FavoriteContract.View mView;

    public FavoriteModule(final FavoriteContract.View view) {
        mView = view;
    }

    @Provides
    @FavoriteScope
    FavoriteContract.View provideView() {
        return mView;
    }
}
