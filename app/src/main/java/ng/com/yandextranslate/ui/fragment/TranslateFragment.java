package ng.com.yandextranslate.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by NG on 15.03.17.
 */

public class TranslateFragment extends Fragment {

    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new TranslateFragment();
        return fragment;
    }
}
