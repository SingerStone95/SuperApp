package singerstone.com.superapp.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import java.util.ArrayList;
import java.util.List;
import singerstone.com.superapp.xposed.hook.BaseHook;
import singerstone.com.superapp.xposed.mtthook.MttHook;

public class HookEntry implements IXposedHookLoadPackage {

    private List<BaseHook> sHookAppList;

    {
        sHookAppList = new ArrayList<>();
        sHookAppList.add(new MttHook());

    }

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);
        for (BaseHook hook : sHookAppList) {
            hook.onLoadPackage(lpparam);
        }
    }
}
