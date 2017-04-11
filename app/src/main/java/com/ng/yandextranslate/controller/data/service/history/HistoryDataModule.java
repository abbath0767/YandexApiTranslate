package com.ng.yandextranslate.controller.data.service.history;

import com.ng.yandextranslate.controller.data.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NG on 11.04.17.
 */

@Module
public class HistoryDataModule {

    @Provides
    @Singleton
    HistoryDataService provideHistoryDataModule(Repository repository) {
        return new HistoryDataService(repository);
    }
}
