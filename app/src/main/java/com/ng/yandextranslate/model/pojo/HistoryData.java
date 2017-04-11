package com.ng.yandextranslate.model.pojo;

import java.util.Date;

/**
 * Created by NG on 11.04.17.
 */

public class HistoryData {

    private int key;
    private LanguagePair languagePair;
    private String originalText;
    private String translateText;
    private long time;
    private boolean favorite;

    public HistoryData(int key, LanguagePair languagePair, String originalText, String translateText, long time) {
        this.key = key;
        this.languagePair = languagePair;
        this.originalText = originalText;
        this.translateText = translateText;
        this.time = time;
        this.favorite = false;
    }

    public HistoryData(int key, LanguagePair languagePair, String originalText, String translateText) {
        this.key = key;
        this.languagePair = languagePair;
        this.originalText = originalText;
        this.translateText = translateText;
        this.time = new Date().getTime();
        this.favorite = false;
    }

    public HistoryData(LanguagePair languagePair, String originalText, String translateText, long time) {
        this.languagePair = languagePair;
        this.originalText = originalText;
        this.translateText = translateText;
        this.time = time;
        this.favorite = false;
    }

    public HistoryData(LanguagePair languagePair, String originalText, String translateText) {
        this.languagePair = languagePair;
        this.originalText = originalText;
        this.translateText = translateText;
        this.time = new Date().getTime();
        this.favorite = false;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public LanguagePair getLanguagePair() {
        return languagePair;
    }

    public void setLanguagePair(LanguagePair languagePair) {
        this.languagePair = languagePair;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getTranslateText() {
        return translateText;
    }

    public void setTranslateText(String translateText) {
        this.translateText = translateText;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "HistoryData{" +
                "key=" + key +
                ", languagePair=" + languagePair +
                ", originalText='" + originalText + '\'' +
                ", translateText='" + translateText + '\'' +
                ", time=" + time +
                '}';
    }
}
