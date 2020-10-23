package singerstone.com.superapp.waveeffect;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import singerstone.com.superapp.R;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/14.
 * YY:909075276
 */

public class WaveView extends View {

    int mWidth;
    int mHeight;
    Paint textPaint;
    Paint drawPaint;
    float currentOffect = 0;
    private int lastX;
    private int lastY;
    int color;
    int size;
    String text;
    ValueAnimator animator;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public WaveView(Context context) {
        super(context);
        initDraw(null, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initDraw(attrs, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initDraw(AttributeSet attrs, Context context) {
        //获取参数
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WaveView);
        //自定义颜色和文字
        color = array.getColor(R.styleable.WaveView_wave_color, Color.rgb(41, 163, 254));
        text = array.getString(R.styleable.WaveView_wave_text);
        size = array.getInteger(R.styleable.WaveView_wave_size, 10);
        array.recycle();
        //文字
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//参数抗锯齿
        textPaint.setColor(color);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//参数抗锯齿
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(color);
        drawPaint.setDither(true);//防抖动
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0, 1);
        }
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentOffect = animation.getAnimatedFraction();
                invalidate();
            }
        });

        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = size;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = size;
        }
        textPaint.setTextSize(mWidth / 2);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 写字->裁剪画布->填充裁剪部分->写字
         */
        L.e("是否硬件加速:" + canvas.isHardwareAccelerated());
        textPaint.setColor(color);
        drawCenterText(canvas, textPaint, text, mWidth, mHeight);
        int r = mWidth < mHeight ? mWidth / 2 : mHeight / 2;
        ///////////////////////////
        Path circle = new Path();
        circle.addCircle(mWidth / 2, mHeight / 2, r, Path.Direction.CCW);
        canvas.clipPath(circle);
        /////////////////////////////
        Path path = getWavePath(0.5f, currentOffect);
        canvas.clipPath(path);
        //////////////////////////////
        canvas.drawPath(path, drawPaint);
        textPaint.setColor(Color.WHITE);
        drawCenterText(canvas, textPaint, text, mWidth, mHeight);
    }

    /**
     * @param progress 进度
     * @param offset   水平偏移量
     * @return 裁剪的路径
     */
    private Path getWavePath(float progress, float offset) {
        Path wavePath = new Path();
        int height = (int) (progress * mHeight);
        wavePath.moveTo(-mWidth * offset, height);
        wavePath.rQuadTo(mWidth / 4, mWidth / 10, mWidth / 2, 0);
        wavePath.rQuadTo(mWidth / 4, -mWidth / 10, mWidth / 2, 0);
        wavePath.rQuadTo(mWidth / 4, mWidth / 10, mWidth / 2, 0);
        wavePath.rQuadTo(mWidth / 4, -mWidth / 10, mWidth / 2, 0);
        wavePath.lineTo(2 * mWidth, mHeight);
        wavePath.lineTo(0, mHeight);
        wavePath.close();
        return wavePath;
    }

    private void drawCenterText(Canvas canvas, Paint textPaint, String text, int width, int height) {
        Rect rect = new Rect(0, 0, width, height);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int centerY = (int) (rect.centerY() - top / 2 - bottom / 2);
        canvas.drawText(text, rect.centerX(), centerY, textPaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offX = x - lastX;
                int offY = y - lastY;
                //调用layout方法来重新放置它的位置
                layout(getLeft() + offX, getTop() + offY,
                        getRight() + offX, getBottom() + offY);
                break;
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }
}
