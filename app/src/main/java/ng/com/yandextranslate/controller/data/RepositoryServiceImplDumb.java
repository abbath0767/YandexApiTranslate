package ng.com.yandextranslate.controller.data;

import android.content.Context;
import android.util.Log;

/**
 * Created by NGusarov on 17/03/17.
 */

public class RepositoryServiceImplDumb implements RepositoryService {

    public RepositoryServiceImplDumb(Context context) {
        Log.d("TAG", "dumb repository constructor! context null? " + (context == null));
    }
}
