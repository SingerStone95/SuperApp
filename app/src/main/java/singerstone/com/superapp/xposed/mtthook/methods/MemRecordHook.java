package singerstone.com.superapp.xposed.mtthook.methods;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import singerstone.com.superapp.xposed.hook.Constant;
import singerstone.com.superapp.xposed.hook.Utils;

public class MemRecordHook extends XC_MethodHook {

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        XposedBridge.log(Constant.TAG + param.args[0] + " " + param.args[1]);
        Utils.printCurrentStackTrace();
    }
}
