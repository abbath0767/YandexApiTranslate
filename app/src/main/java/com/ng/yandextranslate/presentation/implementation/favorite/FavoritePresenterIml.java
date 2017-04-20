package com.ng.yandextranslate.presentation.implementation.favorite;

import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.presentation.contract.favorite.FavoriteContract;
import com.ng.yandextranslate.presentation.contract.history.HistoryContract;

import javax.inject.Inject;

/**
 * Created by NG on 20.04.17.
 */

public class FavoritePresenterIml implements FavoriteContract.Presenter {

    private HistoryDataService mHistoryDataService;
    private FavoriteContract.View mView;

    @Inject
    public FavoritePresenterIml(FavoriteContract.View view, HistoryDataService historyDataService) {
        mView = view;
        mHistoryDataService = historyDataService;
    }
}
