// com.singerstone.IPCAidl.aidl
package singerstone.com.superapp.aidl;
// Declare any non-default types here with import statements
import singerstone.com.superapp.aidl.User;
import singerstone.com.superapp.aidl.IUserAddListener;
interface IPCAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            void showThreadInfo();
            void showUser(inout User user);
            List<User> getUsers();
            void registUserListener(IUserAddListener listener);
            void unRegisterListener(IUserAddListener listener);
            void closeService();


}
