package singerstone.com.superapp.circlepkprogressView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2017/7/20.
 * YY:909075276
 */

public class PKCoolingLayer extends View {
    Context mContex;
    Paint mCirclePaint;
    int mWidth;
    int mHeight;
    RectF mCircleRectF;
    float CIRCLE_RADIUS = 0;
    int PADDING = 10;
    float mAngle = 0;

    public void setOnCountDownFinishedListener(OnCountDownFinishedListener onCountDownFinishedListener) {
        this.onCountDownFinishedListener = onCountDownFinishedListener;
    }

    OnCountDownFinishedListener onCountDownFinishedListener;

    public PKCoolingLayer(Context context) {
        super(context);
        initView(context);
    }

    public PKCoolingLayer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContex = context;
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(ContextCompat.getColor(context, R.color.supergrey));
        mCirclePaint.setAlpha(150);
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public void startCountDown() {
        final ValueAnimator animator = ValueAnimator.ofFloat(360, 0);
        animator.setDuration(15000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mAngle=value;
                postInvalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (onCountDownFinishedListener!=null){
                    onCountDownFinishedListener.onCountDownFinished();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
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
        CIRCLE_RADIUS = halfWidth - dp2px(mContex, PADDING);
        if (null == mCircleRectF) {
            mCircleRectF = new RectF(halfWidth - CIRCLE_RADIUS, halfHeight - CIRCLE_RADIUS, halfWidth + CIRCLE_RADIUS, halfHeight + CIRCLE_RADIUS);
        }
        canvas.drawArc(mCircleRectF, -90+(360-mAngle), mAngle, true, mCirclePaint);
    }
    public interface OnCountDownFinishedListener{
         void onCountDownFinished();
    }

}
