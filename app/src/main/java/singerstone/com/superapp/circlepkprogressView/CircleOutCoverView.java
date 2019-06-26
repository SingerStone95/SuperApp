package singerstone.com.superapp.circlepkprogressView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;



import singerstone.com.superapp.R;


/**
 * Created by chenbinhao on 2017/7/20.
 * YY:909075276
 */

public class CircleOutCoverView extends View {
    Context mContex;
    Paint mCirclePaint;
    Paint mCirclePaintOut;
    int mWidth;
    int mHeight;
    float Radius = 0;//半径
    float currentRadius = 0;

    public CircleOutCoverView(Context context) {
        super(context);
        mContex = context;
        initView();
    }

    public CircleOutCoverView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initView();
    }

    private void initView() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(ContextCompat.getColor(mContex, R.color.red));
        mCirclePaint.setAlpha(50);
        mCirclePaintOut = new Paint();
        mCirclePaintOut.setAntiAlias(true);
        mCirclePaintOut.setStyle(Paint.Style.STROKE);
        mCirclePaintOut.setStrokeWidth(dp2px(mContex,2));
        mCirclePaintOut.setColor(ContextCompat.getColor(mContex, R.color.red));
        mCirclePaintOut.setAlpha(60);
        playAnimaton();
    }

    private void playAnimaton(){
        final ValueAnimator animator = ValueAnimator.ofFloat(0.9f, 1.3f);
        animator.setDuration(500);
        animator.setStartDelay(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                currentRadius = Radius * value;
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                  setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(GONE);
                 playAnimaton();
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
        Radius = (mWidth > mHeight ? mHeight : mWidth)/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        canvas.drawCircle(halfWidth, halfHeight, currentRadius, mCirclePaint);
        canvas.drawCircle(halfWidth, halfHeight, currentRadius+(dp2px(mContex,2)/2), mCirclePaintOut);
    }
    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
