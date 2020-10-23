package singerstone.com.superapp.circlepkprogressView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;



import singerstone.com.superapp.R;


/**
 * Created by chenbinhao on 2017/11/17.
 * YY:909075276
 * 送表情冷却倒计时View
 */

public class SendGiftCoodingView extends View {
    private Context mContext;
    private int mWidth = 0;
    private int mHeight = 0;
    private Paint mBackgroundPaint;
    private Paint mCirclePaint;
    private TextPaint mTextPaint;
    private static final int CIRCLE_LINE_WIDTH = 5;
    private static final int TEXT_SIZE = 4;
    private float progress = 0f;
    private int currentTime = 0;
    private int maxTime;

    public SendGiftCoodingView(Context context) {
        super(context);
        mContext = context;
        initPaint();
    }

    public SendGiftCoodingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        setVisibility(GONE);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(ContextCompat.getColor(mContext, R.color.black));
        mBackgroundPaint.setAlpha(160);
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setColor(ContextCompat.getColor(mContext, R.color.white));
        mTextPaint.setTextSize(sp2px(mContext, sp2px(mContext, TEXT_SIZE)));
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(dp2px(mContext, CIRCLE_LINE_WIDTH));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(mContext, R.color.black));
        mCirclePaint.setAlpha(160);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        float bkgRadius = (halfWidth - dp2px(mContext, CIRCLE_LINE_WIDTH)) + 1;
        RectF bkgRectF = new RectF(halfWidth - bkgRadius, halfHeight - bkgRadius, halfWidth + bkgRadius, halfHeight + bkgRadius);
        canvas.drawArc(bkgRectF, 0, 360, true, mBackgroundPaint);
        float circleRadius = (halfWidth - dp2px(mContext, CIRCLE_LINE_WIDTH) / 2);
        RectF circleRectF = new RectF(halfWidth - circleRadius, halfHeight - circleRadius, halfWidth + circleRadius, halfHeight + circleRadius);
        float swipeProgress = progress * 360;
        canvas.drawArc(circleRectF, -90, -swipeProgress, false, mCirclePaint);
        canvas.drawText(String.valueOf(currentTime) + "s", halfWidth - mTextPaint.measureText(String.valueOf(currentTime) + "s") / 2, halfHeight - (mTextPaint.ascent() + mTextPaint.descent()) / 2, mTextPaint);
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public void setTime(int currentTime, int maxTime) {
        this.maxTime = maxTime;
        this.currentTime = currentTime;
        this.progress = (float) currentTime / (float) maxTime;
        startCountDown();
        this.setVisibility(VISIBLE);
    }

    Handler mHandler=new Handler();
    private void startCountDown() {
        mHandler.postDelayed(countDown, 1000);
    }

    Runnable countDown = new Runnable() {
        @Override
        public void run() {
            if (currentTime > 0) {
                currentTime--;
                progress = (float) currentTime / (float) maxTime;
                invalidate();
                mHandler.postDelayed(countDown, 1000);
            } else {
                if (mOnCoodingFinishedListener!=null){
                    mOnCoodingFinishedListener.onCoodingFinished();
                }
                setVisibility(GONE);
                mHandler.removeCallbacks(countDown);
            }

        }
    };
    OnCoodingFinishedListener mOnCoodingFinishedListener;

    /**
     * 冷却结束回调
     */
    public interface OnCoodingFinishedListener {
        void onCoodingFinished();
    }

    public void setOnCoodingFinishedListener(OnCoodingFinishedListener onCoodingFinishedListener) {
        mOnCoodingFinishedListener = onCoodingFinishedListener;
    }

    /**
     * 保险起见，还是调用这个清除Handler
     */
    public void clearTimer() {
        mHandler.removeCallbacks(countDown);
    }


}
