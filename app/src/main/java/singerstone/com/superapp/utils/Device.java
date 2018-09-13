package singerstone.com.superapp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class Device {

    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versionCode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (versionName == null || versionName.length() <= 0) {
            versionName = "";
        }

        return versionName;
    }

    public static int getVersionName(Context context) {
        int currentapiVersion=android.os.Build.VERSION.SDK_INT;
        return currentapiVersion;
    }

}
