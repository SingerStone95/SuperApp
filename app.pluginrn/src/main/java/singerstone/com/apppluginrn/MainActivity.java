package singerstone.com.apppluginrn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import java.io.File;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReactRootView = new ReactRootView(this);
        String bundleFileName = getBundleFileNameFromSDcard();
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                //.setJSBundleFile(getBundleFileNameFromSDcard())
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new AnExampleReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        // 注意这里的moduleName必须对应“index.android.js”中的
        // “AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "rnapp", null);

        setContentView(mReactRootView);
        addShortcut(this,"hello");
    }

    private String getBundleFileNameFromSDcard() {
        String result = "";
        result = getSDPath() + File.separator + "index.android.bundle";
        File file = new File(result);
        Log.e("singerstone", "file exsit:" + file.exists());
        return result;
    }

    private String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public static void addShortcut(Activity cx, String name) {
// TODO: 2017/6/25 创建快捷方式的intent广播
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
// TODO: 2017/6/25 添加快捷名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
// 快捷图标是允许重复
        shortcut.putExtra("duplicate", false);
// 快捷图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, R.mipmap.ic_launcher);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
// TODO: 2017/6/25 我们下次启动要用的Intent信息
        Intent carryIntent = new Intent(Intent.ACTION_MAIN);
        carryIntent.putExtra("name", name);
        carryIntent.setClassName(cx.getPackageName(), cx.getClass().getName());
        carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//添加携带的Intent
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
// TODO: 2017/6/25 发送广播
        cx.sendBroadcast(shortcut);
    }

}
