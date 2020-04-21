package singerstone.com.superapp.log;

import java.io.OutputStream;

/**
 * author : yogachen
 * date   : 2020-04-21
 * desc   :
 */
public interface IlogProxy {
    void d(String tag, String msg);

    void i(String tag, String msg);

    void w(String tag, String msg);

    void e(String tag, String msg);

    void packLog(OutputStream outputStream);

    void flush(boolean isSync);

    void quit();

}
