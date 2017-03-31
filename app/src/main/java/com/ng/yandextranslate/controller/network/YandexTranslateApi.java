package com.ng.yandextranslate.controller.network;

import com.ng.yandextranslate.controller.network.data.LanguageListResponse;
import com.ng.yandextranslate.model.pojo.support.TranslateResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface YandexTranslateApi {

    public static final String ENDPOINT = "https://translate.yandex.net/";
    //todo refactor this. need more security level
    public static final String API_KEY =
            "trnsl.1.1.20170315T100226Z.fe4426a08d83fdd0.2bbc02ed514d8f9358a92c8673b0ae3a4ffd1ef3";
    public static final String API_STRING = "key=" + API_KEY;

    @POST("api/v1.5/tr.json/getLangs?" + API_STRING)
    Observable<LanguageListResponse> loadSupportedLangList(@Query("ui") String languageKey);

    @POST("api/v1.5/tr.json/translate?" + API_STRING)
    Observable<TranslateResponse> loadTranslate(@Query("text") String text);

    @POST("api/v1.5/tr.json/translate?" + API_STRING)
    Observable<TranslateResponse> loadTranslateLang(@Query("text") String text, @Query("lang") String lang);

}
