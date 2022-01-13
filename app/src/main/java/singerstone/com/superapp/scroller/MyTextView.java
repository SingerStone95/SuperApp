package singerstone.com.superapp.scroller;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.annotation.RequiresApi;
import singerstone.com.superapp.utils.L;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private int top;//上边界坐标
    private int bottom;//下边界坐标

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new OverScroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void setBoundary(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private float mLastX;
    private float mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setBoundary(0, ((View) getParent()).getBottom());
        float y = event.getY();
        float x = event.getX();
        initVelocityTrackerIfNotExists();
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (x - mLastX);
                int deltaY = (int) (y - mLastY);
                overScrollBy(deltaX, -deltaY, getScrollX(), getScrollY(), 0, 100, 0, 0, true);
                mLastX = x;
                mLastY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int initialVelocity = (int) mVelocityTracker.getYVelocity();
                System.out.println("ACTION_UP: " + initialVelocity + " -- " + mMinimumVelocity);
                if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                    fling(initialVelocity);
                } else if (mScroller.springBack(0, (int) getTranslationY(), 0, 0, top - getTop(),
                        bottom - getBottom())) {
                    postInvalidateOnAnimation();
                }
                recycleVelocityTracker();
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(event);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        scrollTo(scrollX, scrollY);
    }

    public void fling(int velocityY) {
        System.out.println(top + "--" + bottom + " -- " + getTranslationY() + "--" + (top - getTop()) + "--" + (bottom
                - getBottom()));
        int minY = top - getTop();//实际上滑允许滚动的范围 = minY - overY
        int maxY = bottom - getBottom();//实际下滑允许滚动的范围 = maxY + overY
        mScroller.fling(0, (int) getTranslationY(), 0, velocityY, 0, 0, 0, 0, minY, maxY);
        postInvalidateOnAnimation();
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public void computeScroll() {
        L.i("mScroller.computeScrollOffset() = " + mScroller.computeScrollOffset());
        if (mScroller.computeScrollOffset()) {
            L.i("mScroller.getCurrY() = " + mScroller.getCurrY());
            setTranslationY(mScroller.getCurrY());
            postInvalidate();
        }
    }

}