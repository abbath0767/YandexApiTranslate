package com.ng.yandextranslate.presentation.contract;

import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;

import java.util.List;
import java.util.Map;

/**
 * Created by NG on 11.04.17.
 */

public interface BaseContract {

    interface BaseView {
        void showProgressBar();
        void dismissProgressBar();
        void showError(String errorMessage);
    }

    interface BasePresenter {
    }
}
