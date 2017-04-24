package com.ng.yandextranslate;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by NG on 15.03.17.
 */

public class App extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        mAppComponent = buildAppComponent();
    }

    private AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
