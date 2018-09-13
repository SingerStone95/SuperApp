package singerstone.com.superapp.qqlive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import singerstone.com.superapp.utils.L;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        L.i("onReceive: ");
    }
}