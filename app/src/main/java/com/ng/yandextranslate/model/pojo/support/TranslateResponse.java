package com.ng.yandextranslate.model.pojo.support;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NG on 31.03.17.
 */
public class TranslateResponse {

    @SerializedName("code")
    int code;

    @SerializedName("lang")
    String lang;

    @SerializedName("text")
    String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TranslateResponse{" +
                "code=" + code +
                ", lang='" + lang + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
