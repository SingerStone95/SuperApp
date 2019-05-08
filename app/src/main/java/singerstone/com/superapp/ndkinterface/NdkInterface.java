package singerstone.com.superapp.ndkinterface;

public class NdkInterface {
    static {
        System.loadLibrary("NdkInterface");
    }


    public static native String getServiceName();

    public static native String genCrash();
}
