package com.ng.yandextranslate.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.model.pojo.LanguageTranscript;

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

    //todo refactor this! (╯°□°）╯︵ ┻━┻"
    private List<String> supportedLanguages;
    private List<LanguageTranscript> transcriptList;
    private List<LanguagePair> languagePairList;
    private LanguageTranscript mFrom;
    private LanguageTranscript mTo;

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
        languagePairList = new ArrayList<>();
        transcriptList = new ArrayList<>();
        supportedLanguages = new ArrayList<>();
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

    public void setLanguages(Map<String, String> langs, List<String> supportedLangDirs) {
        for (String lang: supportedLangDirs) {
            languagePairList.add(new LanguagePair(lang));
        }

        for (Map.Entry<String, String> entry: langs.entrySet()) {
            transcriptList.add(new LanguageTranscript(entry.getValue(), entry.getKey()));
            supportedLanguages.add(entry.getValue());
        }

        initAdapters();
    }

    private void initAdapters() {
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, supportedLanguages);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFromSpinner.setAdapter(adapterFrom);
        //todo another adapter? yes need 2 adapters with two lists of languages
        mToSpinner.setAdapter(adapterFrom);
        setOnItemClickListeners();
    }

    //todo move logic to presenter
    private void setOnItemClickListeners() {
        mFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mFrom = transcriptList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTo = transcriptList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public LanguageTranscript getFrom() {
        return mFrom;
    }

    public LanguageTranscript getTo() {
        return mTo;
    }

    public LanguagePair getLanguagePair() {
        return new LanguagePair(mFrom.getTranscript(), mTo.getTranscript());
    }

    public void swapLanguages() {
        Log.d(TAG, "swap");
        swapTranscript();
        swapView();
    }

    private void swapTranscript() {
        Log.d(TAG, "swap transcript");
        LanguageTranscript tmp = mFrom;
        mFrom = mTo;
        mTo = tmp;
    }

    private void swapView() {
        int tmpPosition = mFromSpinner.getSelectedItemPosition();
        mFromSpinner.setSelection(mToSpinner.getSelectedItemPosition());
        mToSpinner.setSelection(tmpPosition);
    }

    public void setLanguagePair(final LanguagePair languagePair) {
        mFrom = new LanguageTranscript(getTranscript(languagePair.getLanguageFrom()), languagePair.getLanguageFrom());
        mTo = new LanguageTranscript(getTranscript(languagePair.getLanguageTo()), languagePair.getLanguageTo());
        mFromSpinner.setSelection(supportedLanguages.indexOf(mFrom.getName()));
        mToSpinner.setSelection(supportedLanguages.indexOf(mTo.getName()));
    }

    private String getTranscript(String language) {
        for (LanguageTranscript transcript: transcriptList) {
            if (transcript.getTranscript().equals(language)) {
                return transcript.getName();
            }
        }
        return "error";
    }
}
