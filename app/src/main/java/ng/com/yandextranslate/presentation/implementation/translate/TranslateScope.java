package ng.com.yandextranslate.presentation.implementation.translate;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by NGusarov on 17/03/17.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslateScope {
}
