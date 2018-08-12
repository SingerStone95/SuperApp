package singerstone.com.superapp.inke;

/**
 * Created by chenbinhao on 2018/1/2.
 * YY:909075276
 */

public class Result {

    private long error_code;
    private String message;
    private String data;
    public void setError_code(long error_code) {
        this.error_code = error_code;
    }
    public long getError_code() {
        return error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
}
