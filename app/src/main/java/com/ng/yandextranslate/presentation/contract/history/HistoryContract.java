package com.ng.yandextranslate.presentation.contract.history;

import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.presentation.contract.BaseContract;

import java.util.List;

/**
 * Created by NG on 11.04.17.
 */

public interface HistoryContract extends BaseContract {

    interface View extends BaseContract.BaseView {
        void showEmptyView();
    }

    interface Presenter extends BaseContract.BasePresenter {
        List<HistoryData> getHistory();

        void clearHistory();
    }
}
