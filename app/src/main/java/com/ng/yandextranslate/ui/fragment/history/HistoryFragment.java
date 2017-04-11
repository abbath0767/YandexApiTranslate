package com.ng.yandextranslate.ui.fragment.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ng.yandextranslate.App;
import com.ng.yandextranslate.R;
import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.presentation.contract.history.HistoryContract;
import com.ng.yandextranslate.presentation.implementation.history.DaggerHistoryComponent;
import com.ng.yandextranslate.presentation.implementation.history.HistoryModule;
import com.ng.yandextranslate.presentation.implementation.history.HistoryPresenterImpl;
import com.ng.yandextranslate.ui.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryFragment extends BaseFragment implements HistoryContract.View {

    @BindView(R.id.HELLO)
    TextView hellotextView;

    @Inject
    HistoryPresenterImpl mPresenter;

    public static Fragment newInstance(Bundle args) {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this, rootView);

        DaggerHistoryComponent.builder()
                .appComponent(App.getAppComponent())
                .historyModule(new HistoryModule(this))
                .build().inject(this);

        //todo for test
        List<HistoryData> testList = mPresenter.getHistory();
        StringBuilder sb = new StringBuilder("LIST: ");
        for (HistoryData historyData: testList) {
            sb.append(historyData.getOriginalText() + " - " + historyData.getTranslateText()
                    + " " + historyData.getLanguagePair().getLangPairStringValue() + "\n");
        }

        hellotextView.setText(sb.toString().trim());

        return rootView;
    }

}
