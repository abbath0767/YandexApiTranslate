package com.ng.yandextranslate.presentation.implementation.translate;

import dagger.Module;
import dagger.Provides;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;

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
}
