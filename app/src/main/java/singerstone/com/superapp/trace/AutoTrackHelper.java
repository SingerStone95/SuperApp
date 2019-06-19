package singerstone.com.superapp.trace;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import singerstone.com.superapp.utils.L;

/**
 * Author:xishuang
 * Date:2018-12-06
 * Des:全埋点的入口类
 */
public class AutoTrackHelper {


    public static void onFragmentViewCreated(Object object, View rootView, Bundle bundle) {
        L.i("yogachen", "onFragmentViewCreated has been hooked!");
    }


    public static void trackFragmentResume(Object object) {
        L.i("yogachen", "trackFragmentResume has been hooked!");
    }


    public static void trackFragmentDestroy(Object object) {
        L.i("yogachen", "trackFragmentDestroy has been hooked!");

    }

    public static void trackDialog(DialogInterface dialogInterface, int whichButton) {
        L.i("yogachen", "trackDialog has been hooked!");
    }


    public static void trackListView(AdapterView adapterView, View view) {
        L.i("yogachen", "trackListView has been hooked!");
    }

    public static void trackViewOnClick(View view) {
        L.i("yogachen", "trackViewOnClick has been hooked!");
    }


}
