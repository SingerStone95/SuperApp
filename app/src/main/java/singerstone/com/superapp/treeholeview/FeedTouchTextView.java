package singerstone.com.superapp.treeholeview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class FeedTouchTextView extends android.support.v7.widget.AppCompatTextView {

    private Spannable mSpannable;

    private PressableClickSpan mDownSpan;

    private float mTouchSlop;
    private long mLongPressTimeout;

    private float mBeginX;
    private float mBeginY;

    private Handler mHandler;
    private Runnable mStopRunnable;
    private OnClickListener mOnClickListener;


    public FeedTouchTextView(Context context) {
        super(context);
        init(context);
    }

    public FeedTouchTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FeedTouchTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mLongPressTimeout = ViewConfiguration.getLongPressTimeout();
        mHandler = new Handler(Looper.getMainLooper());
        mStopRunnable = new StopRunnable();
    }



    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (text instanceof Spannable) {
            mSpannable = (Spannable) text;
        } else {
            mSpannable = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.setOnClickListener(mOnClickListener);
        int action = event.getAction();
        if (mSpannable == null) {
            if (action == MotionEvent.ACTION_DOWN) {
                setStateBg();
            }
            return super.onTouchEvent(event);
        }
        if (action == MotionEvent.ACTION_DOWN) {
            PressableClickSpan span = getClickableSpan(event);
            if (span != null) {
                span.setPressed(true);
                int spanStart = mSpannable.getSpanStart(span);
                int spanEnd = mSpannable.getSpanEnd(span);
                Selection.setSelection(mSpannable, spanStart, spanEnd);
                setTransparentBg();
                mDownSpan = span;
                mBeginX = event.getX();
                mBeginY = event.getY();
                mHandler.removeCallbacks(mStopRunnable);
                mHandler.postDelayed(mStopRunnable, mLongPressTimeout);
            } else {
                setStateBg();
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            PressableClickSpan span = getClickableSpan(event);
            if (mDownSpan != null && span != mDownSpan) {
                mDownSpan.setPressed(false);
                mDownSpan = null;
                Selection.removeSelection(mSpannable);
                setStateBg();
                mHandler.removeCallbacks(mStopRunnable);
            }
        } else {
            if (mDownSpan != null) {
                mDownSpan.setPressed(false);
                Selection.removeSelection(mSpannable);
                if (canPerformClick(mBeginX, mBeginY, event)) {
                    super.setOnClickListener(null);
                    mDownSpan.onClick(this);
                }
                mDownSpan = null;
                setStateBg();
                mHandler.removeCallbacks(mStopRunnable);
            }
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    private boolean canPerformClick(float beginX, float beginY, MotionEvent endEvent) {
        return Math.abs(beginX - endEvent.getX()) < mTouchSlop && Math.abs(beginY - endEvent.getY()) < mTouchSlop;
    }

    @Nullable
    protected PressableClickSpan getClickableSpan(MotionEvent event) {
        // bugid=65687199
        try {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= getTotalPaddingLeft();
            y -= getTotalPaddingTop();

            x += getScrollX();
            y += getScrollY();

            Layout layout = getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] link = mSpannable.getSpans(off, off, ClickableSpan.class);
            ClickableSpan span = link != null && link.length != 0 ? link[0] : null;
            return span instanceof PressableClickSpan ? (PressableClickSpan) span : null;
        } catch (Exception e) {
            return null;
        }
    }

    private void setTransparentBg() {
        setBackgroundColor(Color.TRANSPARENT);
    }

    private void setStateBg() {
//        setBackgroundResource(mBackgroundResId);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOnClickListener = l;
        super.setOnClickListener(l);
    }

    private class StopRunnable implements Runnable {

        @Override
        public void run() {
            if (mDownSpan != null) {
                mDownSpan.setPressed(false);
                Selection.removeSelection(mSpannable);
                setStateBg();
                mDownSpan = null;
                Selection.removeSelection(mSpannable);
            }
        }
    }
}
