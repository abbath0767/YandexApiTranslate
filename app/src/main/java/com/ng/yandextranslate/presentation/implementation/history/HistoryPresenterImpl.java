package com.ng.yandextranslate.presentation.implementation.history;

import com.ng.yandextranslate.controller.data.Repository;
import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.presentation.contract.history.HistoryContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryPresenterImpl implements HistoryContract.Presenter {

    public static final String TAG = HistoryPresenterImpl.class.getSimpleName();

    HistoryContract.View mView;
    HistoryDataService mHistoryDataService;

    @Inject
    public HistoryPresenterImpl(HistoryContract.View view, HistoryDataService historyDataService) {
        this.mView = view;
        this.mHistoryDataService = historyDataService;
        initAndShowHistory();
    }

    private void initAndShowHistory() {
        //init history
        //show history
    }

    @Override
    public List<HistoryData> getHistory() {
        return mHistoryDataService.getHistoryDataList();
    }
}
