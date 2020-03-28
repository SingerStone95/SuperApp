package singerstone.com.superapp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


import java.io.File;
import java.io.IOException;

import singerstone.com.superapp.ndkinterface.CrashLib;
import singerstone.com.superapp.utils.L;


/**
 * Created by chenbinhao on 2017/9/18.
 * YY:909075276
 */

public class SuperApp extends Application {

    RefWatcher refWatcher;

    public static Application application;

    public SuperApp() {

    }

    public static RefWatcher getRefWatcher(Context context) {
        SuperApp application = (SuperApp) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        String path = base.getExternalFilesDir("").getAbsolutePath();
        L.e("crash init path:" + path);
        CrashLib.breakpadInit(path);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("SuperApp:onCreate");
        application = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }
}
