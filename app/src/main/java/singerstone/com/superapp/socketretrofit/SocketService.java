package singerstone.com.superapp.socketretrofit;

import io.reactivex.Observable;
import singerstone.com.superapp.socketretrofit.annotation.Singer;
import singerstone.com.superapp.socketretrofit.annotation.Stone;

/**
 * Created by chenbinhao on 2017/7/12.
 * YY:909075276
 */

public interface SocketService {
    @Singer(ipAddr = "172.26.82.7",port = 8765)
    public Observable<String> commit(@Stone(name ="name")String name,@Stone(name = "word")String word);

    @Singer(ipAddr = "172.26.82.7",port = 8765)
    public Observable<String> commit2(@Stone(name ="name")String name,@Stone(name = "word")String word);
}
