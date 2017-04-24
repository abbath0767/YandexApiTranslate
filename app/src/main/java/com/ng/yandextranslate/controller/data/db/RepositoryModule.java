package com.ng.yandextranslate.controller.data.db;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NG on 11.04.17.
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    Repository provideRepository(Context context) {
        return new RepositorySQLiteIml(context);
    }
}
