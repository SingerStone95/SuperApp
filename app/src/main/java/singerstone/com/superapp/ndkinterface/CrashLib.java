package singerstone.com.superapp.ndkinterface;

/**
 * author : yogachen
 * date   : 2019-05-08
 * desc   :
 */
public class CrashLib {
    static {
        System.loadLibrary("Breakpad");
    }

    public static native void breakpadInit(String reportPath);
}
