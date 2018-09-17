package singerstone.com.superapp.qqlive;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.TimeZone;

import singerstone.com.superapp.utils.L;

/**
 * 添加日历事件工具类
 */
public class CalendarReminderUtils {

    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";
    private static long DEFAULT_END_REMIND_TIME = 10 * 60 * 1000; //设置终止时间，开始时间加10分钟
    private static int DEFAULT_CALENDAR_ID = 1; //默认CalendarId
    public static long DONT_REMIND = 0L;

    /**
     * 打开日历事件 Intent方式,打开日历APP，无需权限
     *
     * @param context
     * @param title        标题
     * @param description  描述
     * @param reminderTime 提醒的时间
     */
    public static void addCalendarEventManual(Context context, String title, String description, long reminderTime) {
        if (context == null) {
            return;
        }
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + DEFAULT_END_REMIND_TIME);
        long end = mCalendar.getTime().getTime();
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, description)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "娱乐")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        context.startActivity(intent);
    }

    /**
     * 添加日历事件不附带Action信息 ContentProvider方式 调用前确保授权
     *
     * @param context
     * @param title        标题
     * @param description  描述
     * @param reminderTime 提醒时间
     * @param previousHour 提前XXX小时提醒
     */
    public static void addCalendarEventAuto(Context context, String title, String description, String location, long reminderTime, int previousHour) {
        if (context == null) {
            return;
        }
        int calId = DEFAULT_CALENDAR_ID;
        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + DEFAULT_END_REMIND_TIME);//设置终止时间，开始时间加10分钟
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        if (!isEmpty(title)) {
            event.put(CalendarContract.Events.TITLE, title);
        }
        if (!isEmpty(description)) {
            event.put(CalendarContract.Events.DESCRIPTION, description);
        }
        if (!isEmpty(location)) {
            event.put(CalendarContract.Events.EVENT_LOCATION, location);
        }
        event.put(CalendarContract.Events.CALENDAR_ID, calId); //插入账户的id
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());//这个是时区，必须有
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
        if (newEvent == null) { //添加日历事件失败直接返回
            L.e("add calendar event failed!");
            return;
        }
        L.e("add calendar event success!");
        //事件提醒的设定
        if (reminderTime == DONT_REMIND) {
            return;
        }
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.MINUTES, previousHour * 60);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
        if (uri == null) { //添加事件提醒失败直接返回
            L.e("add reminder failed!");
            return;
        }
        L.e("add reminder success!");
    }

    /**
     * 添加日历事件附带Action信息 ContentProvider方式 调用前确保授权
     *
     * @param context
     * @param title        标题
     * @param description  描述
     * @param location     文本，建议设置成跳转URL，在某些系统可以点击跳转（emui），某些系统不会显示（miui）
     * @param action       附带的Action信息
     * @param reminderTime 事件时间 (不提醒设置成0)
     * @param previousHour 提前XXX小时提醒 （不提醒设设置成0）
     */
    public static void addCalendarEventWithAction(Context context, String title, String description, String location, String action, long reminderTime, int previousHour) {
        if (context == null) {
            return;
        }
        int calId = DEFAULT_CALENDAR_ID;
        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + DEFAULT_END_REMIND_TIME);//设置终止时间，开始时间加10分钟
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        if (!isEmpty(title)) {
            event.put(CalendarContract.Events.TITLE, title);
        }
        if (!isEmpty(description)) {
            event.put(CalendarContract.Events.DESCRIPTION, description);
        }
        if (!isEmpty(location)) {
            event.put(CalendarContract.Events.EVENT_LOCATION, location);
        }
        event.put(CalendarContract.Events.CALENDAR_ID, calId); //插入账户的id
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());//这个是时区，必须有
        event.put(CalendarContract.Events.CUSTOM_APP_PACKAGE, context.getPackageName());
        if (!isEmpty(action)) {
            event.put(CalendarContract.Events.CUSTOM_APP_URI, action);
        }
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
        if (newEvent == null) { //添加日历事件失败直接返回
            L.e("add calendar failed!");
            return;
        }
        //事件提醒的设定
        if (reminderTime == DONT_REMIND) {
            return;
        }
        ContentValues values = new ContentValues();
        //设置提醒
        L.e("event id :" + ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.MINUTES, previousHour * 60);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
        if (uri == null) { //添加事件提醒失败直接返回
            L.e("add alert failed!");
            return;
        }
        L.e("add alert success!");
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str) || "NULL".equals(str.toUpperCase());
    }

    /**
     * 删除日历事件 根据标题和描述确定同一条日历事件
     *
     * @param context
     * @param title
     * @param description
     */
    public static void deleteCalendarEvent(Context context, String title, String description) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex(CalendarContract.Events.TITLE));
                    String descri = eventCursor.getString(eventCursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle) && !TextUtils.isEmpty(descri) && descri.equals(description)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) { //事件删除失败
                            L.e("delete calendar event error!");
                            return;
                        }
                    }
                }
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
    }

    /**
     * 查询是否存在事件,根据title和description判断
     *
     * @param context
     * @param title       标题
     * @param description 描述
     * @return
     */
    public static boolean hasCalendarEvent(Context context, String title, String description) {
        if (context == null) {
            return false;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return false;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex(CalendarContract.Events.TITLE));
                    String descri = eventCursor.getString(eventCursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle) && !TextUtils.isEmpty(descri) && descri.equals(description)) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            L.e(e.getMessage());
            return false;
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
        return false;
    }
}
