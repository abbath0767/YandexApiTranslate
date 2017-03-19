package ng.com.yandextranslate.controller.data;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NGusarov on 17/03/17.
 */

public class RepositoryServiceImplDumb implements RepositoryService {

    private Map<String, String> supportedLangs;

    public RepositoryServiceImplDumb(Context context) {
        Log.d("TAG", "dumb repository constructor! context null? " + (context == null));
        supportedLangs = new HashMap<>();
    }

    @Override
    public Map<String, String> getLanguages() {
        return supportedLangs;
    }

    @Override
    public void saveNewSupportLangs(Map<String, String> supportedLangs) {
        this.supportedLangs = supportedLangs;
    }
}
