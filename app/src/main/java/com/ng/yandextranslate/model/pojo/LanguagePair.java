package com.ng.yandextranslate.model.pojo;

/**
 * Created by NG on 19.03.17.
 */

public class LanguagePair {

    private String languageFrom;
    private String languageTo;

    public LanguagePair(String dirs) {
        languageFrom = dirs.split("-")[0];
        languageTo = dirs.split("-")[1];
    }

    public LanguagePair(String languageFrom, String languageTo) {
        this.languageFrom = languageFrom;
        this.languageTo = languageTo;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }
}
