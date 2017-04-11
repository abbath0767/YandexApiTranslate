package com.ng.yandextranslate.presentation.contract.translate;

import java.util.List;
import java.util.Map;

import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;
import com.ng.yandextranslate.presentation.contract.BaseContract;

import rx.Observable;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract extends BaseContract {

    interface View extends BaseContract.BaseView {
        void showTranslateResult(String message);
        void setDefaultLanguages(LanguagePair languagePair);
        void setLanguages(Map<String, String> supportedLangs, List<String> supportedLangDirs);

        LanguageTranscript getFrom();
        LanguageTranscript getTo();

        void showDialog(String message);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void saveToHistory();
        void getTranslate(String message, LanguagePair languagePair);
    }
}
