package com.ng.yandextranslate;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

import com.ng.yandextranslate.controller.data.db.Repository;
import com.ng.yandextranslate.controller.data.db.RepositoryModule;
import com.ng.yandextranslate.controller.data.service.history.HistoryDataModule;
import com.ng.yandextranslate.controller.data.service.history.HistoryDataService;
import com.ng.yandextranslate.controller.network.NetworkModule;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;

/**
 * Created by NGusarov on 17/03/17.
 */

// component is alive while the application is live. Global
@Component(modules = {AppModule.class, NetworkModule.class,
                        RepositoryModule.class, HistoryDataModule.class})
@Singleton
public interface AppComponent {
    YandexTranslateApi yandexApi();
    Repository repository();
    HistoryDataService historyDataService();
    Context context();

}

