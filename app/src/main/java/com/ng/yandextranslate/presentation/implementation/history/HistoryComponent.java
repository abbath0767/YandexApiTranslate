package com.ng.yandextranslate.presentation.implementation.history;

import com.ng.yandextranslate.AppComponent;
import com.ng.yandextranslate.ui.fragment.history.HistoryFragment;

import dagger.Component;

/**
 * Created by NG on 11.04.17.
 */

@HistoryScope
@Component(dependencies = AppComponent.class, modules = HistoryModule.class)
public interface HistoryComponent {
    void inject(HistoryFragment fragment);
}
