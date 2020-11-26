package singerstone.com.superapp.xposed.hook;

import de.robv.android.xposed.XposedBridge;

public class Utils {

    public static final void printCurrentStackTrace() {
        Throwable throwable = new Throwable();
        throwable.printStackTrace();
    }
}
