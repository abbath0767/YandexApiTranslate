package com.ng.yandextranslate.presentation.implementation.translate;

import dagger.Component;
import com.ng.yandextranslate.AppComponent;
import com.ng.yandextranslate.ui.fragment.traslate.TranslateFragment;

/**
 * Created by NGusarov on 17/03/17.
 */

@TranslateScope
@Component(dependencies = AppComponent.class,
            modules = {TranslateModule.class})
public interface TranslateComponent {
    void inject(TranslateFragment fragment);
}
