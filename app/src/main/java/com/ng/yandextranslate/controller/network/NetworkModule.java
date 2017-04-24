package com.ng.yandextranslate.controller.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NGusarov on 17/03/17.
 */

import static com.ng.yandextranslate.util.AppPrefs.*;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    YandexTranslateApi provideYandexApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(logging);

        client.connectTimeout(TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build())
                .baseUrl(YandexTranslateApi.ENDPOINT)
                .build();



        return retrofit.create(YandexTranslateApi.class);
    }
}
