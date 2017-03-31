package com.ng.yandextranslate.presentation.contract.translate;

import java.util.List;
import java.util.Map;

import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.support.TranslateResponse;

import rx.Observable;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract {

    interface View {
        void showTranslateResult(String message);
        void setDefaultLanguages(LanguagePair languagePair);
        void setLanguages(Map<String, String> supportedLangs, List<String> supportedLangDirs);

        String getFrom();
        String getTo();
    }

    interface Presenter {
        void saveToHistory();
        Observable<String> getTranslate(String message, LanguagePair languagePair);
    }
}
