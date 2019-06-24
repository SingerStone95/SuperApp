package singerstone.com.superapp.TouchEvent;

import android.view.View;

public class Test {
    void test(View view) {
        view.setOnClickListener(v -> {
            int a = 0;
            a = a + 1;
            v.setVisibility(a);
        });
    }
}
