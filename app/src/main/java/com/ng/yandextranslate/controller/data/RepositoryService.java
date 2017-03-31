package com.ng.yandextranslate.controller.data;

import java.util.List;
import java.util.Map;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface RepositoryService {

    Map<String,String> getLanguages();

    List<String> getLanguagesDirs();

    void saveNewSupportLangs(Map<String, String> supportedLangs);

    void saveNewSupportLangsDirs(List<String> supportedLangDirs);
}
