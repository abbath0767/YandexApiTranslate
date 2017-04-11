package com.ng.yandextranslate.controller.data.service.languages;

import android.content.Context;

import com.ng.yandextranslate.controller.data.Repository;
import com.ng.yandextranslate.presentation.implementation.translate.TranslateScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NGusarov on 17/03/17.
 */

@Module
public class TranslateDataModule {

    @TranslateScope
    @Provides
    TranslateDataService provideRepository(Repository repository) {
        return new TranslateDataService(repository);
    }
}
