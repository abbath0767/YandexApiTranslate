package com.ng.yandextranslate.presentation.implementation.translate;

import android.util.Log;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.ng.yandextranslate.controller.data.service.languages.TranslateDataService;
import com.ng.yandextranslate.controller.network.RequestHelper;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslatePresenterImpl implements TranslateContract.Presenter {

    public static final String TAG = TranslatePresenterImpl.class.getSimpleName();

    TranslateContract.View mView;
    YandexTranslateApi mYandexTranslateApi;
    TranslateDataService mTranslateDataService;

    private Map<String, String> supportedLangs;
    private List<String> supportedLangDirs;

    //todo unhardcode
    private static String LANG = "ru";

    @Inject
    public TranslatePresenterImpl(YandexTranslateApi api,
                                  TranslateDataService repositoryService,
                                  TranslateContract.View view) {
        this.mView = view;
        this.mYandexTranslateApi = api;
        this.mTranslateDataService = repositoryService;
        setSupportLanguages();
    }

    private void setSupportLanguages() {
        supportedLangs = mTranslateDataService.getLanguages();
        supportedLangDirs = mTranslateDataService.getLanguagesDirs();

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
                    Log.d(TAG, "ERROR IMPL LOAD SUPP LANG! ");
                    error.printStackTrace();
                }
        );
    }

    private void compareSupportLanguages(Map<String, String> supportedLangs, List<String> supportedLangDirs) {
        if (!this.supportedLangs.equals(supportedLangs)) {
            Log.d(TAG, "compareSupportLanguages. supportedLangs not equals old");
            mTranslateDataService.saveNewSupportLangs(supportedLangs);
        }
        if (!this.supportedLangDirs.equals(supportedLangDirs)) {
            Log.d(TAG, "compareSupportLanguages. supportedLangsDirs not equals old");
            mTranslateDataService.saveNewSupportLangsDirs(supportedLangDirs);
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

    @Override
    public void getTranslate(String message, LanguagePair langPair) {
        Log.d(TAG, "GET TRANSLATE FOR MESSAGE: " + message);
        Log.d(TAG, "GET LANG: " + langPair.getLangPair());

        mView.showProgressBar();
        RequestHelper.asyncRequest(mYandexTranslateApi.loadTranslateLang(message, langPair.getLangPair()),
                data -> {
                    mView.showTranslateResult(data.getResponseText());
                    mView.dismissProgressBar();
                },
                error -> {
                    //todo add error logic
                    mView.showError(error.getMessage());
                });
    }
}
