package singerstone.com.superapp.circlepkprogressView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.Nullable;


/**
 * 贡献榜单上榜外部圆环动画View
 * Created by chenbinhao on 2018/1/18.
 * YY:909075276
 */

public class AnimationCircleView extends View {
    private int mWidth = 0;
    private int mHeight = 0;
    private Paint mPaint;
    private float mStrokeWidth = 0;
    private Context mContext;
    private boolean isAnimationPlaying = false;

    public AnimationCircleView(Context context) {
        super(context);
        mContext = context;
        initPaint();
    }

    public AnimationCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//参数抗锯齿
        mPaint.setColor(Color.parseColor("#ffdd00"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(mStrokeWidth / 2, mStrokeWidth / 2, mWidth - mStrokeWidth / 2, mHeight - mStrokeWidth);
        canvas.drawArc(rectF, -90, 360, false, mPaint);
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public void playAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 4f, 0f);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //宽度
                float currentValue = ((Float) animation.getAnimatedValue()).floatValue();
                mStrokeWidth = dp2px(mContext, currentValue);
                mPaint.setStrokeWidth(mStrokeWidth);
                //透明度
                int alpha = (int) (currentValue / 4 * 255);
                mPaint.setAlpha(alpha);
                invalidate();
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimationPlaying = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationPlaying = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isAnimationPlaying = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    public boolean isAnimationPlaying() {
        return isAnimationPlaying;
    }
}
