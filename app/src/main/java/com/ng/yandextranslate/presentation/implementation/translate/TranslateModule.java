package com.ng.yandextranslate.presentation.implementation.translate;

import dagger.Module;
import dagger.Provides;

import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.controller.data.spreference.SPreferenceManager;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;
import com.ng.yandextranslate.ui.view.LanguageSelectView;

/**
 * Created by NGusarov on 17/03/17.
 */

@Module
public class TranslateModule {

    private final TranslateContract.View mView;

    public TranslateModule(TranslateContract.View view) {
        this.mView = view;
    }

    @Provides
    @TranslateScope
    TranslateContract.View provideView() {
        return mView;
    }

    @Provides
    @TranslateScope
    TranslatePresenterImpl providePresenter(YandexTranslateApi api, HistoryDataService historyDataService, SPreferenceManager preferenceManager) {
        return new TranslatePresenterImpl(api, historyDataService, preferenceManager, this.mView);
    }
}
