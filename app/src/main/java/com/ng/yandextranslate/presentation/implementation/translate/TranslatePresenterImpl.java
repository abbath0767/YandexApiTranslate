package com.ng.yandextranslate.presentation.implementation.translate;

import android.util.Log;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.ng.yandextranslate.controller.data.RepositoryService;
import com.ng.yandextranslate.controller.network.RequestHelper;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslatePresenterImpl implements TranslateContract.Presenter {

    TranslateContract.View mView;
    YandexTranslateApi mYandexTranslateApi;
    RepositoryService mRepositoryService;

    private Map<String, String> supportedLangs;
    private List<String> supportedLangDirs;

    //todo unhardcode
    private static String LANG = "ru";

    @Inject
    public TranslatePresenterImpl(YandexTranslateApi api,
                                  RepositoryService repositoryService,
                                  TranslateContract.View view) {
        this.mView = view;
        this.mYandexTranslateApi = api;
        this.mRepositoryService = repositoryService;
        setSupportLanguages();
    }

    private void setSupportLanguages() {
        supportedLangs = mRepositoryService.getLanguages();
        supportedLangDirs = mRepositoryService.getLanguagesDirs();

        if (supportedLangs != null && supportedLangs.size() != 0 &&
                supportedLangDirs != null && supportedLangDirs.size() != 0) {
            setSupportLangToView(supportedLangs, supportedLangDirs);
        }

        loadSupportLanguages();
    }

    public void loadSupportLanguages() {
        RequestHelper.asyncRequest(mYandexTranslateApi.loadSupportedLangList(LANG),
                data -> {
                    compareSupportLanguages(data.getMapLangs(), data.getListDirs());
                },
                error -> {
                    //todo need error processing to view
                    Log.d("TAG", "ERROR IMPL LOAD SUPP LANG! ");
                    error.printStackTrace();
                }
        );
    }

    private void compareSupportLanguages(Map<String, String> supportedLangs, List<String> supportedLangDirs) {
        if (!this.supportedLangs.equals(supportedLangs)) {
            mRepositoryService.saveNewSupportLangs(supportedLangs);
        }
        if (!this.supportedLangDirs.equals(supportedLangDirs)) {
            mRepositoryService.saveNewSupportLangsDirs(supportedLangDirs);
        }

        setSupportLangToView(supportedLangs, supportedLangDirs);
    }

    private void setSupportLangToView(Map<String, String> supportedLangs, List<String> supportedLangDirs) {
        mView.setLanguages(supportedLangs, supportedLangDirs);
    }

    @Override
    public void saveToHistory() {
        //todo save to repo
    }

    //todo refactor
    @Override
    public Observable<String> getTranslate(String message, LanguagePair langPair) {
        Log.d("TAG", "GET TRANSLATE");
        Log.d("TAG", "GET LANG: " + langPair.getLangPair());

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                RequestHelper.asyncRequest(mYandexTranslateApi.loadTranslateLang(message, langPair.getLangPair()),
                        data -> {
                            subscriber.onNext(data.getText().get(0));
                        },
                        error -> {
                            subscriber.onError(error);
                        }
                );
//                mYandexTranslateApi.loadTranslateLang(message, langPair.getLangPair())
//                        .doOnNext(new Action1<TranslateResponse>() {
//                            @Override
//                            public void call(TranslateResponse translateResponse) {
//                                subscriber.onNext(translateResponse.getText());
//                            }
//                        })
//                        .observeOn(Schedulers.io())
//                        .subscribeOn(AndroidSchedulers.mainThread());
            }
        });

    }
}
