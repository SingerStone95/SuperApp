package singerstone.com.superapp.appinfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.List;

import singerstone.com.superapp.utils.L;


public class ApkTool {
    static String TAG = "ApkTool";

    public static void scanLocalInstallAppList(PackageManager packageManager) {
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                L.e(packageInfo.packageName);

            }
        } catch (Exception e) {
            Log.e(TAG, "===============获取应用包信息失败");
        }

    }
}

