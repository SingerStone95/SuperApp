package singerstone.com.superapp.socketretrofit;

import io.reactivex.Observable;
import singerstone.com.superapp.socketretrofit.annotation.Singer;
import singerstone.com.superapp.socketretrofit.annotation.Stone;

/**
 * Created by chenbinhao on 2017/7/12.
 * YY:909075276
 */

public interface SocketService {
    @Singer(ipAddr = "127.0.0.1",port = 8899)
    public Observable<String> commit(@Stone(name ="name")String name,@Stone(name = "word")String word);

    @Singer(ipAddr = "127.0.0.1",port = 8899)
    public Observable<String> commit2(@Stone(name ="name")String name,@Stone(name = "word")String word);
}
