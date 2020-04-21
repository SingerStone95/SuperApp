package singerstone.com.superapp.log;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;

/**
 * author : yogachen
 * date   : 2020-04-21
 * desc   :
 */
public class ProcessUtil {
    public static String getProcessName(Context context) {
        String processName = getProcessNameByService(context);
        if (processName.length() == 0) {
            BufferedReader mBufferedReader = null;

            try {
                File file = new File("/proc/" + Process.myPid() + "/cmdline");
                mBufferedReader = new BufferedReader(new FileReader(file));
                String line = mBufferedReader.readLine();
                if (!TextUtils.isEmpty(line)) {
                    processName = line.trim();
                }
            } catch (Throwable var13) {
                var13.printStackTrace();
            } finally {
                if (mBufferedReader != null) {
                    try {
                        mBufferedReader.close();
                    } catch (Exception var12) {
                    }
                }

            }
        }

        return processName;
    }

    private static String getProcessNameByService(Context context) {
        String processName = "";
        if (context != null) {
            int pid = Process.myPid();
            ActivityManager mActivityManager = (ActivityManager) context.getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> procList = mActivityManager.getRunningAppProcesses();
            if (procList != null) {
                Iterator var5 = procList.iterator();

                while (var5.hasNext()) {
                    ActivityManager.RunningAppProcessInfo appProcess = (ActivityManager.RunningAppProcessInfo) var5.next();
                    if (appProcess.pid == pid) {
                        if (appProcess.processName != null) {
                            processName = appProcess.processName;
                        }
                        break;
                    }
                }
            }
        }

        return processName;
    }
}
