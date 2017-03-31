package com.ng.yandextranslate.controller.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NGusarov on 17/03/17.
 */

public class RepositoryServiceImplDumb implements RepositoryService {

    private Map<String, String> supportedLangs;
    private List<String> supportedLangsDirs;

    public RepositoryServiceImplDumb(Context context) {
        Log.d("TAG", "dumb repository constructor! context null? " + (context == null));
        supportedLangs = new HashMap<>();
        supportedLangsDirs = new ArrayList<>();
    }

    @Override
    public Map<String, String> getLanguages() {
        return supportedLangs;
    }

    @Override
    public List<String> getLanguagesDirs() {
        return supportedLangsDirs;
    }

    @Override
    public void saveNewSupportLangs(Map<String, String> supportedLangs) {
        this.supportedLangs = supportedLangs;
    }

    @Override
    public void saveNewSupportLangsDirs(List<String> supportedLangDirs) {
        this.supportedLangsDirs = supportedLangsDirs;
    }
}
