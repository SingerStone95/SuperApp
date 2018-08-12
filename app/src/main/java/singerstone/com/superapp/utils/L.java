package singerstone.com.superapp.utils;

import android.util.Log;

/**
 * Log统一管理类
 * 
 * @author chenbinhao
 *
 * 解决了日志自动截断问题
 *
 * 建议App类中初始化，并同一控制，不初始化也可以。
 * 
 */
public class L
{


	public L()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
	private static  String TAG = "SINGERSTONE_DEBUG";

	// 下面四个是默认tag的函数
	public static void i(String msg)
	{

		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.i(TAG, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.i(TAG, logContent);
				}
				Log.i(TAG, msg);
			}


		}
	}

	public static void d(String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.d(TAG, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.d(TAG, logContent);
				}
				Log.d(TAG, msg);
			}


		}
	}

	public static void e(String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.e(TAG, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.e(TAG, logContent);
				}
				Log.e(TAG, msg);
			}


		}



	}

	public static void v(String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.v(TAG, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.v(TAG, logContent);
				}
				Log.v(TAG, msg);
			}
		}
	}

	// 下面是传入自定义tag的函数
	public static void i(String tag, String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.i(tag, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.i(tag, logContent);
				}
				Log.i(tag, msg);
			}
		}
	}

	public static void d(String tag, String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.d(tag, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.d(tag, logContent);
				}
				Log.d(tag, msg);
			}
		}
	}

	public static void e(String tag, String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.e(tag, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.e(tag, logContent);
				}
				Log.e(tag, msg);
			}
		}
	}

	public static void v(String tag, String msg)
	{
		if (isDebug) {
			//Log.e(TAG, msg);
			int p = 2048;
			long length = msg.length();
			if (length < p || length == p)
				Log.v(tag, msg);
			else {
				while (msg.length() > p) {
					String logContent = msg.substring(0, p);
					msg = msg.replace(logContent, "");
					Log.v(tag, logContent);
				}
				Log.v(tag, msg);
			}
		}
	}
}