package singerstone.com.inject;


import singerstone.com.inject.provider.Provider;

/**
 * Created by JokAr on 16/8/6.
 */
public interface Inject<T> {

    /**
     *
     * @param host 代表有注解的那个类
     * @param object view或者Activity object.findviewbyid
     * @param provider 一个接口，手动实现接口，apt生成的代码调用接口
     */
    void inject(T host, Object object, Provider provider);
}
