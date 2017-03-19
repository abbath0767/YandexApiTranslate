package ng.com.yandextranslate.presentation.contract.translate;

import ng.com.yandextranslate.model.pojo.LanguagePair;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract {

    interface View {
        void showTranslateResult(String message);
        void setDefaultLanguages(LanguagePair languagePair);
    }

    interface Presenter {
        void saveToHistory();
        void getTranslate(String message);
    }
}
