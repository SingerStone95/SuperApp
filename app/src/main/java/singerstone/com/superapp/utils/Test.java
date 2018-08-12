package singerstone.com.superapp.utils;

import android.content.Context;

import org.w3c.dom.Text;

/**
 * Created by SingerStone on 2017.10.11.
 */

public class Test {
    Context context;
    private volatile static Test instance;

    private Test(Context context) {
        this.context = context;
    }

    public static Test getInstance(Context context) {
        if (instance == null) {
            synchronized (Test.class) {
                if (instance == null) {
                    instance = new Test(context);
                }
            }
        }
        return instance;
    }
}
