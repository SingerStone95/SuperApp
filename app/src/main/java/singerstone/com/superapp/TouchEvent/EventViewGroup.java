package singerstone.com.superapp.TouchEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import singerstone.com.superapp.utils.L;

/**
 * Created by SingerStone on 2017/7/17.
 */

public class EventViewGroup extends RelativeLayout {
    public EventViewGroup(Context context) {
        super(context);
    }


    public EventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEvent();
    }

    private void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("ViewGroup onclick");
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result;
        L.e("EventViewGroup OnTouchEvent  " + " MotionEvent:" + getEventName(event));
        result = super.onTouchEvent(event);
        L.e("EventViewGroup:OnTouchEvent result=" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*L.e("EventViewGroup:onInterceptTouchEvent=" + " MotionEvent:" + getEventName(ev));
        boolean result = super.onInterceptTouchEvent(ev);
        L.e("EventViewGroup:onInterceptTouchEvent result=" + result);*/
        L.e("EventViewGroup:onInterceptTouchEvent=" + " MotionEvent:" + getEventName(ev));
        boolean result=false;
       /* if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            result = false;

        } else if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
            result = false;

        } else {
            result = false;
        }*/

        onTouchEvent(ev);
        L.e("EventViewGroup:onInterceptTouchEvent result=" + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("EventViewGroup:dispatchTouchEvent=" + " MotionEvent:" + getEventName(ev));
        boolean result = super.dispatchTouchEvent(ev);
        L.e("EventViewGroup:dispatchTouchEvent result=" + result);
        return result;
    }

    public static String getEventName(MotionEvent event) {
        String r;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            r = "ACTION_DOWN";
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            r = "ACTION_MOVE";
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            r = "ACTION_UP";
        } else {
            r = "UNKNOWN";
        }
        return r;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String testString = "底部ViewGroup";
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(80);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        Rect bounds = new Rect();
        mPaint.getTextBounds(testString, 0, testString.length(), bounds);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(testString, getMeasuredWidth() / 2 - bounds.width() / 2, baseline, mPaint);
    }
}
