package com.ng.yandextranslate.ui.fragment.traslate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.ng.yandextranslate.presentation.contract.translate.TranslateContract;
import com.ng.yandextranslate.presentation.implementation.translate.DaggerTranslateComponent;
import com.ng.yandextranslate.presentation.implementation.translate.TranslateComponent;
import com.ng.yandextranslate.presentation.implementation.translate.TranslateModule;
import com.ng.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;
import com.ng.yandextranslate.ui.fragment.BaseFragment;
import com.ng.yandextranslate.ui.view.LanguageSelectView;
import com.ng.yandextranslate.util.DoneOnEditorActionListener;

import static com.ng.yandextranslate.util.AppPrefs.DELAY_BEFORE_POST;

/**
 * Created by NG on 15.03.17.
 */

public class TranslateFragment extends BaseFragment implements TranslateContract.View {

    private static final String TAG = TranslateFragment.class.getSimpleName();

    @BindView(R.id.translate_language_select)
    LanguageSelectView mLanguageSelectView;
    @BindView(R.id.translate_edit_text_in)
    EditText mEditTextIn;
    @BindView(R.id.translate_text_view_out)
    TextView mTextViewOut;
    @BindView(R.id.translate_progress_for_text)
    ProgressBar mProgressBar;

    private static TranslateComponent mTranslateComponent;

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

        mTranslateComponent = buildTranslateComponent();
        mTranslateComponent.inject(this);

        mEditTextIn.setOnEditorActionListener(new DoneOnEditorActionListener());

        mEditTextIn.addTextChangedListener(
                new TextWatcher() {
                    private Timer timer = new Timer();

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
                                            mPresenter.getTranslate(s.toString());
                                        }
                                    }
                                },
                                DELAY_BEFORE_POST
                        );
                    }
                }
        );

//        mLanguageSelectView.setOnClickListener(v -> {
//            mPresenter.swapLangs();
//            mLanguageSelectView.invalidate();
//        });

        mLanguageSelectView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });

//        mLanguageSelectView.setOnItemClickListeners(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
//                mPresenter.fromSelectItem(position);
//            }
//            @Override
//            public void onNothingSelected(final AdapterView<?> parent) {
//            }
//        },
//                new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
//                mPresenter.toSelectItem(position);
//            }
//            @Override
//            public void onNothingSelected(final AdapterView<?> parent) {
//
//            }
//        });
        Log.d(TAG, "HASHCODE : " + mPresenter.hashCode());

        return rootView;
    }

    public static TranslateComponent getTranslateComponent() {
        return mTranslateComponent;
    }

    private TranslateComponent buildTranslateComponent() {
        return DaggerTranslateComponent.builder()
                .appComponent(App.getAppComponent())
                .translateModule(new TranslateModule(this))
                .build();
    }

    public void showDialog(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getFromSpinnerPosition() {
        return mLanguageSelectView.getFromSpinnerPosition();
    }

    @Override
    public int getToSpinnerPosition() {
        return mLanguageSelectView.getToSpinnerPosition();
    }

    @Override
    public void setFromSpinnerSelection(final int position) {
        mLanguageSelectView.setFromSpinnerPosition(position);
    }

    @Override
    public void setToSpinnerPosition(final int position) {
        mLanguageSelectView.setToSpinnerPosition(position);
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
    public void setLanguages() {
        mLanguageSelectView.setLanguages();
    }

    @Override
    public void showTranslateResult(String message) {
        mTextViewOut.setText(message);
    }

    @Override
    public void invalidateSpinnerView() {
        mLanguageSelectView.invalidate();
    }
}
