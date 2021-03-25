package singerstone.com.superapp.xposed.hook;

public class Utils {

    public static final void printCurrentStackTrace() {
        Throwable throwable = new Throwable();
        throwable.printStackTrace();
    }
}
