package singerstone.com.superapp;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import singerstone.com.superapp.log.LogModule;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tryHackActivityThreadH();
            }
        }, 5000);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("SuperApp:onCreate");
        application = this;
    }

    public static void tryHackActivityThreadH() {
        try {
            //只在Android5-7 做这个Hook
            // if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1)) {
            Class cActivityThread = Class.forName("android.app.ActivityThread");
            Method method = cActivityThread.getDeclaredMethod("currentActivityThread");
            Object activityThread = method.invoke(null);
            if (activityThread != null) {
                Field fieldHander = cActivityThread.getDeclaredField("mH");
                fieldHander.setAccessible(true);
                Handler handler = (Handler) fieldHander.get(activityThread);
                if (handler != null) {
                    Field fieldCallback = Handler.class.getDeclaredField("mCallback");
                    fieldCallback.setAccessible(true);
                    ActivityThreadHCallBack activityThreadHCallBack = new ActivityThreadHCallBack();
                    fieldCallback.set(handler, activityThreadHCallBack);
                }
            }
            // }
        } catch (Throwable t) {
            t.printStackTrace();
            // ignore
        }
    }

    public static class ActivityThreadHCallBack implements Handler.Callback {
        private static final int SERVICE_ARGS = 115;
        private static final int STOP_SERVICE = 116;
        private static final int SLEEPING = 137;
        private static final int STOP_ACTIVITY_SHOW = 103;
        private static final int STOP_ACTIVITY_HIDE = 104;


        public boolean handleMessage(Message msg) {
            final int what = msg.what;
            switch (what) {
                case SERVICE_ARGS:
                    SpBlockHelper.beforeSPBlock("SERVICE_ARGS");
                    break;
                case STOP_SERVICE:
                    SpBlockHelper.beforeSPBlock("STOP_SERVICE");
                    break;
                case SLEEPING:
                    SpBlockHelper.beforeSPBlock("SLEEPING");
                    break;
                case STOP_ACTIVITY_SHOW:
                case STOP_ACTIVITY_HIDE:
                    SpBlockHelper.beforeSPBlock("STOP_ACTIVITY");
                    break;
            }
            return false;
        }
    }

    public static class SpBlockHelper {
        static boolean init = false;
        static String CLASS_QUEUED_WORK = "android.app.QueuedWork";
        static String FIELD_PENDING_FINISHERS = "sPendingWorkFinishers";
        static ConcurrentLinkedQueue<Runnable> sPendingWorkFinishers = null;

        public static void beforeSPBlock(String tag) {
            if (!init) {
                getPendingWorkFinishers();
                init = true;
            }
            if (sPendingWorkFinishers != null) {
                sPendingWorkFinishers.clear();
            }
        }

        static void getPendingWorkFinishers() {
            try {
                Class clazz = Class.forName(CLASS_QUEUED_WORK);
                Field field = clazz.getDeclaredField(FIELD_PENDING_FINISHERS);
                if (field != null) {
                    field.setAccessible(true);
                    sPendingWorkFinishers = (ConcurrentLinkedQueue<Runnable>) field.get(null);
                }
            } catch (Exception e) {
                //ignore
                e.printStackTrace();
            }
        }
    }
}
