package singerstone.com.superapp.log;

import java.io.OutputStream;

/**
 * author : yogachen
 * date   : 2020-04-21
 * desc   :
 */
public class AppLog {
    private static IlogProxy sLogProxy;

    public static void setLogProxy(IlogProxy proxy) {
        sLogProxy = proxy;
    }

    public static void i(String tag, String msg) {
        if (sLogProxy != null) {
            sLogProxy.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sLogProxy != null) {
            sLogProxy.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLogProxy != null) {
            sLogProxy.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sLogProxy != null) {
            sLogProxy.e(tag, msg);
        }
    }

    public static void flush(boolean isSync) {
        if (sLogProxy != null) {
            sLogProxy.flush(isSync);
        }
    }

    public static void quit() {
        if (sLogProxy != null) {
            sLogProxy.quit();
        }
    }

    public static void packLog(OutputStream outputStream) {
        if (sLogProxy != null) {
            sLogProxy.packLog(outputStream);
        }
    }
}
