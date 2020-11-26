package singerstone.com.superapp.xposed.mtthook.methods;

import de.robv.android.xposed.XC_MethodHook;
import singerstone.com.superapp.xposed.hook.Utils;

public class KillProcessHook extends XC_MethodHook {

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Utils.printCurrentStackTrace();
    }
}
