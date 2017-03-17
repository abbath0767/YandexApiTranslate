package ng.com.yandextranslate;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by NGusarov on 17/03/17.
 */

@Module
public class AppModule {

    Context mApplication;

    public AppModule(Context application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }
}
