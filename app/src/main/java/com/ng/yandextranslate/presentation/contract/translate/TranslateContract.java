package com.ng.yandextranslate.presentation.contract.translate;

import java.util.Map;

import com.ng.yandextranslate.model.pojo.LanguagePair;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract {

    interface View {
        void showTranslateResult(String message);
        void setDefaultLanguages(LanguagePair languagePair);
        void setLanguages(Map<String, String> supportedLangs);
        String getFrom();
        String getTo();
    }

    interface Presenter {
        void saveToHistory();
        void getTranslate(String message);
    }
}
