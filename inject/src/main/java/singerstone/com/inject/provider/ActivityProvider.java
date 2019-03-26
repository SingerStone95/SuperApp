package singerstone.com.inject.provider;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by JokAr on 16/8/6.
 */
public class ActivityProvider implements Provider {
    @Override
    public View findView(Object sourse, int id) {
        return ((Activity) sourse).findViewById(id);
    }
}
