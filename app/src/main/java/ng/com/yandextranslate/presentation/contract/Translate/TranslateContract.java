package ng.com.yandextranslate.presentation.contract.translate;

import java.util.Map;

import ng.com.yandextranslate.model.pojo.LanguagePair;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract {

    interface View {
        void showTranslateResult(String message);
        void setDefaultLanguages(LanguagePair languagePair);

        void setLanguages(Map<String, String> supportedLangs);
    }

    interface Presenter {
        void saveToHistory();
        void getTranslate(String message);
    }
}
