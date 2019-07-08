package singerstone.com.superapp.TouchEvent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import singerstone.com.superapp.Dialog.DialogItemAdapter;
import singerstone.com.superapp.utils.L;

public class Test {
    void test(int in, long j, Object o) {
        ITest iTest = view1 -> view1.setVisibility(View.GONE);
        View.OnClickListener listener = v -> {
            L.i(in + "" + j + o);

        };
        Dialog.OnClickListener listener1 = (dialogInterface, i) -> {
            L.i(in + "" + j + o);
        };
        DialogItemAdapter.OnItemClickListener onItemClickListener = (position, item) -> {

        };
        AdapterView.OnItemClickListener listener2 = (parent, view12, position, id) -> {

        };
        RadioGroup.OnCheckedChangeListener listener3 = (group, checkedId) -> {

        };
        CompoundButton.OnCheckedChangeListener listener4 = (buttonView, isChecked) -> {

        };

    }

    private static void test2(View view) {
        String a;
        view.setVisibility(View.GONE);
    }
}