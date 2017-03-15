package ng.com.yandextranslate.util;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by NG on 15.03.17.
 */

//todo mb implement base activity from OnItemClickListener and delete this class?
public class DrawerItemClickListener implements AdapterView.OnItemClickListener {
    private OnDrawerItemClickListener listener;

    public DrawerItemClickListener(OnDrawerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        listener.onDrawerItemClick(position);
    }

    public interface OnDrawerItemClickListener {
        void onDrawerItemClick(int position);
    }
}
