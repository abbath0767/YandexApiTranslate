package com.ng.yandextranslate.presentation.contract.favorite;

import com.ng.yandextranslate.model.pojo.HistoryData;
import com.ng.yandextranslate.presentation.contract.BaseContract;

import java.util.List;

/**
 * Created by NG on 20.04.17.
 */

public interface FavoriteContract  extends BaseContract {

    interface View {
        void showEmptyView();

        void notifyData();
    }

    interface Presenter {
        List<HistoryData> getFavorite();

        void unMackeFavorite(int key, boolean isChecked);

        void notifyData();

        void unMakeAllFavorites();
    }
}
