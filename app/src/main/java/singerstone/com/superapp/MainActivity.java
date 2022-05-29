package singerstone.com.superapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.keeplive.LiveService;
import singerstone.com.superapp.utils.L;

public class MainActivity extends FragmentActivity implements ILife {


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getData() != null) {
            String myCustomUri = getIntent().getStringExtra(CalendarContract.EXTRA_CUSTOM_APP_URI);
            L.e("MainActivity onCreate!   " + myCustomUri + "  " + getIntent().getDataString());
        }
       /* getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }*/
        setContentView(R.layout.activity_main);
        setFragment(MainFragmentKt.newInstance());
        startService(new Intent(MainActivity.this, LiveService.class));
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("one");
        arrayList.add("two");

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setFragmentAddToBackStack(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
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
        FragmentManager fm = getSupportFragmentManager();
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
        L.e("MainActivity onNewIntent!，extra:" + intent.getStringExtra("test"));
        super.onNewIntent(intent);
    }

    public static void addShortcut(Activity cx, String name) {
        try {
            Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
            // 快捷图标是允许重复
            shortcut.putExtra("duplicate", false);
            // 快捷图标
            Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(cx, R.mipmap.ic_launcher);
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
            Intent carryIntent = new Intent(Intent.ACTION_MAIN);
            carryIntent.putExtra("name", name);
            carryIntent.setClassName(cx.getPackageName(), cx.getClass().getName());
            carryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //添加携带的Intent
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, carryIntent);
            cx.sendBroadcast(shortcut);
            L.i("add shortcut success!");
        } catch (Exception e) {
            L.e("add shortcut failed!");
        }
    }
}
