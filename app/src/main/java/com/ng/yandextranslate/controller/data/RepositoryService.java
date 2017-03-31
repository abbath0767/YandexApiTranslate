package com.ng.yandextranslate.controller.data;

import java.util.Map;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface RepositoryService {

    Map<String,String> getLanguages();

    void saveNewSupportLangs(Map<String, String> supportedLangs);
}
