package singerstone.com.superapp.xposed.mtthook;

import de.robv.android.xposed.XposedBridge;
import java.util.ArrayList;
import java.util.List;
import singerstone.com.superapp.xposed.hook.IMethodHook;
import singerstone.com.superapp.xposed.hook.MethodHook;
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

        initMethodHooks(classLoader);

    }

    //com.tencent.matrix.memorycanary.MemoryRecord.recordVmSizeDetail
    private void initMethodHooks(ClassLoader classLoader) {
        MethodHook methodHook = new MethodHook(classLoader,
                "com.tencent.matrix.memorycanary.MemoryRecord", "recordVmSizeDetail", boolean.class,
                String.class, String.class
                , new MemRecordHook());
        methodHooks.add(methodHook);

    }

}
