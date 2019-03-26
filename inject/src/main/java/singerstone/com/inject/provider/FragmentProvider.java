package singerstone.com.inject.provider;

import android.view.View;

/**
 * Created by 44519 on 2018/4/23.
 */

public class FragmentProvider implements Provider {
    @Override
    public View findView(Object object, int id) {
        return ((View) object).findViewById(id);
    }
}
