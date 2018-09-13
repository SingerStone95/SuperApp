package singerstone.com.superapp.qqlive;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.TimeZone;

import singerstone.com.superapp.utils.L;

public class CalendarReminderUtils {

    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";


    /**
     * 添加日历事件 Intent方式，无需权限
     */
    public static void addCalendarEventManual(Context context, String title, String description, long reminderTime, int previousDate) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + 10 * 60 * 1000);//设置终止时间，开始时间加10分钟
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
     * 添加日历事件 ContentProvider方式 需要权限
     */
    public static void addCalendarEventAuto(Context context, String title, String description, long reminderTime, int previousDate) {
        if (context == null) {
            return;
        }
        int calId = 1;
        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(start + 10 * 60 * 1000);//设置终止时间，开始时间加10分钟
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        event.put("title", title);
        event.put("description", description);
        event.put("calendar_id", calId); //插入账户的id
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");//这个是时区，必须有
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件
        if (newEvent == null) { //添加日历事件失败直接返回
            L.e("add calendar failed!");
            return;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.MINUTES, previousDate * 24 * 60);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
        if (uri == null) { //添加事件提醒失败直接返回
            L.e("add alert failed!");
            return;
        }
        L.e("add alert success!");
    }

    /**
     * 删除日历事件
     */
    public static void deleteCalendarEvent(Context context, String title) {
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
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) { //事件删除失败
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
}
