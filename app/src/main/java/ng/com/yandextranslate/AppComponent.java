package ng.com.yandextranslate;

import javax.inject.Singleton;

import dagger.Component;
import ng.com.yandextranslate.controller.data.DataModule;
import ng.com.yandextranslate.controller.data.RepositoryService;
import ng.com.yandextranslate.controller.network.NetworkModule;
import ng.com.yandextranslate.controller.network.YandexTranslateApi;

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
