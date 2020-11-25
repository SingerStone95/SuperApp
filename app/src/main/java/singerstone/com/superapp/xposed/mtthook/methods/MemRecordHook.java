package singerstone.com.superapp.xposed.mtthook.methods;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;

public class MemRecordHook extends XC_MethodHook {

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        XposedBridge.log("yogachen:" + param.args[0] + " " + param.args[1]);
    }
}
