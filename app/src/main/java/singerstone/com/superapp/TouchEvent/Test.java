package singerstone.com.superapp.TouchEvent;

import android.view.View;

public class Test {
    void test(View view) {
        view.setOnClickListener(v -> {
            v.setVisibility(View.GONE);
        });
        view.setOnClickListener(v -> v.setVisibility(View.GONE));
        ITest iTest = view1 -> view1.setVisibility(View.GONE);
        iTest.onclick(null);
    }

    private static void test2(View view) {
        view.setVisibility(View.GONE);
    }
}