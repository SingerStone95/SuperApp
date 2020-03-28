package singerstone.com.superapp.ServiceIPC;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import singerstone.com.annotations.BindView;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.R;
import singerstone.com.superapp.aidl.IUserAddListener;
import singerstone.com.superapp.aidl.User;
import singerstone.com.superapp.utils.L;

public class ServiceIPCActivity extends Activity {

    @BindView(R.id.tv_start)
    TextView tv_start;
    @BindView(R.id.tv_stop)
    TextView tv_stop;
    TextView tv_bind;
    TextView tv_unbind;
    TextView tv_show;
    singerstone.com.superapp.aidl.IPCAidl mIPCBinder;
    private RemoteCallbackList<IUserAddListener> mListener = new
            RemoteCallbackList<IUserAddListener>();
    IUserAddListener.Stub mIAddUserListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceipc);
        ViewInject.inject(this);
        tv_bind = (TextView) findViewById(R.id.tv_bind);
        tv_unbind = (TextView) findViewById(R.id.tv_unbind);
        tv_show = (TextView) findViewById(R.id.tv_show);


        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动远程服务
                Intent intent = new Intent();
                intent.setClass(ServiceIPCActivity.this, IPCService.class);
               // startService(intent);
                bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });
        tv_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = new Intent();
                intent.setClass(ServiceIPCActivity.this, IPCService.class);
                stopService(intent);*/
                if (mIPCBinder != null) {
                    try {
                        mIPCBinder.closeService();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
               /* if (conn != null) {
                    unbindService(conn);
                }*/
            }
        });
        mIAddUserListener = new IUserAddListener.Stub() {

            @Override
            public void onUseradded(final User user) throws RemoteException {
                if (tv_show != null) {
                    tv_show.post(new Runnable() {
                        @Override
                        public void run() {
                            tv_show.setText(user.toString());
                        }
                    });

                }
            }
        };
        tv_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIPCBinder != null) {
                    try {
                        mIPCBinder.registUserListener(mIAddUserListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tv_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIPCBinder != null) {
                    try {
                        mIPCBinder.unRegisterListener(mIAddUserListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            /**
             * 通过IBinder传回Binder对象,如果Service和Activity不同进程，即是返回的Proxy类，进行跨进程通信
             */

            mIPCBinder = singerstone.com.superapp.aidl.IPCAidl.Stub.asInterface(service);
            try {
                mIPCBinder.showThreadInfo();//跨进程调用就不行了
                User user = new User();
                user.uid = 123456;
                user.name = "cbh";
                mIPCBinder.showUser(user);
                L.e("获得的远程服务器的值:" + mIPCBinder.getUsers());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
    /*    Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setClass(ServiceIPCActivity.this, IPCService.class);
        stopService(intent);*/

        if (mIPCBinder != null) {
            try {
                mIPCBinder.closeService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }


}
