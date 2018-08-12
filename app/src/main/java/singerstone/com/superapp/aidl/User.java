package singerstone.com.superapp.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenbinhao on 2017/12/21.
 * YY:909075276
 */

public class User implements Parcelable {

    public String name = "";
    public long uid = 0;

    protected User(Parcel in) {
        name = in.readString();
        uid = in.readLong();
    }

    public User() {

    }

    public User(String name, long uid) {
        this.name = name;
        this.uid = uid;
    }

    public void readFromParcel(Parcel in) { //空实现都可以
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(uid);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }
}
