package singerstone.com.superapp.ndkinterface;

public class NdkInterface {
    static {
        System.loadLibrary("NdkInterface");
    }


    public static native String getServiceName();

    public static native String genCrash();

    public static native String genMallocOOM();

    public static native String genMmapOOM();

}
