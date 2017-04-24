package com.ng.yandextranslate.presentation.implementation.translate;

import dagger.Component;
import com.ng.yandextranslate.AppComponent;
import com.ng.yandextranslate.controller.data.spreference.SPreferenceModule;
import com.ng.yandextranslate.ui.fragment.traslate.TranslateFragment;
import com.ng.yandextranslate.ui.view.LanguageSelectView;

/**
 * Created by NGusarov on 17/03/17.
 */

@TranslateScope
@Component(dependencies = AppComponent.class,
            modules = {TranslateModule.class, SPreferenceModule.class})
public interface TranslateComponent {
    void inject(TranslateFragment fragment);
    void inject(LanguageSelectView selectView);
}
