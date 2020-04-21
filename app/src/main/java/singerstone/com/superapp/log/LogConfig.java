package singerstone.com.superapp.log;


public class LogConfig {

    private volatile static String sCacheLogFolder;
    private volatile static String sLogFolder;

    public static void setLogFolder(String logFolder) {
        sLogFolder = logFolder;
    }

    public static void setLogCacheFolder(String logCacheFolder) {
        sCacheLogFolder = logCacheFolder;
    }

    public static String getLogFolder() {
        return sLogFolder;
    }

    public static String getCacheLogFolder() {
        return sCacheLogFolder;
    }
}
