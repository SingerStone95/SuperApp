package singerstone.com.superapp.xposed.mtthook;

import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import singerstone.com.superapp.xposed.hook.BaseHook;

/**
 * QQ 浏览器 Hook 入口
 */
public class MttHook implements BaseHook {

    MttHookSet mttHookSet;

    @Override
    public void onLoadPackage(LoadPackageParam lpparam) {
        if (!getHookPacketName().equals(lpparam.packageName)) {
            return;
        }
        if (mttHookSet == null) {
            mttHookSet = new MttHookSet(lpparam.classLoader);
        }
        mttHookSet.hookMethod();
    }

    @Override
    public String getHookPacketName() {
        return "com.tencent.mtt";
    }


}
