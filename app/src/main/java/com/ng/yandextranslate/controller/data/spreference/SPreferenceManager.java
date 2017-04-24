package com.ng.yandextranslate.controller.data.spreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ng.yandextranslate.model.pojo.LanguagePair;
import com.ng.yandextranslate.util.AppPrefs;

/**
 * Created by NG on 24.04.17.
 */

public class SPreferenceManager {

    private static final String TAG = SPreferenceManager.class.getSimpleName();

    private Context mContext;

    public SPreferenceManager(Context context) {
        mContext = context;
    }

    public void saveCurrentLanguages(LanguagePair languagePair) {
        Log.d(TAG, "save current languages: " + languagePair.toString());
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
        editor.putString(AppPrefs.LANGUAGE_FROM, languagePair.getLanguageFrom());
        editor.putString(AppPrefs.LANGUAGE_TO, languagePair.getLanguageTo());
        editor.apply();
    }

    public LanguagePair getCurrentLanguages() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        String langFrom = prefs.getString(AppPrefs.LANGUAGE_FROM, AppPrefs.DEFAULT_LANGUAGE_FROM);
        String langTo = prefs.getString(AppPrefs.LANGUAGE_TO, AppPrefs.DEFAULT_LANGUAGE_TO);
        Log.d(TAG, "load current languages: " + langFrom + ", " + langTo);
        return new LanguagePair(langFrom, langTo);
    }
}
