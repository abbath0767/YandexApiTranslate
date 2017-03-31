package com.ng.yandextranslate.controller.network;

import android.util.Log;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by NG on 19.03.17.
 */

public class RequestHelper {

    private static Action1<Throwable> mOnError = throwable -> {
        Log.d("DEBUG_TAG", "Error. " + throwable.getMessage());
        throwable.printStackTrace();
    };

    public static <T> Subscription asyncRequest(Observable<T> observable, Action1<? super T> action) {
        return asyncRequest(observable, action, mOnError);
    }

    public static <T> Subscription asyncRequest(Observable<T> observable, Action1<? super T> action, Action1<Throwable> mOnError) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action, mOnError);
    }
}
