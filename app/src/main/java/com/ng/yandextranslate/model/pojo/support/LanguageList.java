package com.ng.yandextranslate.model.pojo.support;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by NG on 19.03.17.
 */

public class LanguageList {

    @SerializedName("dirs")
    List<String> listDirs;

    @SerializedName("langs")
    Map<String, String> mapLangs;

    public List<String> getListDirs() {
        return listDirs;
    }

    public void setListDirs(List<String> listDirs) {
        this.listDirs = listDirs;
    }

    public Map<String, String> getMapLangs() {
        return mapLangs;
    }

    public void setMaoLangs(Map<String, String> mapLangs) {
        this.mapLangs = mapLangs;
    }

    @Override
    public String toString() {
        return "langs: " + Arrays.toString(listDirs.toArray()) + "\n map size: " + mapLangs.size() ;
    }
}
