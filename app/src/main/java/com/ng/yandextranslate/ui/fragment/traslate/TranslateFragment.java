package com.ng.yandextranslate.ui.fragment.traslate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;
import com.ng.yandextranslate.presentation.implementation.translate.DaggerTranslateComponent;
import com.ng.yandextranslate.presentation.implementation.translate.TranslateModule;
import com.ng.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;
import com.ng.yandextranslate.ui.fragment.BaseFragment;
import com.ng.yandextranslate.ui.view.LanguageSelectView;

/**
 * Created by NG on 15.03.17.
 */

public class TranslateFragment extends BaseFragment implements TranslateContract.View {

    @BindView(R.id.translate_language_select)
    LanguageSelectView mLanguageSelectView;

    @Inject
    TranslatePresenterImpl mPresenter;

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TranslateFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_translate, container, false);

        ButterKnife.bind(this, rootView);

        //todo mb App.getAppComponent.translateModulre(new TranslateModule(this).inject(this))
        DaggerTranslateComponent.builder()
                .appComponent(App.getAppComponent())
                .translateModule(new TranslateModule(this))
                .build().inject(this);

        return rootView;
    }

    @Override
    public void setLanguages(Map<String, String> supportedLangs) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : supportedLangs.entrySet()) {
            list.add(entry.getValue());
        }
        mLanguageSelectView.setLanguages(list);
    }

    @Override
    public String getFrom() {
        return mLanguageSelectView.getFrom();
    }

    @Override
    public String getTo() {
        return mLanguageSelectView.getTo();
    }

    @Override
    public void showTranslateResult(String message) {
    }

    @Override
    public void setDefaultLanguages(LanguagePair languagePair) {

    }
}
