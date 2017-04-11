package com.ng.yandextranslate.ui.fragment.traslate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;
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

    private static final String TAG = TranslateFragment.class.getSimpleName();

    private static final long DELAY_BEFORE_POST = 1000;

    @BindView(R.id.translate_language_select)
    LanguageSelectView mLanguageSelectView;
    @BindView(R.id.translate_edit_text_in)
    EditText mEditTextIn;
    @BindView(R.id.translate_text_view_out)
    TextView mTextViewOut;
    @BindView(R.id.translate_progress_for_text)
    ProgressBar mProgressBar;

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


        mEditTextIn.addTextChangedListener(
                new TextWatcher() {
                    private Timer timer=new Timer();

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (timer != null) {
                            timer.cancel();
                        }
                    }

                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        if (!TextUtils.isEmpty(s.toString())) {
                                            mPresenter.getTranslate(s.toString(), getCurrentLanguagePair());
                                        }
                                    }
                                },
                                DELAY_BEFORE_POST
                        );
                    }
                }
        );

        return rootView;
    }

    public void showDialog(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //todo change textView and progressBar to one custom view
    @Override
    public void showProgressBar() {
        Log.d(TAG, "showProgressBar");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewOut.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void dismissProgressBar() {
        Log.d(TAG, "dismissProgressBar");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextViewOut.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void setLanguages(Map<String, String> supportedLangs, List<String> supportLangDirs) {
        Log.d(TAG, "setLanguages. supportLangDirs: " + Arrays.toString(supportLangDirs.toArray()));

        List<String> list = new ArrayList<>();
        List<String> keyList = new ArrayList<>();
        for (Map.Entry<String, String> entry : supportedLangs.entrySet()) {
            keyList.add(entry.getKey());
            list.add(entry.getValue());
        }

        mLanguageSelectView.setLanguages(supportedLangs, supportLangDirs);
    }

    @Override
    public LanguageTranscript getFrom() {
        return mLanguageSelectView.getFrom();
    }

    @Override
    public LanguageTranscript getTo() {
        return mLanguageSelectView.getTo();
    }

    private LanguagePair getCurrentLanguagePair() {
        return mLanguageSelectView.getLanguagePair();
    }

    @Override
    public void showTranslateResult(String message) {
        mTextViewOut.setText(message);
    }

    @Override
    public void setDefaultLanguages(LanguagePair languagePair) {

    }
}
