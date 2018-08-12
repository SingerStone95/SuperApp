package singerstone.com.superapp.Marquee;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class ResolutionUtils {

    /**
     * Gets the width of the display, in pixels.
     * 
     * Note that this value should not be used for computing layouts, since a
     * device will typically have screen decoration (such as a status bar) along
     * the edges of the display that reduce the amount of application space
     * available from the size returned here. Layouts should instead use the
     * window size.
     * 
     * The size is adjusted based on the current rotation of the display.
     * 
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display. The returned size may
     * be adjusted to exclude certain system decoration elements that are always
     * visible. It may also be scaled to provide compatibility with older
     * applications that were originally designed for smaller displays.
     * 
     * 
     * @return Screen width in pixels.
     */
    public static int getScreenWidth(Context context) {
        return getScreenSize(context, null).x;
    }

    /**
     * 
     * Gets the height of the display, in pixels.
     * 
     * Note that this value should not be used for computing layouts, since a
     * device will typically have screen decoration (such as a status bar) along
     * the edges of the display that reduce the amount of application space
     * available from the size returned here. Layouts should instead use the
     * window size.
     * 
     * The size is adjusted based on the current rotation of the display.
     * 
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display. The returned size may
     * be adjusted to exclude certain system decoration elements that are always
     * visible. It may also be scaled to provide compatibility with older
     * applications that were originally designed for smaller displays.
     * 
     * 
     * @return Screen height in pixels.
     */
    public static int getScreenHeight(Context context) {
        return getScreenSize(context, null).y;
    }

    /**
     * 
     * Gets the size of the display, in pixels.
     * 
     * Note that this value should not be used for computing layouts, since a
     * device will typically have screen decoration (such as a status bar) along
     * the edges of the display that reduce the amount of application space
     * available from the size returned here. Layouts should instead use the
     * window size.
     * 
     * The size is adjusted based on the current rotation of the display.
     * 
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display. The returned size may
     * be adjusted to exclude certain system decoration elements that are always
     * visible. It may also be scaled to provide compatibility with older
     * applications that were originally designed for smaller displays.
     * 
     * 
     * @param outSize
     *            null-ok. If it is null, will create a Point instance inside,
     *            otherwise use it to fill the output. NOTE if it is not null,
     *            it will be the returned value.
     * 
     * 
     * @return Screen size in pixels, the x is the width, the y is the height.
     */
    public static Point getScreenSize(Context context, Point outSize) {
        WindowManager wm = (WindowManager) context
            .getSystemService(Context.WINDOW_SERVICE);
        Point ret = outSize == null ? new Point() : outSize;
        final Display defaultDisplay = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(ret);
        }
        else {
            ret.x = defaultDisplay.getWidth();
            ret.y = defaultDisplay.getHeight();
        }
        return ret;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){

        try {
            if(context==null){
                return dp;
            }
            Resources resources = context.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            float px = dp * (metrics.densityDpi / 160f);
            return px;
        }catch (Exception ex){
            Log.e("ResolutionUtils", "Empty Catch on convertDpToPixel", ex);
        }

        return -1;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        try {

            if(context==null){
                return px;
            }
            Resources resources = context.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            float dp = px / (metrics.densityDpi / 160f);
            return dp;

        }catch (Exception ex){
            Log.e("ResolutionUtils", "Empty Catch on convertPixelsToDp", ex);
        }
        return -1;
    }

	/**
	 * 返回屏幕的宽高比
	 * @param context
	 * @param outSize
	 * @return
	 */
	public static float getScreenWidthHeightRatio(Context context, Point outSize) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Point ret = outSize == null ? new Point() : outSize;
		final Display defaultDisplay = wm.getDefaultDisplay();
		if (Build.VERSION.SDK_INT >= 13) {
			defaultDisplay.getSize(ret);
		}
		else {
			ret.x = defaultDisplay.getWidth();
			ret.y = defaultDisplay.getHeight();
		}
		float xf = ret.x;
		float yf = ret.y;
		return xf/yf;
	}

}
