package ng.com.yandextranslate.ui.fragment.traslate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ng.com.yandextranslate.App;
import ng.com.yandextranslate.R;
import ng.com.yandextranslate.model.pojo.LanguagePair;
import ng.com.yandextranslate.presentation.contract.translate.TranslateContract;
import ng.com.yandextranslate.presentation.implementation.translate.DaggerTranslateComponent;
import ng.com.yandextranslate.presentation.implementation.translate.TranslateModule;
import ng.com.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;
import ng.com.yandextranslate.ui.fragment.BaseFragment;
import ng.com.yandextranslate.ui.view.LanguageSelectView;

/**
 * Created by NG on 15.03.17.
 */

public class TranslateFragment extends BaseFragment implements TranslateContract.View {

    @BindView(R.id.test_text_view)
    TextView mTestTextView;
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
        View rootView = inflater.inflate(R.layout.translate_fragment, container, false);

        ButterKnife.bind(this, rootView);

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
