package singerstone.com.superapp.socketretrofit;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenbinhao on 2017/7/12.
 * YY:909075276
 */

public class Singerstone {


    // 在类被加载进入内存的时候就创建单一的Foo对象
    public static final Singerstone singerstone = new Singerstone();
    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap<>();

    // 构造函数私有化
    private Singerstone() {

    }

    // 提供一个全局的静态方法
    public static Singerstone getInstance() {
        return singerstone;
    }

    public <T> T create(final Class<T> service) {
        Utils.validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object... args)
                            throws Throwable {
                        // If the method is a method from Object then defer to normal invocation.
                        if (method.getDeclaringClass() == Object.class) {
                            return method.invoke(this, args);
                        }
                        ServiceMethod serviceMethod = loadServiceMethod(method);

                        return sendMessage(serviceMethod, args);
                    }
                });
    }

    ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result;
        synchronized (serviceMethodCache) {
            result = serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod(method);
                serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    private Observable<String> sendMessage(final ServiceMethod serviceMethod, final Object... args) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Socket socket = new Socket(serviceMethod.ipAddr, serviceMethod.port);
                OutputStream out = socket.getOutputStream();
                String writeString = "";
                for (int i = 0; i < args.length; i++) {
                    writeString += ((String) serviceMethod.paramsAnnoNames[0] + ":" + (String) args[i]+"\n");
                }
                out.write(writeString.getBytes("utf-8"));
                InputStream in = socket.getInputStream();
                byte[] buffer = new byte[1024];
                in.read(buffer);
                emitter.onNext(new String(buffer, 0, buffer.length));

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
