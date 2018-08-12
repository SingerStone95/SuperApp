package singerstone.com.superapp.TouchEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/17.
 * YY:909075276
 */

public class TouchEventView extends View {
    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            L.e("TouchEventView:dispatchTouchEvent=" + " ACTION_DOWN");
           // getParent().requestDisallowInterceptTouchEvent(true);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //getParent().requestDisallowInterceptTouchEvent(false);
            L.e("TouchEventView:dispatchTouchEvent=" + " ACTION_MOVE");
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            L.e("TouchEventView:dispatchTouchEvent=" + " ACTION_UP");

        }
        boolean result=super.dispatchTouchEvent(event);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            L.e("TouchEventView:onTouchEvent=" + " ACTION_DOWN");
            result = super.onTouchEvent(event);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            L.e("TouchEventView:onTouchEvent=" + " ACTION_MOVE");
            result = super.onTouchEvent(event);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            L.e("TouchEventView:onTouchEvent=" + " ACTION_UP");
            result = super.onTouchEvent(event);
        }
        L.e("TouchEventView OnTouchEvent return " + result);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String testString = "顶部View";
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(40);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.LEFT);
        Rect bounds = new Rect();
        mPaint.getTextBounds(testString, 0, testString.length(), bounds);
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(testString,getMeasuredWidth() / 2 - bounds.width() / 2, baseline, mPaint);
    }
}
