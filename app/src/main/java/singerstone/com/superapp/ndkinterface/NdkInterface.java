package singerstone.com.superapp.ndkinterface;

public class NdkInterface {
    static {
        System.loadLibrary("superndk");   //defaultConfig.ndk.moduleName
    }
    public static native String getServiceName();
}
