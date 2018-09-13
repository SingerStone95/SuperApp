package singerstone.com.superapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import singerstone.com.superapp.keeplive.LiveService;
import singerstone.com.superapp.utils.L;

public class MainActivity extends AppCompatActivity implements ILife {


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e("MainActivity onCreate!"+getIntent().getStringExtra("test"));
        setContentView(R.layout.activity_main);
        setFragment(MainFragment.newInstance());
        startService(new Intent(MainActivity.this, LiveService.class));
        Log.i("info", getApplication().getApplicationInfo().nativeLibraryDir);


        String commonStr = "I am";
        String text1 = "I am a coder";
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(commonStr, text1);
        LinkedList<diff_match_patch.Patch> patches = dmp.patch_make(diffs);
        String patchesStr = dmp.patch_toText(patches);
        patches = (LinkedList<diff_match_patch.Patch>) dmp.patch_fromText(patchesStr);
        Object[] results = dmp.patch_apply(patches, commonStr);
        L.e("results[0]------------>" + results[0]);
        /*new View(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3000);*/
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        //addShortcut(this,"test");
        L.i("current device: "+Rom.isMiui());
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setFragmentAddToBackStack(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    protected void onResume() {
        L.e("MainActivity onResume!");
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setFragment(Fragment fragment) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }

    public static void scanLocalInstallAppList(PackageManager packageManager) {
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);
                L.e(packageInfo.applicationInfo.loadLabel(packageManager).toString());

            }
        } catch (Exception e) {
            L.e("error");
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        L.e("MainActivity onNewIntent!，extra:"+intent.getStringExtra("test"));
        super.onNewIntent(intent);
    }

    public static void addShortcut(Activity cx, String name) {
        try {
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
            L.i("add shortcut success!");
        }catch (Exception e){
            L.e("add shortcut failed!");
        }
    }
}
