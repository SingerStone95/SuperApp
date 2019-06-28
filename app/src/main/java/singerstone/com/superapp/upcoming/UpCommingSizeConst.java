package singerstone.com.superapp.upcoming;

import android.content.Context;

import singerstone.com.superapp.utils.DimentionUtils;

/**
 * author : yogachen
 * date   : 2019-06-28
 * desc   :
 */
public class UpCommingSizeConst {
    public static int sBaseWidth = 0;

    public static int sBaseHeight = 0;


    public static int sBigPosterWidth = 0;
    public static int sBigPosterHeight = 0;

    public static int sMidPosterWidth = 0;
    public static int sMidPosterHeight = 0;

    public static int sLeftOffset = 0;

    public static int getBasePosterWidth(Context context) {
        if (sBaseWidth != 0) {
            return sBaseWidth;
        }
        sBaseWidth = (int) (DimentionUtils.getScreenWidth(context) * 0.23f);
        return sBaseWidth;
    }

    public static int getBasePosterHeight(Context context) {
        if (sBaseHeight != 0) {
            return sBaseHeight;
        }
        int width = getBasePosterWidth(context);
        sBaseHeight = (int) (width * 1.41);
        return sBaseHeight;
    }

    public static int getBigPosterWidth(Context context) {
        if (sBigPosterWidth != 0) {
            return sBigPosterWidth;
        }
        return (int) (getBasePosterWidth(context) * 2.82);
    }

    public static int getBigPosterHeight(Context context) {
        if (sBigPosterHeight != 0) {
            return sBigPosterHeight;
        }
        return (int) (getBasePosterHeight(context) * 1.19);
    }

    public static int getMidPosterWidth(Context context) {
        if (sMidPosterWidth != 0) {
            return sMidPosterWidth;
        }
        return (int) (getBasePosterWidth(context) * 1.36);
    }

    public static int getMidPosterHeight(Context context) {
        if (sMidPosterHeight != 0) {
            return sMidPosterHeight;
        }
        return (int) (getBasePosterHeight(context) * 1.08);
    }

    public static int getLeftOffeset(Context context) {
        if (sLeftOffset != 0) {
            return sLeftOffset;
        }
        return (int) (getBasePosterWidth(context) * 0.14);
    }


}
