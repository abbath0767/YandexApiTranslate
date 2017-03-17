package ng.com.yandextranslate.ui.fragment.traslate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ng.com.yandextranslate.R;
import ng.com.yandextranslate.presentation.contract.translate.TranslateContract;
import ng.com.yandextranslate.presentation.implementation.translate.DaggerTranslateComponent;
import ng.com.yandextranslate.presentation.implementation.translate.TranslateModule;
import ng.com.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;
import ng.com.yandextranslate.ui.fragment.BaseFragment;

/**
 * Created by NG on 15.03.17.
 */

public class TranslateFragment extends BaseFragment implements TranslateContract.View {

    @Inject
    TranslatePresenterImpl mPresenter;

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TranslateFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.translate_fragment, container, false);

        DaggerTranslateComponent.builder()
                .translateModule(new TranslateModule(this))
                .build().inject(this);

        return rootView;
    }

    @Override
    public void showTranslateResult(String message) {
    }
}
