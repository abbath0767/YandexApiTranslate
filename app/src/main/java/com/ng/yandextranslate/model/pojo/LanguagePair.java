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

    public String getLangPairStringValue() {
        return languageFrom + "-" + languageTo;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    @Override
    public String toString() {
        return "LanguagePair{" +
                "languageFrom='" + languageFrom + '\'' +
                ", languageTo='" + languageTo + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LanguagePair that = (LanguagePair) o;

        if (languageFrom != null ? !languageFrom.equals(that.languageFrom) : that.languageFrom != null)
            return false;
        return languageTo != null ? languageTo.equals(that.languageTo) : that.languageTo == null;

    }

    @Override
    public int hashCode() {
        int result = languageFrom != null ? languageFrom.hashCode() : 0;
        result = 31 * result + (languageTo != null ? languageTo.hashCode() : 0);
        return result;
    }
}
