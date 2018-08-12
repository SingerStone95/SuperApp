package singerstone.com.inject;

/**
 * Created by chenbinhao on 2018/4/24.
 * YY:909075276
 * 实现最简单的注入接口
 */

public interface InjectTest<T> {
    /**
     * host.xxx=sourse.findViewbyId(xxx)
     *
     * @param host
     * @param sourse
     */
    void inject(T host, Object sourse);
}
