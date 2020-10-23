package singerstone.com.superapp.backscrollimage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import singerstone.com.superapp.utils.L;

/**
 * Created by zhanghongyang01 on 17/11/23.
 */

public class AdImageView extends ImageView {
    Context mContext;


    public AdImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private int offset = 0;//图片在imageview的偏移量
    private int drawaleHeight = 0;

    /**
     * @param dy        imageview 距离顶部的高度
     * @param direction true:向上滑  false:向下滑
     */
    public void setDyAndDirection(int dy, boolean direction, int heightImageView, int heightListView) {

        /**
         * 默认图片高度大于imageview高度
         */
        if (getDrawable() != null) {
            drawaleHeight = getDrawable().getIntrinsicHeight();
            L.i("FragmentScrollImage", "drawaleHeight" + drawaleHeight);
        }
        L.e(drawaleHeight + " " + heightImageView + " " + heightListView + " " + dy);

        if (direction == true) {
            if ((heightListView - dy) > 0 && (heightListView - dy) < drawaleHeight) { //刚进屏幕
                offset = drawaleHeight - (heightListView - dy);
                offset = -offset;
                // L.i("FragmentScrollImage", "true---->1   " + offset);
            } else {
                offset = 0;
                // L.i("FragmentScrollImage", "true---->3   " + offset);
            }

        } else {
            if (dy < 0) {
                offset = -dy;
                L.i("FragmentScrollImage", "false---->1   " + offset);
            } else if (dy > 0 && dy < (drawaleHeight - heightImageView)) {
                offset = -dy;
                L.i("FragmentScrollImage", "false---->2   " + offset);
            } else if (dy > (drawaleHeight - heightImageView) && dy < (heightListView - heightImageView)) {
                offset = -(drawaleHeight - heightImageView);
                L.i("FragmentScrollImage", "false---->3   " + offset);
            } else {

            }


        }

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        int w = getWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, offset);
        super.onDraw(canvas);
        canvas.restore();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
