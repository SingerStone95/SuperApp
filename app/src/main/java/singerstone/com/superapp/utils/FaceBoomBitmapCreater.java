package singerstone.com.superapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.TypedValue;

import singerstone.com.superapp.R;

/**
 * Created by SingerStone on 2017.11.11.
 */

public class FaceBoomBitmapCreater {
    public static Bitmap createBitmapNum(Context context,int num){
        if (num>9999){
            return createBitmap(9999,context);
        }else if (num<0){
            return createBitmap(0,context);
        }else {
            return createBitmap(num,context);
        }

    }

    private static Bitmap createBitmap(int num,Context context){
      if (num>999){//四位数
            int g=num%10;
            int s=num/10%10;
            int b=num/100%10;
            int q=num/1000;
            Bitmap qb=mergeBitmap_LR(getBitmapFromOneMum(q,context),getBitmapFromOneMum(b,context),false,context);
            Bitmap qbs=mergeBitmap_LR(qb,getBitmapFromOneMum(s,context),false,context);
            return mergeBitmap_LR(qbs,getBitmapFromOneMum(g,context),false,context);
        }else if (num>99) {
            int g=num%10;
            int s=num/10%10;
            int b=num/100;
            Bitmap bs=mergeBitmap_LR(getBitmapFromOneMum(b,context),getBitmapFromOneMum(s,context),false,context);
            return mergeBitmap_LR(bs,getBitmapFromOneMum(g,context),false,context);
        }else if (num>9) {
            int g=num%10;
            int s=num/10;
            return  mergeBitmap_LR(getBitmapFromOneMum(s,context),getBitmapFromOneMum(g,context),false,context);
        }else if (num>=0){
            return getBitmapFromOneMum(num,context);
        }else {
            return null;
        }
    }

    /**
     * 把两个位图覆盖合成为一个位图，左右拼接
     * @param leftBitmap
     * @param rightBitmap
     * @param isBaseMax 是否以宽度大的位图为准，true则小图等比拉伸，false则大图等比压缩
     * @return
     */
    public static Bitmap mergeBitmap_LR(Bitmap leftBitmap, Bitmap rightBitmap, boolean isBaseMax,Context context) {

        if (leftBitmap == null || leftBitmap.isRecycled()
                || rightBitmap == null || rightBitmap.isRecycled()) {
            return null;
        }
        int height = 0; // 拼接后的高度，按照参数取大或取小
        if (isBaseMax) {
            height = leftBitmap.getHeight() > rightBitmap.getHeight() ? leftBitmap.getHeight() : rightBitmap.getHeight();
        } else {
            height = leftBitmap.getHeight() < rightBitmap.getHeight() ? leftBitmap.getHeight() : rightBitmap.getHeight();
        }

        // 缩放之后的bitmap
        Bitmap tempBitmapL = leftBitmap;
        Bitmap tempBitmapR = rightBitmap;

        if (leftBitmap.getHeight() != height) {
            tempBitmapL = Bitmap.createScaledBitmap(leftBitmap, (int)(leftBitmap.getWidth()*1f/leftBitmap.getHeight()*height), height, false);
        } else if (rightBitmap.getHeight() != height) {
            tempBitmapR = Bitmap.createScaledBitmap(rightBitmap, (int)(rightBitmap.getWidth()*1f/rightBitmap.getHeight()*height), height, false);
        }

        // 拼接后的宽度
        int width = tempBitmapL.getWidth() + tempBitmapR.getWidth();

        // 定义输出的bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // 缩放后两个bitmap需要绘制的参数
        Rect leftRect = new Rect(0, 0, tempBitmapL.getWidth(), tempBitmapL.getHeight());
        Rect rightRect  = new Rect(0, 0, tempBitmapR.getWidth(), tempBitmapR.getHeight());

        // 右边图需要绘制的位置，往右边偏移左边图的宽度，高度是相同的
        Rect rightRectT  = new Rect(tempBitmapL.getWidth()-dp2px(context,10), 0, width, height);

        canvas.drawBitmap(tempBitmapL, leftRect, leftRect, null);
        canvas.drawBitmap(tempBitmapR, rightRect, rightRectT, null);
        return bitmap;
    }

    private static Bitmap getBitmapFromOneMum(int num,Context context){
        Bitmap bitmap;
        switch (num){
            case 0:
                 bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_0);
                return bitmap;
            case 1:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_1);
                return bitmap;
            case 2:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_2);
                return bitmap;
            case 3:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_3);
                return bitmap;
            case 4:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_4);
                return bitmap;
            case 5:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_5);
                return bitmap;
            case 6:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_6);
                return bitmap;
            case 7:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_7);
                return bitmap;
            case 8:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_8);
                return bitmap;
            case 9:
                bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_9);
                return bitmap;
           default:
               bitmap = BitmapFactory. decodeResource (context.getResources(), R.mipmap.combo_0);
               return bitmap;
        }
    }
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
