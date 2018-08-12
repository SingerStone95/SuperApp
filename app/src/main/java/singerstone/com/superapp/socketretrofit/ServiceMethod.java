package singerstone.com.superapp.socketretrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import singerstone.com.superapp.socketretrofit.annotation.Singer;
import singerstone.com.superapp.socketretrofit.annotation.Stone;

/**
 * Created by chenbinhao on 2017/7/12.
 * YY:909075276
 */

public class ServiceMethod {
    public String ipAddr;
    public int port;
    public String[] paramsAnnoNames;


    public ServiceMethod(Method method) {
        for (Annotation annotation : method.getAnnotations()) {
            parseMethodAnnotation(annotation);
        }
        paramsAnnoNames = getMethodParameterNamesByAnnotation(method);
    }

    private void parseMethodAnnotation(Annotation annotation) {
        if (annotation instanceof Singer) {
            ipAddr = ((Singer) annotation).ipAddr();
            port = ((Singer) annotation).port();
        }

    }

    String[] getMethodParameterNamesByAnnotation(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return null;
        }
        String[] parameterNames = new String[parameterAnnotations.length];
        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation instanceof Stone) {
                    Stone param = (Stone) annotation;
                    parameterNames[i++] = param.name()
                    ;
                }
            }
        }
        return parameterNames;
    }
}
