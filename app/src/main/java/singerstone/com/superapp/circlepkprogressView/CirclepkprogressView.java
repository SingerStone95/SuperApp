package singerstone.com.superapp.circlepkprogressView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/19.
 * YY:909075276
 */

public class CirclepkprogressView extends View {

    Context mContex;
    Paint mCirclePaint;
    Paint mCircleInnerPaint;
    Paint mTextPaint_color;
    Paint mTextPaint_white;
    int mWidth = 0;
    int mHeight = 0;
    float mCount = 1;
    String text_color = "";
    String text_white = "";
    RectF mCircleRectF;
    //外部填充圆形半径
    float CIRCLE_RADIUS_EXTER = 0;
    //进度圆形半径
    float CIRCLE_RADIUS = 0;
    int MAX_COUNT = 66;
    int MIN_COUNT = 0;
    int CIRCLE_LINE_WIDTH = 5;
    int TEXT_SIZE = 15;
    //进度条到边缘的距离
    int PADDING = 8;

    public CirclepkprogressView(Context context) {
        super(context);
        init(context);
    }

    public CirclepkprogressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mContex = context;
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(dp2px(context, CIRCLE_LINE_WIDTH));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(context, R.color.orange));

        mCircleInnerPaint = new Paint();
        mCircleInnerPaint.setAntiAlias(true);
        mCircleInnerPaint.setStyle(Paint.Style.FILL);
        mCircleInnerPaint.setColor(ContextCompat.getColor(context, R.color.supergrey));
        mCircleInnerPaint.setAlpha(180);

        mTextPaint_color = new TextPaint();
        mTextPaint_color.setAntiAlias(true);
        mTextPaint_color.setStyle(Paint.Style.FILL);
        mTextPaint_color.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint_color.setColor(ContextCompat.getColor(context, R.color.orange));
        mTextPaint_color.setTextSize(sp2px(context, TEXT_SIZE));

        mTextPaint_white = new TextPaint();
        mTextPaint_white.setAntiAlias(true);
        mTextPaint_white.setStyle(Paint.Style.FILL);
        mTextPaint_white.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint_white.setColor(ContextCompat.getColor(context, R.color.white));
        mTextPaint_white.setTextSize(sp2px(context, TEXT_SIZE));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    public void setCount(int count) {
        if (count > MAX_COUNT) {
            count = MAX_COUNT;
        }
        if (count < MIN_COUNT) {
            count = MIN_COUNT;
        }
        mCount = count;
        text_color = count + "";
        text_white = "/" + MAX_COUNT;

        if (mCount < MAX_COUNT) {
            postInvalidate();
        } else {
            //进度条满了就隐藏字符
            text_white = "";
            text_color = "";
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        //边距=5
        CIRCLE_RADIUS = halfWidth - dp2px(mContex, PADDING);
        CIRCLE_RADIUS_EXTER = halfWidth;
        //画外部分填充圆形
        canvas.drawCircle(halfWidth, halfHeight, CIRCLE_RADIUS_EXTER - dp2px(mContex, CIRCLE_LINE_WIDTH) / 2, mCircleInnerPaint);
        //进度圆形

        if (null == mCircleRectF) {
            mCircleRectF = new RectF(halfWidth - CIRCLE_RADIUS, halfHeight - CIRCLE_RADIUS, halfWidth + CIRCLE_RADIUS, halfHeight + CIRCLE_RADIUS);
        }
        float swipeProgress = mCount / (float) MAX_COUNT * 360;
        mCirclePaint.setColor(ContextCompat.getColor(mContex, R.color.lightgrey));
        mCirclePaint.setAlpha(50);
        canvas.drawArc(mCircleRectF, -90, 360, false, mCirclePaint);
        mCirclePaint.setColor(ContextCompat.getColor(mContex, R.color.orange));
        mCirclePaint.setAlpha(255);
        canvas.drawArc(mCircleRectF, -90, swipeProgress, false, mCirclePaint);
        //画文字
        canvas.drawText(text_color, halfWidth - mTextPaint_color.measureText(text_color + text_white) / 2, halfHeight - (mTextPaint_color.ascent() + mTextPaint_color.descent()) / 2, mTextPaint_color);
        canvas.drawText(text_white, halfWidth - mTextPaint_white.measureText(text_color + text_white) / 2 + mTextPaint_color.measureText(text_color), halfHeight - (mTextPaint_white.ascent() + mTextPaint_white.descent()) / 2, mTextPaint_white);
    }

    public void setMaxCount(int maxCount) {
        this.MAX_COUNT = maxCount;
        setCount((int) mCount);
        postInvalidate();
    }
    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onDetachedFromWindow() {
        L.e("onDetachedFromWindow");
        super.onDetachedFromWindow();
    }
}
