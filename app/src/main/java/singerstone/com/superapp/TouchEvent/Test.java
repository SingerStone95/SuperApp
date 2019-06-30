package singerstone.com.superapp.TouchEvent;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import singerstone.com.superapp.Dialog.DialogItemAdapter;

public class Test {
    void test(View view) {
        view.setOnClickListener(v -> {
            v.setVisibility(View.GONE);
        });
        view.setOnClickListener(v -> v.setVisibility(View.GONE));
        ITest iTest = view1 -> view1.setVisibility(View.GONE);
        iTest.onclick(null);
        RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };
        Dialog.OnClickListener listener1 = (dialogInterface, i) -> {

        };
        DialogItemAdapter.OnItemClickListener onItemClickListener = (position, item) -> {

        };

    }

    private static void test2(View view) {
        view.setVisibility(View.GONE);
    }
}