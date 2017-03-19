package ng.com.yandextranslate.presentation.implementation.translate;

import android.net.Network;
import android.util.Log;

import java.util.Map;

import javax.inject.Inject;

import ng.com.yandextranslate.controller.data.RepositoryService;
import ng.com.yandextranslate.controller.network.RequestHelper;
import ng.com.yandextranslate.controller.network.YandexTranslateApi;
import ng.com.yandextranslate.model.pojo.LanguagePair;
import ng.com.yandextranslate.presentation.contract.translate.TranslateContract;
import rx.Subscription;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslatePresenterImpl implements TranslateContract.Presenter {

    TranslateContract.View mView;

    YandexTranslateApi mYandexTranslateApi;
    RepositoryService mRepositoryService;

    Map<String, String> supportedLangs;

    //todo unhardcode
    private static String LANG = "ru";

    @Inject
    public TranslatePresenterImpl(YandexTranslateApi api,
                                  RepositoryService repositoryService,
                                  TranslateContract.View view) {
        this.mView = view;
        this.mYandexTranslateApi = api;
        this.mRepositoryService = repositoryService;
        loadSupportLanguages();
    }

    public void loadSupportLanguages() {
        Subscription getSupportedLanguages = RequestHelper.asyncRequest(mYandexTranslateApi.loadSupportedLangList(LANG),
                data -> {
                    Log.d("TAG", "Load supportedLanguges finish");
                    supportedLangs = data.getMapLangs();
                    //todo unhardcode
                    if (data.getListDirs().contains("en-ru")) {
                        mView.setDefaultLanguages(new LanguagePair("en-ru"));
                    } else {
                        mView.setDefaultLanguages(new LanguagePair("PRO-BLEMS"));
                    }
                },
                error -> {
                    //todo need error processing
                    Log.d("TAG", "error! ");
                    error.printStackTrace();
                }
        );

    }

    @Override
    public void saveToHistory() {

    }

    @Override
    public void getTranslate(String message) {

    }
}
