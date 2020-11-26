package singerstone.com.superapp.xposed.mtthook;

import java.util.ArrayList;
import java.util.List;
import singerstone.com.superapp.xposed.hook.IMethodHook;
import singerstone.com.superapp.xposed.hook.MethodHook;
import singerstone.com.superapp.xposed.mtthook.methods.KillProcessHook;
import singerstone.com.superapp.xposed.mtthook.methods.MemRecordHook;

public class MttHookSet implements IMethodHook {

    @Override
    public void hookMethod() {
        for (IMethodHook hook : methodHooks) {
            hook.hookMethod();
        }
    }

    private List<IMethodHook> methodHooks = new ArrayList<>();

    public MttHookSet(ClassLoader classLoader) {
       // registerMemRecordMethodHooks(classLoader);
        registerKillProcessMethodHooks(classLoader);

    }

    //android.os.Process.killProcess(pid)
    private void registerKillProcessMethodHooks(ClassLoader classLoader) {
        MethodHook methodHook = new MethodHook(classLoader,
                "android.os.Process", "killProcess", int.class, new KillProcessHook());
        methodHooks.add(methodHook);
    }

    //com.tencent.matrix.memorycanary.MemoryRecord.recordVmSizeDetail
    private void registerMemRecordMethodHooks(ClassLoader classLoader) {
        MethodHook methodHook = new MethodHook(classLoader,
                "com.tencent.matrix.memorycanary.MemoryRecord", "recordVmSizeDetail", boolean.class,
                String.class, String.class
                , new MemRecordHook());
        methodHooks.add(methodHook);

    }

}
