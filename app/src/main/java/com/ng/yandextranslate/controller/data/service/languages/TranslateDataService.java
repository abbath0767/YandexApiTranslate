package com.ng.yandextranslate.controller.data.service.languages;

import android.content.Context;
import android.util.Log;

import com.ng.yandextranslate.controller.data.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslateDataService {

    private static final String TAG = TranslateDataService.class.getSimpleName();

    private Repository repository;

    public TranslateDataService(Repository repository) {
        this.repository = repository;
    }

    public Map<String, String> getLanguages() {
        return Collections.EMPTY_MAP;
    }

    public List<String> getLanguagesDirs() {
        return Collections.EMPTY_LIST;
    }

    public void saveNewSupportLangs(Map<String, String> supportedLangs) {

    }

    public void saveNewSupportLangsDirs(List<String> supportedLangDirs) {
    }
}
