package com.ng.yandextranslate.controller.data;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NGusarov on 17/03/17.
 */

@Module
public class DataModule {

    @Singleton
    @Provides
    RepositoryService provideRepository(Context context) {
        return new RepositoryServiceImplDumb(context);
    }
}
