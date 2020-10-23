package singerstone.com.superapp.Accessbility;


import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;


import androidx.annotation.RequiresApi;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/31.
 * YY:909075276
 */

public class AutoAccessbility extends AccessibilityService {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED == event.getEventType()) {
            L.e(event.toString());
            //获取根节点
            AccessibilityNodeInfo rootNode = getRootInActiveWindow();
//           for (int i=0;i<rootNode.getChildCount();i++){
//               L.e(rootNode.getChild(i).toString());
//           }

            L.e(event.getClassName().toString());
        /* if (rootNode!=null) {
             final List<AccessibilityNodeInfo> list1 = rootNode.findAccessibilityNodeInfosByText("SEND");
             if (list1!=null) {
                 AccessibilityNodeInfo info = list1.get(0);
                 L.e(info.isEnabled() + "");
                 info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
             }
         }
*/

            /*    //匹配id获取节点
            List<AccessibilityNodeInfo> list2 = rootNode.findAccessibilityNodeInfosByViewId("match_id");
            //获取子节点
            AccessibilityNodeInfo infoNode = rootNode.getChild(index);*/
            // rootNode.performAction()
        }
    }

    @Override
    public void onInterrupt() {

    }
}
