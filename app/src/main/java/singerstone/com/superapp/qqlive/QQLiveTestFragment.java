package singerstone.com.superapp.qqlive;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import singerstone.com.annotations.BindView;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.MainActivity;
import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.Device;
import singerstone.com.superapp.utils.L;

import static android.content.Context.CLIPBOARD_SERVICE;

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
    @BindView(R.id.btn_get_clipboard)
    public Button btnGetClipboard;

    public static String TITLE_SHORTCUT = "test";

    public static QQLiveTestFragment newInstance() {
        QQLiveTestFragment fragment = new QQLiveTestFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup view = (ViewGroup) inflater.inflate(R.layout.layout_qqlive_test_fragment, container, false);
        final Intent intent = new Intent(getActivity(), MainActivity.class);
        ViewInject.inject(this, view);
        btnAddShort.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "no", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();
                }
                L.i("version:--->>" + Device.getVersionName(getActivity()));
                if (Device.getVersionName(getActivity()) < Build.VERSION_CODES.O) {
                    installShortCut(TITLE_SHORTCUT, R.mipmap.ic_launcher, intent);
                } else {
                    addShortCut(getActivity());
                }


            }
        });
        L.e(btnAddShort.length() + "   <<<<<<<<");
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
                long time = stringToDate("2018-9-17 16:22:00", "yyyy-MM-dd HH:mm:ss").getTime();
                CalendarReminderUtils.addCalendarEventWithAction(getActivity(), "watch movie", "8.00 watch movie", "www.baidu.com ",
                        "txvideo://v.qq.com/VideoDetailActivity?vid=s0020ghdx11", time, 1);
                // CalendarReminderUtils.addCalendarEventManual(getActivity(), "134", "2334", time);
                //Calendar2.addCalendarEvent(getActivity(), "111", "22", time, 1);
            }
        });
        btnRemoveCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CalendarReminderUtils.hasCalendarEvent(getActivity(), "watch movie", "8.00 watch movie")) {
                    CalendarReminderUtils.deleteCalendarEvent(getActivity(), "watch movie", "8.00 watch movie");
                } else {
                    Toast.makeText(getActivity(), "no event yet!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnGetClipboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);

                //写文本
                ClipData clipWrite = ClipData.newPlainText("txvideo", "");
                clipboard.setPrimaryClip(clipWrite);
                //读文本
                ClipData clipRead = clipboard.getPrimaryClip();
                if (clipRead == null || clipRead.getItemCount() == 0)
                    return; // ... whatever; just don't go to next line
                String t = clipRead.getItemAt(0).getText().toString();


                Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                //showBubbleOnShare(getActivity(), "和小伙伴们一起来学习吧", btnGetClipboard, 3000);

                final View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_pop_window_bubble, null);
                contentView.measure(makeDropDownMeasureSpec(view.getMeasuredWidth()), makeDropDownMeasureSpec(view.getMeasuredHeight()));
                L.i(contentView.getMeasuredWidth() + "---" + contentView.getMeasuredHeight());
                view.addView(contentView);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        L.i(contentView.getWidth() + "");
                    }
                });

            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        L.i(list.toString());
        L.i(list.toString());
        return view;
    }


    /**
     * 在分享ICON上显示气泡，纯文字
     *
     * @param text 文案
     */
    private void showBubbleOnShare(Context context, String text, View anchorView, long delayTime) {
     /*   View contentView = LayoutInflater.from(context).inflate(R.layout.layout_pop_window_bubble, null);
        TextView tvTips = contentView.findViewById(R.id.tv_tips);
        tvTips.setText(text);
        PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);
        L.e(" location0:" + location[0] + "  location1:" + location[1]);
        contentView.measure(makeDropDownMeasureSpec(popupWindow.getWidth()), makeDropDownMeasureSpec(popupWindow.getHeight()));
        L.i(contentView.getMeasuredWidth() + "---" + contentView.getMeasuredHeight());
        popupWindow.showAtLocation(anchorView, Gravity.TOP, location[0],
                location[1]);*/
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_pop_window_bubble, null);
        TextView tvTips = contentView.findViewById(R.id.tv_tips);
        tvTips.setText(text);
        RelativePopupWindow popupWindow = new RelativePopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.showOnAnchor(anchorView, RelativePopupWindow.VerticalPosition.ABOVE, RelativePopupWindow.HorizontalPosition.ALIGN_RIGHT);

    }

    private static int makeDropDownMeasureSpec(int measureSpec) {
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measureSpec), View.MeasureSpec.UNSPECIFIED);
    }

    private static int getDropDownMeasureSpecMode(int measureSpec) {
        switch (measureSpec) {
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                return View.MeasureSpec.UNSPECIFIED;
            default:
                return View.MeasureSpec.EXACTLY;
        }
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


    @TargetApi(Build.VERSION_CODES.O)
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
