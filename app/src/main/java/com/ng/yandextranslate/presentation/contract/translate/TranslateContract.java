package com.ng.yandextranslate.presentation.contract.translate;

import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;
import com.ng.yandextranslate.presentation.contract.BaseContract;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract extends BaseContract {

    interface View extends BaseContract.BaseView {
        void showTranslateResult(String message);
        void setLanguages();

        void showProgressBar();
        void dismissProgressBar();

        void showDialog(String message);

        int getFromSpinnerPosition();

        int getToSpinnerPosition();

        void setFromSpinnerSelection(int toSpinnerPosition);

        void setToSpinnerPosition(int tmpPosition);

        void invalidateSpinnerView();
    }

    interface Presenter extends BaseContract.BasePresenter {
        void getTranslate(String message);
    }
}
