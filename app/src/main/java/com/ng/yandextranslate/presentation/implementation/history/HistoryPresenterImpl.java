package com.ng.yandextranslate.presentation.implementation.history;

import com.ng.yandextranslate.controller.data.Repository;
import com.ng.yandextranslate.presentation.contract.history.HistoryContract;

import javax.inject.Inject;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryPresenterImpl implements HistoryContract.Presenter {

    public static final String TAG = HistoryPresenterImpl.class.getSimpleName();

    HistoryContract.View mView;
    Repository mRepo;

    @Inject
    public HistoryPresenterImpl(HistoryContract.View mView, Repository mRepo) {
        this.mView = mView;
        this.mRepo = mRepo;
        initAndShowHistory();
    }

    private void initAndShowHistory() {
        //init history
        //show history
    }
}
