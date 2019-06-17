package singerstone.com.superapp.keeplive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import singerstone.com.superapp.utils.L;

public class LiveService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //屏幕关闭的时候启动一个1像素的Activity，开屏的时候关闭Activity
        final AliveActivityManager aliveActivityManager = AliveActivityManager.getInstance(LiveService.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this,new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                L.e("LiveService onScreenOn "+LiveService.this);
                aliveActivityManager.finishActivity();
            }
            @Override
            public void onScreenOff() {
                L.e("LiveService onScreenOff "+LiveService.this);
                aliveActivityManager.startActivity();
            }
        });
        listener.startListen();
        return START_REDELIVER_INTENT;
    }
}
