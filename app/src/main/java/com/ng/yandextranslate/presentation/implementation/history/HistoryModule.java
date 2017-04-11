package com.ng.yandextranslate.presentation.implementation.history;

import com.ng.yandextranslate.presentation.contract.history.HistoryContract;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NG on 11.04.17.
 */

@Module
public class HistoryModule {

    private final HistoryContract.View mView;

    public HistoryModule(HistoryContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @HistoryScope
    HistoryContract.View provideView() {
        return mView;
    }
}
