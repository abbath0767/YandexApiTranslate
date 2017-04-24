package com.ng.yandextranslate.util;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.ng.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import static com.ng.yandextranslate.util.AppPrefs.DELAY_BEFORE_POST;

/**
 * Created by NG on 24.04.17.
 */

public class DebounceTextWatcher implements TextWatcher {

    private Timer timer = new Timer();
    private TranslatePresenterImpl mTranslatePresenter;

    @Inject
    public DebounceTextWatcher(TranslatePresenterImpl presenter) {
        this.mTranslatePresenter = presenter;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(final Editable s) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(s.toString())) {
                            mTranslatePresenter.getTranslate(s.toString());
                        }
                    }
                },
                DELAY_BEFORE_POST
        );
    }
}
