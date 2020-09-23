package singerstone.com.superapp.log;

import android.app.Application;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;

import com.tencent.mars.xlog.Xlog;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import singerstone.com.superapp.BuildConfig;


public class LogModule {
    public static final LogModule INSTANCE = new LogModule();
    final String PUB_KEY = "572d1e2710ae5fbca54c76a382fdd44050b3a675cb2bf39feebe85ef63d947aff0fa4943f1112e8b6af34bebebbaefa1a0aae055d9259b89a1858f7cc9af9df1";
    final String PREFIX = "SuperApp";
    private int mProcessId;
    private long mMainTid;
    private String PROCCESS_SUFFIX;
    Xlog xlog = new Xlog();
    public static final long DEFAULT_MAX_LOG_PACKAGE_SIZE = 4 * 1024 * 1024;

    public void init(Application application) {
        loadLib();
        initXlogConfig(application);
        AppLog.setLogProxy(new IlogProxy() {
            @Override
            public void d(String tag, String msg) {
                xlog.logD(tag, "un", "un", -1, mProcessId, Thread.currentThread().getId(), mMainTid, msg);
            }

            @Override
            public void i(String tag, String msg) {
                xlog.logI(tag, "un", "un", -1, mProcessId, Thread.currentThread().getId(), mMainTid, msg);
            }

            @Override
            public void w(String tag, String msg) {
                xlog.logW(tag, "un", "un", -1, mProcessId, Thread.currentThread().getId(), mMainTid, msg);
            }

            @Override
            public void e(String tag, String msg) {
                xlog.logE(tag, "un", "un", -1, mProcessId, Thread.currentThread().getId(), mMainTid, msg);
            }

            @Override
            public void packLog(OutputStream outputStream) {
                List<File> fileList = LogUtil.selectLatestLogFile(LogConfig.getLogFolder());
                try {
                    LogUtil.writeLogPackage(outputStream, true, fileList, DEFAULT_MAX_LOG_PACKAGE_SIZE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void flush(boolean isSync) {
                xlog.appenderFlush(isSync);
            }

            @Override
            public void quit() {
                xlog.appenderClose();
            }
        });
    }

    private void initXlogConfig(Application application) {
        LogConfig.setLogCacheFolder(application.getFilesDir() + "/logs/cache");
        LogConfig.setLogFolder(application.getFilesDir() + "/logs/logs");
        mProcessId = Process.myPid();
        mMainTid = Looper.getMainLooper().getThread().getId();
        String processName = ProcessUtil.getProcessName(application);
        if (TextUtils.isEmpty(processName)) {
            PROCCESS_SUFFIX = "PID" + mProcessId;
        } else {
            String[] processNameStrs = processName.split(":");
            if (processNameStrs.length >= 2) {
                PROCCESS_SUFFIX = processNameStrs[processNameStrs.length - 1];
            } else {
                PROCCESS_SUFFIX = "";
            }
        }
        try {
            Xlog.setMaxFileSize(DEFAULT_MAX_LOG_PACKAGE_SIZE);//单个日志的最大size
            if (BuildConfig.DEBUG) {
                Xlog.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, LogConfig.getCacheLogFolder(), LogConfig.getLogFolder(), PREFIX + (PROCCESS_SUFFIX.length() > 0 ? "_" : "") + PROCCESS_SUFFIX, 0, PUB_KEY);
                Xlog.setConsoleLogOpen(true);
            } else {
                Xlog.appenderOpen(Xlog.LEVEL_INFO, Xlog.AppednerModeAsync, LogConfig.getCacheLogFolder(), LogConfig.getLogFolder(), PREFIX + (PROCCESS_SUFFIX.length() > 0 ? "_" : "") + PROCCESS_SUFFIX, 0, PUB_KEY);
                Xlog.setConsoleLogOpen(false);
            }
        } catch (Throwable throwable) {
        }
    }

    private void loadLib() {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
    }


}
