package singerstone.com.superapp;

import android.app.Activity;

public class Test {

    public static void doLeak(Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (activity != null) {
                    activity.finish();
                }

            }
        }).start();
    }
}
