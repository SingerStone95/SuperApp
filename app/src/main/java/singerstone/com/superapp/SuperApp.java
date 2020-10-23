package singerstone.com.superapp;

import android.app.Application;
import android.content.Context;

import singerstone.com.superapp.ndkinterface.CrashLib;
import singerstone.com.superapp.utils.L;


/**
 * Created by chenbinhao on 2017/9/18. YY:909075276
 */

public class SuperApp extends Application {


    public static Application application;

    public SuperApp() {

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        String path = base.getExternalFilesDir("").getAbsolutePath();
        L.e("crash init path:" + path);
        CrashLib.breakpadInit(path);

        LogModule.INSTANCE.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("SuperApp:onCreate");
        application = this;
    }
}
