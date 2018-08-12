package singerstone.com.superapp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import net.wequick.small.Small;


/**
 * Created by chenbinhao on 2017/9/18.
 * YY:909075276
 */

public class SuperApp extends Application {

    RefWatcher refWatcher;

    public SuperApp() {
        Small.preSetUp(this);
        Small.setLoadFromAssets(BuildConfig.LOAD_FROM_ASSETS);
    }

    public static RefWatcher getRefWatcher(Context context) {
        SuperApp application = (SuperApp) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }
}
