package singerstone.com.superapp;

import android.app.Activity;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Test {

    @RequiresApi(api = VERSION_CODES.N)
    public static void doLeak(Activity activity) {
        ArrayList<Integer> list=new ArrayList();
        list.removeIf(new Predicate<Number>() {
            @Override
            public boolean test(Number integer) {
                if(integer.longValue()==0)
                return false;
            }
        });

    }



}
