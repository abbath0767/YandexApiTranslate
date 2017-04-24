package com.ng.yandextranslate.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;


import butterknife.BindView;
import butterknife.ButterKnife;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.presentation.implementation.translate.TranslatePresenterImpl;
import com.ng.yandextranslate.ui.fragment.traslate.TranslateFragment;

import javax.inject.Inject;

/**
 * Created by NG on 19.03.17.
 */

public class LanguageSelectView extends LinearLayout {

    private static final String TAG = LanguageSelectView.class.getSimpleName();
    private Context context;

    @BindView(R.id.language_select_swap_button)
    ImageButton mSwapImageButton;
    @BindView(R.id.language_select_spinner_from)
    Spinner mFromSpinner;
    @BindView(R.id.language_select_spinner_to)
    Spinner mToSpinner;

    ArrayAdapter<String> adapter;

    @Inject
    TranslatePresenterImpl mTranslatePresenter;

    public LanguageSelectView(Context context) {
        super(context);
        initView(context);
    }

    public LanguageSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.view_language_select, this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        TranslateFragment.getTranslateComponent().inject(this);

        mSwapImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                mTranslatePresenter.swapLangs();
            }
        });

        mFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                mTranslatePresenter.fromSelectItem(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                mTranslatePresenter.toSelectItem(position);
                mTranslatePresenter.forceTranslate();
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {

            }
        });
    }

    public void setOnClickListener(OnClickListener listener) {
        mSwapImageButton.setOnClickListener(listener);
    }

    public void setOnTouchListener(OnTouchListener listener) {
        mFromSpinner.setOnTouchListener(listener);
        mToSpinner.setOnTouchListener(listener);
        mSwapImageButton.setOnTouchListener(listener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setLanguages() {
        initAdapters();
    }

    private void initAdapters() {
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, mTranslatePresenter.getSupportedLang());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFromSpinner.setAdapter(adapter);
        mToSpinner.setAdapter(adapter);
    }


    public int getFromSpinnerPosition() {
        return mFromSpinner.getSelectedItemPosition();
    }

    public int getToSpinnerPosition() {
        return mToSpinner.getSelectedItemPosition();
    }

    public void setFromSpinnerPosition(final int position) {
        mFromSpinner.setSelection(position);
    }

    public void setToSpinnerPosition(final int position) {
        mToSpinner.setSelection(position);
    }
}
