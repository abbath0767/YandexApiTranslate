package ng.com.yandextranslate.controller.network;

import ng.com.yandextranslate.model.pojo.support.LanguageList;
import retrofit2.Call;
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
    Observable<LanguageList> loadSupportedLangList(@Query("ui") String languageKey);

}
