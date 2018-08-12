package singerstone.com.superapp.keeplive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

import singerstone.com.superapp.utils.L;

public class AliveActivityManager {

    private Context mContext;//applicationContex

    private WeakReference<Activity> mActivityWref;
    private Activity activity;

    public static AliveActivityManager gDefualt;

    public static AliveActivityManager getInstance(Context pContext) {
        if (gDefualt == null) {
            gDefualt = new AliveActivityManager(pContext.getApplicationContext());
        }
        return gDefualt;
    }
    private AliveActivityManager(Context pContext) {
        this.mContext = pContext;
    }

    public void setActivity(Activity pActivity) {
        //mActivityWref = new WeakReference<Activity>(pActivity);
        activity=pActivity;
    }

    public void startActivity() {
        Intent intent = new Intent(mContext, KeepLiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }

    public void finishActivity() {
        //结束掉LiveActivity
      /*  if (mActivityWref != null) {
            Activity activity = mActivityWref.get();
            if (activity != null) {
                activity.finish();
            }
        }*/
      if (activity!=null) {
          activity.finish();
      }else {
          L.e("Activity is null.");
      }
    }
}