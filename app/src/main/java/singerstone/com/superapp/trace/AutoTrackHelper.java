package singerstone.com.superapp.trace;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import singerstone.com.superapp.utils.L;

/**
 * Author:xishuang
 * Date:2018-12-06
 * Des:全埋点的入口类
 */
public class AutoTrackHelper {

    private static SparseArray<Long> eventTimestamp = new SparseArray<>();

    /**
     * 防抖动，超过一定时间才算数
     */
    private static boolean isDeBounceTrack(Object object) {

        boolean isDeBounceTrack = false;
        long currentOnClickTimestamp = System.currentTimeMillis();
        Object targetObject = eventTimestamp.get(object.hashCode());
        if (targetObject != null) {
            long lastOnClickTimestamp = (long) targetObject;
            if ((currentOnClickTimestamp - lastOnClickTimestamp) < 500) {
                isDeBounceTrack = true;
            }
        }

        eventTimestamp.put(object.hashCode(), currentOnClickTimestamp);
        return isDeBounceTrack;
    }

    /**
     * 防抖动，超过一定时间才算数
     */
    private static boolean isDeBounceTrackForView(View view) {

        boolean isDeBounceTrack = false;


        return isDeBounceTrack;
    }


    public static void onFragmentViewCreated(Object object, View rootView, Bundle bundle) {
        L.i("yogachen", "onFragmentViewCreated has been hooked!");

    }


    public static void trackFragmentResume(Object object) {


    }


    public static void trackFragmentDestroy(Object object) {


    }


    public static void trackFragmentSetUserVisibleHint(Object object, boolean isVisibleToUser) {

    }


    public static void trackOnHiddenChanged(Object object, boolean hidden) {


    }


    public static void trackViewPageSelected(Object object, int position) {

    }


    /**
     * Fragment日志处理
     */
    private static void trackFragmentAppViewScreen(Fragment fragment) {

    }

    /**
     * 对应实现接口的埋点方法{@link ExpandableListView.OnGroupClickListener#onGroupClick(ExpandableListView, View, int, long)}
     *
     * @param expandableListView 参数对应ExpandableListView
     * @param view               参数对应View
     * @param groupPosition      参数对应int
     */
    public static void trackExpandableListViewOnGroupClick(ExpandableListView expandableListView, View view,
                                                           int groupPosition) {

    }


    /**
     * 对应实现接口的埋点方法{@link ExpandableListView.OnChildClickListener#onChildClick(ExpandableListView, View, int, int, long)}
     *
     * @param expandableListView 参数对应ExpandableListView
     * @param view               参数对应View
     * @param groupPosition      参数对应groupPosition
     * @param childPosition      参数对应childPosition
     */
    public static void trackExpandableListViewOnChildClick(ExpandableListView expandableListView, View view,
                                                           int groupPosition, int childPosition) {


    }

    /**
     * 对应实现接口的埋点方法{@link AdapterView.OnItemSelectedListener#onItemSelected(AdapterView, View, int, long)}
     * 和{@link AdapterView.OnItemClickListener#onItemClick(AdapterView, View, int, long)}
     *
     * @param adapterView 参数对应AdapterView
     * @param view        参数对应View
     * @param position    参数对应int
     */
    public static void trackListView(AdapterView<?> adapterView, View view, int position) {

    }


    public static void trackTabLayoutSelected(Object object, Object tab) {

    }


    public static void trackMenuItem(Object object, MenuItem menuItem) {

    }


    public static void trackRadioGroup(RadioGroup view, int checkedId) {

    }


    /**
     * 对应实现接口的埋点方法{@link DialogInterface.OnClickListener#onClick(DialogInterface, int)}
     *
     * @param dialogInterface DialogInterface对象
     * @param whichButton     参数对应int
     */
    public static void trackDialog(DialogInterface dialogInterface, int whichButton) {

    }

    /**
     * 监听 void onDrawerOpened(View)方法
     *
     * @param view 方法中的view参数
     */
    public static void trackDrawerOpened(View view) {

    }

    /**
     * 监听 void onDrawerClosed(View)方法
     *
     * @param view 方法中的view参数
     */
    public static void trackDrawerClosed(View view) {

    }

    public static void trackViewOnClick(View view) {

    }

    public static void trackViewOnClick(Object anything) {
        try {
            System.out.println("测试:trackViewOnClick");
            if (anything == null) {
                return;
            }

            if (!(anything instanceof View)) {
                return;
            }

            trackViewOnClick((View) anything);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
