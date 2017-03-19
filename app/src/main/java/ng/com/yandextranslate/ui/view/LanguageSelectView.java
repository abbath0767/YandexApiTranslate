package ng.com.yandextranslate.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ng.com.yandextranslate.R;

/**
 * Created by NG on 19.03.17.
 */

public class LanguageSelectView extends LinearLayout {

    private Context context;
    private AdapterView.OnItemSelectedListener listenerFrom;
    private AdapterView.OnItemSelectedListener listenerTo;

    @BindView(R.id.language_select_swap_button)
    ImageButton mRefreshImageButton;
    @BindView(R.id.language_select_spinner_from)
    Spinner mFromSpinner;
    @BindView(R.id.language_select_spinner_to)
    Spinner mToSpinner;

    List<String> supportedLanguages;

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
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setLanguages(List<String> supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
        initAdapters();
    }

    private void initAdapters() {
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, supportedLanguages);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFromSpinner.setAdapter(adapterFrom);
        //todo another adapter?
        mToSpinner.setAdapter(adapterFrom);
    }

    public void setOnItemClickListeners(AdapterView.OnItemSelectedListener listenerFrom,
                                        AdapterView.OnItemSelectedListener listenerTo) {
        this.listenerFrom = listenerFrom;
        this.listenerTo = listenerTo;
        mFromSpinner.setOnItemSelectedListener(this.listenerFrom);
        mToSpinner.setOnItemSelectedListener(this.listenerTo);
    }
}
