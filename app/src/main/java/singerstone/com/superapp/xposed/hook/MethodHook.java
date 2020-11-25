package singerstone.com.superapp.xposed.hook;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import java.lang.reflect.Method;

public class MethodHook implements IMethodHook{

    private XC_MethodHook callback;
    private Method m;

    public MethodHook(ClassLoader cl, String className, String methodName, Object... parameterTypesAndCallback) {
        Class<?> clazz = findClass(className, cl);
        this.callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length - 1];
        this.m = findMethodExact(clazz, methodName, getParameterClasses(clazz.getClassLoader(), parameterTypesAndCallback));

    }



    private static Class<?>[] getParameterClasses(ClassLoader classLoader, Object[] parameterTypesAndCallback) {
        Class<?>[] parameterClasses = null;
        for (int i = parameterTypesAndCallback.length - 1; i >= 0; i--) {
            Object type = parameterTypesAndCallback[i];
            if (type instanceof XC_MethodHook)
                continue;
            if (parameterClasses == null)
                parameterClasses = new Class<?>[i + 1];
            if (type instanceof Class)
                parameterClasses[i] = (Class<?>) type;
            else if (type instanceof String)
                parameterClasses[i] = findClass((String) type, classLoader);
        }
        if (parameterClasses == null)
            parameterClasses = new Class<?>[0];
        return parameterClasses;
    }

    @Override
    public void hookMethod() {
        XposedBridge.hookMethod(m, callback);
    }
}
