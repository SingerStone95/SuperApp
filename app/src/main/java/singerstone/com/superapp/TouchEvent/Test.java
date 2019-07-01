package singerstone.com.superapp.TouchEvent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import singerstone.com.superapp.Dialog.DialogItemAdapter;

public class Test {
    void test(View view) {
        ITest iTest = view1 -> view1.setVisibility(View.GONE);
        View.OnClickListener listener = v -> {

        };
        Dialog.OnClickListener listener1 = (dialogInterface, i) -> {

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
        view.setVisibility(View.GONE);
    }
}