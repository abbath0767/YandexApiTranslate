package com.ng.yandextranslate.ui.fragment.traslate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.support.TranslateResponse;
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
    @BindView(R.id.translate_edit_text_in)
    EditText mEditTextIn;
    @BindView(R.id.translate_text_view_out)
    TextView mTextViewOut;

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

        //remove rx
        createTextChangeObservable()
                .observeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        showProgressBar();
                    }
                })
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return mPresenter.getTranslate(s, getCurrentLanguagePair());
//                    }
//                })
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        Log.d("TAG", "FLAT MAP");
                        return mPresenter.getTranslate(s, getCurrentLanguagePair());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("TAG", "ACTION AFTER");
                        hideProgressBar();
                        showResult(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("TAG", "ERROR: " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });

        return rootView;
    }

    private void showResult(String s) {
        mTextViewOut.setText(s);
    }

    private void showProgressBar() {
        //todo show bar
        Log.d("TAG", "SHOW PROGRESS");
    }

    private void hideProgressBar() {
        //todo hide progress
        Log.d("TAG", "HIDE PROGRESS");
    }

    private Observable<String> createTextChangeObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {

                final TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        subscriber.onNext(s.toString());
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //do nothing
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        //do nothing
                    }
                };

                mEditTextIn.addTextChangedListener(textWatcher);
            }
        }).debounce(3, TimeUnit.SECONDS);
    }

    @Override
    public void setLanguages(Map<String, String> supportedLangs, List<String> supportLangDirs) {
        Log.d("TAG", "T.FRAGMENT. supportLangDirs: " + Arrays.toString(supportLangDirs.toArray()));

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : supportedLangs.entrySet()) {
            Log.d("TAG", "map entry, key: " + entry.getKey() + ", value: " + entry.getValue());

            list.add(entry.getValue());
        }
        mLanguageSelectView.setLanguages(list, supportLangDirs);
    }

    @Override
    public String getFrom() {
        return mLanguageSelectView.getFrom();
    }

    @Override
    public String getTo() {
        return mLanguageSelectView.getTo();
    }

    private LanguagePair getCurrentLanguagePair() {
        return mLanguageSelectView.getLanguagePair();
    }

    @Override
    public void showTranslateResult(String message) {
    }

    @Override
    public void setDefaultLanguages(LanguagePair languagePair) {

    }
}
