package com.ng.yandextranslate.presentation.implementation.translate;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.controller.data.spreference.SPreferenceManager;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;
import com.ng.yandextranslate.controller.network.data.response.LanguageListResponse;
import com.ng.yandextranslate.controller.network.data.response.TranslateResponse;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslatePresenterImpl implements TranslateContract.Presenter {

    public static final String TAG = TranslatePresenterImpl.class.getSimpleName();

    private TranslateContract.View mView;

    private YandexTranslateApi mYandexTranslateApi;
    private HistoryDataService mHistoryDataService;
    private SPreferenceManager mSPreferenceManager;

    private List<String> mSupportedLanguages = new ArrayList<>();
    private List<LanguageTranscript> mLanguageTranscripts = new ArrayList<>();
    private List<LanguagePair> mLanguagePairs = new ArrayList<>();
    private LanguageTranscript mFrom;
    private LanguageTranscript mTo;

    //todo unhardcode
    private static String LANG = "ru";

//    @Inject
    public TranslatePresenterImpl(YandexTranslateApi api,
                                  HistoryDataService historyDataService,
                                  SPreferenceManager preferenceManager,
                                  TranslateContract.View view) {
        this.mView = view;
        this.mYandexTranslateApi = api;
        this.mHistoryDataService = historyDataService;
        this.mSPreferenceManager = preferenceManager;
        loadSupportLanguages();
    }

    private void loadSupportLanguages() {
        Call<LanguageListResponse> call = mYandexTranslateApi.loadSupportedLangList(LANG);
        call.enqueue(new Callback<LanguageListResponse>() {
            @Override
            public void onResponse(final Call<LanguageListResponse> call, final Response<LanguageListResponse> response) {
                setSupportLangToView(response.body().getMapLangs(), response.body().getListDirs());
            }

            @Override
            public void onFailure(final Call<LanguageListResponse> call, final Throwable t) {
                Log.d(TAG, "error");
                t.printStackTrace();
            }
        });
    }

    private void setSupportLangToView(Map<String, String> supportedLangs, List<String> supportedLangDirs) {
        for (String lang: supportedLangDirs) {
            mLanguagePairs.add(new LanguagePair(lang));
        }

        for (Map.Entry<String, String> entry: supportedLangs.entrySet()) {
            mLanguageTranscripts.add(new LanguageTranscript(entry.getValue(), entry.getKey()));
            mSupportedLanguages.add(entry.getValue());
        }

        mView.setLanguages();

        setDefaultLanguages(mSPreferenceManager.getCurrentLanguages());
        mView.invalidateSpinnerView();
    }

    private void setDefaultLanguages(final LanguagePair currentLanguages) {
        mFrom = new LanguageTranscript(getTranscript(currentLanguages.getLanguageFrom()), currentLanguages.getLanguageFrom());
        mTo = new LanguageTranscript(getTranscript(currentLanguages.getLanguageTo()), currentLanguages.getLanguageTo());
        mView.setFromSpinnerSelection((mSupportedLanguages.indexOf(mFrom.getName())));
        mView.setToSpinnerPosition(mSupportedLanguages.indexOf(mTo.getName()));
    }

    private String getTranscript(String language) {
        for (LanguageTranscript transcript: mLanguageTranscripts) {
            if (transcript.getTranscript().equals(language)) {
                return transcript.getName();
            }
        }

        return "error";
    }

    @Override
    public void getTranslate(String message) {
        Log.d(TAG, "GET TRANSLATE FOR MESSAGE: " + message);
        Log.d(TAG, "GET LANG: " + getCurrentLanguagePair().getLangPairStringValue());

        mView.showProgressBar();

        Call<TranslateResponse> call = mYandexTranslateApi.loadTranslateLang(message, getCurrentLanguagePair().getLangPairStringValue());
        call.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(final Call<TranslateResponse> call, final Response<TranslateResponse> response) {
                mView.showTranslateResult(response.body().getResponseText());
                mView.dismissProgressBar();
                mHistoryDataService.addHistoryData(message, response.body().getResponseText(), getCurrentLanguagePair());
                mSPreferenceManager.saveCurrentLanguages(getCurrentLanguagePair());
            }

            @Override
            public void onFailure(final Call<TranslateResponse> call, final Throwable t) {
                mView.showError(t.getMessage());
            }
        });
    }

    public List<String> getSupportedLang() {
        return mSupportedLanguages;
    }

    private LanguagePair getCurrentLanguagePair() {
        Log.d(TAG, "get current lang pair:  " + mFrom.toString() + ", to: " + mTo.toString());
        return new LanguagePair(mFrom.getTranscript(), mTo.getTranscript());
    }

    public void fromSelectItem(final int position) {
        mFrom = mLanguageTranscripts.get(position);
        Log.d(TAG, "new MFROM: " + mFrom.toString() + "position: " + position);
    }

    public void toSelectItem(final int position) {
        mTo = mLanguageTranscripts.get(position);
        Log.d(TAG, "new MTO: " + mTo.toString() + "position: " + position);
    }

    public void swapLangs() {
        swapTranscript();
        swapView();
    }

    private void swapTranscript() {
        Log.d(TAG, "swap transcript");
        LanguageTranscript tmp = mFrom;
        mFrom = mTo;
        mTo = tmp;
    }

    private void swapView() {
        int tmpPosition = mView.getFromSpinnerPosition();
        mView.setFromSpinnerSelection(mView.getToSpinnerPosition());
        mView.setToSpinnerPosition(tmpPosition);
    }
}
