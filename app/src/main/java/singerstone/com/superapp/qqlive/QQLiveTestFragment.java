package singerstone.com.superapp.qqlive;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import singerstone.com.annotations.BindView;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.MainActivity;
import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.Device;
import singerstone.com.superapp.utils.L;

public class QQLiveTestFragment extends Fragment {
    @BindView(R.id.btn_add_shortcut)
    public Button btnAddShort;
    @BindView(R.id.btn_remove_shortcut)
    public Button btnRemoveShort;
    @BindView(R.id.btn_find_shortcut)
    public Button btnFindShortcut;

    @BindView(R.id.btn_add_can)
    public Button btnAddCan;
    @BindView(R.id.btn_remove_can)
    public Button btnRemoveCan;

    public static String TITLE_SHORTCUT = "test";

    public static QQLiveTestFragment newInstance() {
        QQLiveTestFragment fragment = new QQLiveTestFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_qqlive_test_fragment, container, false);
        final Intent intent = new Intent(getActivity(), MainActivity.class);
        ViewInject.inject(this, view);
        btnAddShort.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                L.i("version:--->>" + Device.getVersionName(getActivity()));
                if (Device.getVersionName(getActivity()) < Build.VERSION_CODES.O) {
                    installShortCut(TITLE_SHORTCUT, R.drawable.faceeli_ic_pkscenen_gift, intent);
                } else {
                    addShortCut(getActivity());
                }
            }
        });
        btnFindShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExsist = LauncherUtil.isShortCutExist(getActivity(), TITLE_SHORTCUT);
                if (isExsist) {
                    Toast.makeText(getActivity(), "exsist", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "not exsist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRemoveShort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShortcut(getActivity(), TITLE_SHORTCUT, intent, true);
            }
        });
        btnAddCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long time = stringToDate("2018-9-14 18:51:00", "yyyy-MM-dd HH:mm:ss").getTime();
                CalendarReminderUtils.addCalendarEventAuto(getActivity(), "watch movie", "8.00 watch movie", time, 1);
            }
        });
        btnRemoveCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarReminderUtils.deleteCalendarEvent(getActivity(), "watch movie");
            }
        });

        return view;
    }

    public static Date stringToDate(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public void installShortCut(String name, int icon, Intent intent) {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

        // 快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        shortcut.putExtra("duplicate", false); // 不允许重复创建

        // 快捷方式的图标
        Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(getActivity(), icon);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);

        intent.setAction("android.intent.action.MAIN");// 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
        intent.addCategory("android.intent.category.LAUNCHER");// 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);

        getActivity().sendBroadcast(shortcut);
    }

    /**
     * 删除桌面快捷方式
     *
     * @param context
     * @param shortcutName 快捷方式名
     * @param actionIntent 快捷方式操作，也就是上面创建的Intent
     * @param isDuplicate  为true时循环删除快捷方式（即存在很多相同的快捷方式）
     */
    public static void deleteShortcut(Context context, String shortcutName,
                                      Intent actionIntent, boolean isDuplicate) {
        Intent shortcutIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        shortcutIntent.putExtra("duplicate", isDuplicate);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(shortcutIntent);
    }


    public static void addShortCut(Context context) {
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);

        if (shortcutManager.isRequestPinShortcutSupported()) {
            Intent shortcutInfoIntent = new Intent(context, MainActivity.class);
            shortcutInfoIntent.setAction(Intent.ACTION_VIEW); //action必须设置，不然报错
            shortcutInfoIntent.putExtra("test", "test");

            ShortcutInfo info = new ShortcutInfo.Builder(context, "The only id")
                    .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                    .setShortLabel("Short Label")
                    .setIntent(shortcutInfoIntent)
                    .build();

            //当添加快捷方式的确认弹框弹出来时，将被回调
            PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(context, 0, new Intent(context, MyReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);

            shortcutManager.requestPinShortcut(info, shortcutCallbackIntent.getIntentSender());
        }

    }

}
