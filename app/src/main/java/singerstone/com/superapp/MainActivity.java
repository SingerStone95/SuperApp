package singerstone.com.superapp;

import android.annotation.TargetApi;
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
import android.view.View;

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
        new View(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 3000);
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
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
        L.e("MainActivity onNewIntent!");
        super.onNewIntent(intent);
    }
}
