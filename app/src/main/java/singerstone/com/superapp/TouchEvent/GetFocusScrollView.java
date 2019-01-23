package singerstone.com.superapp.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import singerstone.com.superapp.utils.L;

/**
 * author : yogachen
 * date   : 2019/1/23下午12:07
 * desc   :
 */
public class GetFocusScrollView extends ScrollView {


    private int reachToBottom = 0;
    private int reachToTop = 1;

    private int direct = 0; //0未知 1上 2下

    public GetFocusScrollView(Context context) {
        super(context);
    }

    public GetFocusScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (ev.getAction() == 3) {
            direct = 0;
            reachToBottom = -1;
            reachToBottom = -1;
        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        L.i("yogachen", ev.getAction() + "");
        boolean result = super.onTouchEvent(ev);
        if (reachToTop == 1) {
            if (direct == 2) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        if (reachToBottom == 1) {
            if (direct == 1) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        return result;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t - oldt > 0) {
            direct = 1;
        } else {
            direct = 2;
        }
        handleScrollState();
        super.onScrollChanged(l, t, oldl, oldt);
    }

    private void handleScrollState() {
        View view = getChildAt(getChildCount() - 1);
        int diff = (view.getBottom() - (getHeight() + getScrollY()));
        if (diff == 0) {
            reachToBottom = 1;
        } else {
            reachToBottom = 0;
        }
        if (getScrollY() == 0) {
            reachToTop = 1;
        } else {
            reachToTop = 0;
        }
    }
}
