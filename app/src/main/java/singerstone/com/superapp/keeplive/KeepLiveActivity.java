package singerstone.com.superapp.keeplive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.L;

public class KeepLiveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_live);
        L.e("KeepLiveActivity onCreate");
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.START | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        //宽高设计为1个像素
        attributes.width = 1;
        attributes.height = 1;
        //起始坐标
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        /**
         * 把这个Activity保存在Manager类中用于finish
         */
        AliveActivityManager.getInstance(this).setActivity(this);
    }


    public static void actionToLiveActivity(Context pContext) {
        Intent intent = new Intent(pContext, KeepLiveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pContext.startActivity(intent);
    }

    @Override
    protected void onStop() {
        L.e("KeepLiveActivity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        L.e("KeepLiveActivity onDestroy");
        super.onDestroy();

    }
}
