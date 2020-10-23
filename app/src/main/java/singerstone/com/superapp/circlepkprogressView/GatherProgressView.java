package singerstone.com.superapp.circlepkprogressView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;



import singerstone.com.superapp.R;

public class GatherProgressView extends View {

    Context mContex;
    Paint mCirclePaint;
    Paint mCirclePaint_bg;//进度背景
    int mWidth = 0;
    int mHeight = 0;
    int MAX = 100;
    RectF mCircleRectF;
    private float mCount = 0;
    private int resId = -1;

    public GatherProgressView(Context context) {
        super(context);
        mContex = context;
        initPaint();
    }

    public GatherProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initPaint();
    }

    private void initPaint() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth((int) convertDpToPixel(5, mContex));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(mContex, R.color.white));

        mCirclePaint_bg = new Paint();
        mCirclePaint_bg.setAntiAlias(true);
        mCirclePaint_bg.setStrokeWidth((int) convertDpToPixel(5, mContex));
        mCirclePaint_bg.setStyle(Paint.Style.STROKE);
        mCirclePaint_bg.setColor(ContextCompat.getColor(mContex, R.color.gray));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        int CIRCLE_RADIUS = (int) (halfWidth - (int) convertDpToPixel(5, mContex));
        if (null == mCircleRectF) {
            mCircleRectF = new RectF(0, 0, mWidth, mHeight);
        }
        mCircleRectF = new RectF(halfWidth - CIRCLE_RADIUS, halfHeight - CIRCLE_RADIUS, halfWidth + CIRCLE_RADIUS, halfHeight + CIRCLE_RADIUS);
        float swipeProgress = mCount / (float) MAX * 360;
        canvas.drawArc(mCircleRectF, -90, 360, false, mCirclePaint_bg);
        canvas.drawArc(mCircleRectF, -90, swipeProgress, false, mCirclePaint);
        Bitmap bitmap = getBitmap(resId);
        int left = (int) (halfWidth - (bitmap.getWidth() / 2));
        int top = (int) (halfHeight - (bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, left, top, mCirclePaint_bg);
        // canvas.drawBitmap(getBitmap(resId),rect,rect,mCirclePaint);


    }

    private Bitmap getBitmap(int resId) {
        Bitmap bitmap;

        if (resId != -1) {
            bitmap = BitmapFactory.decodeResource(getResources(), resId);

        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        return bitmap;
    }

    public void setProgress(int progress) {
        this.mCount = progress;
        postInvalidate();
    }

    public void setBitmapResourse(int resourseID) {
        this.resId = resourseID;
    }

    public static float convertDpToPixel(float dp, Context context) {

        try {
            if (context == null) {
                return dp;
            }
            Resources resources = context.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            float px = dp * (metrics.densityDpi / 160f);
            return px;
        } catch (Exception ex) {
            Log.e("ResolutionUtils", "Empty Catch on convertDpToPixel", ex);
        }
        return -1;
    }
}