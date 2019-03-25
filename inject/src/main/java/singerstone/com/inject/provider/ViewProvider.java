package singerstone.com.inject.provider;

import android.view.View;

/**
 * Created by JokAr on 16/8/6.
 */
public class ViewProvider implements Provider {
    @Override
    public View findView(Object object, int id) {
        return ((View) object).findViewById(id);
    }
}
