package com.ng.yandextranslate;

import javax.inject.Singleton;

import dagger.Component;
import com.ng.yandextranslate.controller.data.DataModule;
import com.ng.yandextranslate.controller.data.RepositoryService;
import com.ng.yandextranslate.controller.network.NetworkModule;
import com.ng.yandextranslate.controller.network.YandexTranslateApi;

/**
 * Created by NGusarov on 17/03/17.
 */

// component is alive while the application is live. Global
@Component(modules = {AppModule.class, DataModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    YandexTranslateApi yandexApi();
    RepositoryService repository();
}
