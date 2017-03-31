package com.ng.yandextranslate.controller.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NGusarov on 17/03/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    YandexTranslateApi provideYandexApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(YandexTranslateApi.ENDPOINT)
                .build();

        return retrofit.create(YandexTranslateApi.class);
    }
}
