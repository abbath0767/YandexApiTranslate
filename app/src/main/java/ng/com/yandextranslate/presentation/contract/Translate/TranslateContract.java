package ng.com.yandextranslate.presentation.contract.translate;

/**
 * Created by NGusarov on 17/03/17.
 */

public interface TranslateContract {

    interface View {
        void showTranslateResult(String message);
    }

    interface Presenter {
        void saveToHistory();
        void getTranslate(String message);
    }
}
