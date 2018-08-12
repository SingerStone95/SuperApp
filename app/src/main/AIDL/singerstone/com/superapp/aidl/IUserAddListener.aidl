// IUserAddListener.aidl
package singerstone.com.superapp.aidl;

// Declare any non-default types here with import statements
import singerstone.com.superapp.aidl.User;
interface IUserAddListener {
   void onUseradded(in User user);
}
