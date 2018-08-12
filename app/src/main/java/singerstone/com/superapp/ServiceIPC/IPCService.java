package singerstone.com.superapp.ServiceIPC;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import singerstone.com.superapp.aidl.IPCAidl;
import singerstone.com.superapp.aidl.IUserAddListener;
import singerstone.com.superapp.aidl.User;
import singerstone.com.superapp.utils.L;

public class IPCService extends Service {
    private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IUserAddListener> mListener = new
            RemoteCallbackList<>();
    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
    private boolean isStop = false;

    public IPCService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e("IPCService  onBind");
        // TODO: Return the communication channel to the service.
        return new IPCBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e("IPCService  onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.e("远程服务器创建成功...");
        users.add(new User("chenbinhao", 2017));
        users.add(new User("xiaohong", 2018));
        L.e("创建两个User:" + users);
        new Thread(new addTask()).start();
    }

    class addTask implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get() && isStop == false) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = users.size() + 1;
                User newUser = new User("name" + bookId, bookId);
                L.e("远程服务器生产一个:" + newUser);
                try {
                    onNewUserArrived(newUser);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onNewUserArrived(User user) throws RemoteException {
        users.add(user);
        int N = mListener.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IUserAddListener listener = mListener.getBroadcastItem(i);
            listener.onUseradded(user);
        }
        mListener.finishBroadcast();
    }

    public class IPCBinder extends IPCAidl.Stub {


        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void showThreadInfo() throws RemoteException {
            int pid = android.os.Process.myPid();
            String processName = "";
            ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                if (process.pid == pid) {
                    processName = process.processName;
                }
            }
            L.e(processName);
        }

        @Override
        public void showUser(User user) throws RemoteException {
            L.e("从主进程传来的值:" + user.name + "  " + user.uid);
        }

        @Override
        public List<User> getUsers() throws RemoteException {
            return users;
        }

        @Override
        public void registUserListener(IUserAddListener listener) throws RemoteException {
            mListener.register(listener);
        }

        @Override
        public void unRegisterListener(IUserAddListener listener) throws RemoteException {
            mListener.unregister(listener);
        }

        @Override
        public void closeService() throws RemoteException {
            L.e("调用 stopSelf()");
            isStop = true;
            IPCService.this.stopSelf();
        }

    }

    @Override
    public void unbindService(ServiceConnection conn) {
        L.e("解绑远程服务");
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        L.e("停止远程服务");
        super.onDestroy();
    }
}
