package com.ng.yandextranslate.presentation.implementation.favorite;

import com.ng.yandextranslate.AppComponent;
import com.ng.yandextranslate.ui.fragment.favorite.FavoriteFragment;

import dagger.Component;

/**
 * Created by NG on 20.04.17.
 */

@FavoriteScope
@Component(dependencies = AppComponent.class, modules = FavoriteModule.class)
public interface FavoriteComponent {
    void inject(FavoriteFragment favoriteFragment);
}
