package ng.com.yandextranslate.presentation.implementation.translate;

import javax.inject.Inject;

import ng.com.yandextranslate.controller.data.RepositoryService;
import ng.com.yandextranslate.controller.network.YandexTranslateApi;
import ng.com.yandextranslate.presentation.contract.translate.TranslateContract;

/**
 * Created by NGusarov on 17/03/17.
 */

public class TranslatePresenterImpl implements TranslateContract.Presenter {

    TranslateContract.View mView;

    YandexTranslateApi mYandexTranslateApi;
    RepositoryService mRepositoryService;

    @Inject
    public TranslatePresenterImpl(YandexTranslateApi api,
                                  RepositoryService repositoryService,
                                  TranslateContract.View view) {
        this.mView = view;
        this.mYandexTranslateApi = api;
        this.mRepositoryService = repositoryService;
    }

    @Override
    public void saveToHistory() {

    }

    @Override
    public void getTranslate(String message) {

    }
}
