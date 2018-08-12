package singerstone.com.superapp.socketretrofit;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by liyong on 2017/7/7.
 */

public class Utils {

    /**
     * 获取指定方法的参数名
     *
     * @param method 要获取参数名的方法
     * @return 按参数顺序排列的参数名列表
     */


    static Type[] getTurnTypeGenericTypes(Method method){
        Type type=method.getGenericReturnType();
        if(type instanceof ParameterizedType){
            Type[] pt=((ParameterizedType)type).getActualTypeArguments();
            return pt;
        }
        return null;
    }

    static Class getParamGenericTypes(Method method){
        Type[] types=method.getGenericParameterTypes();
        if(types==null){
            throw new IllegalArgumentException("参数必须是SmartObservelResult<T>,T is String or BaseEntity类型");
        }
        Type type=types[0];
        if(type instanceof ParameterizedType){
            Type[] pt=((ParameterizedType)type).getActualTypeArguments();
            return (Class) pt[0];
        }
        return null;
    }

    static  <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }
}
