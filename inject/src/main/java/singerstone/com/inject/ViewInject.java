package singerstone.com.inject;

import android.annotation.TargetApi;
import android.app.Activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;


import singerstone.com.inject.provider.ActivityProvider;
import singerstone.com.inject.provider.FragmentProvider;
import singerstone.com.inject.provider.Provider;
import singerstone.com.inject.provider.ViewProvider;


/**
 * Created by JokAr on 16/8/6.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class ViewInject {
    //两个工具类
    private static final ActivityProvider activityProvider = new ActivityProvider();
    private static final ViewProvider viewProvider = new ViewProvider();
    private static final FragmentProvider fragmentProvider = new FragmentProvider();

    private static final ArrayMap<String, Inject> injectMap = new ArrayMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, activityProvider);
    }

    public static void inject(Fragment fragment, View rootView) {
        inject(fragment, rootView, fragmentProvider);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    private static void inject(Object host, View view) {
        inject(host, view, viewProvider);
    }

    //host.xxx=object.findviewbyid(xxx)
    private static void inject(Object host, Object object, Provider provider) {
        String className = host.getClass().getName();
        Log.i("TAG", className);
        try {
            Inject inject = injectMap.get(className);
            if (inject == null) {
                Class<?> aClass = Class.forName(className + "$$ViewInject");
                inject = (Inject) aClass.newInstance();
                injectMap.put(className, inject);
            }
            Log.i("TAG", host + "  " + object + "  " + provider);
            inject.inject(host, object, provider);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
