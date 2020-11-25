package singerstone.com.superapp.xposed.hook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public interface BaseHook {

    void onLoadPackage(XC_LoadPackage.LoadPackageParam lpparam);

    String getHookPacketName();

}
