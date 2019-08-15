package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * author : yogachen
 * date   : 2019-07-10
 * desc   :
 */
public class SmoothControlRecyclerView extends RecyclerView {
    public SmoothControlRecyclerView(Context context) {
        super(context);
        init();
    }

    public SmoothControlRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayoutManager(new SpeedyLinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public class SpeedyLinearLayoutManager extends LinearLayoutManager {

        private static final float MILLISECONDS_PER_INCH = 5f; //default is 25f (bigger = slower)
        private static final float MANUAL_SCROLL_SLOW_RATIO = 1f;

        public SpeedyLinearLayoutManager(Context context) {
            super(context);
        }

        public SpeedyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public SpeedyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }


        @Override
        public int scrollHorizontallyBy(int delta, RecyclerView.Recycler recycler, RecyclerView.State state) {
            int prevDelta = delta;
            if (getScrollState() == SCROLL_STATE_DRAGGING)
                delta = (int) (delta > 0 ? Math.max(delta * MANUAL_SCROLL_SLOW_RATIO, 1) : Math.min(delta * MANUAL_SCROLL_SLOW_RATIO, -1));

            // MANUAL_SCROLL_SLOW_RATIO is between 0 (no manual scrolling) to 1 (normal speed) or more (faster speed).
            // write your scrolling logic code here whereby you move each view by the given delta

            /*if (getScrollState() == SCROLL_STATE_DRAGGING)
                delta = prevDelta;
*/
            return delta;
        }


    }
    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityX *= 0.5;
        // velocityX *= 0.7; for Horizontal recycler view. comment velocityY line not require for Horizontal Mode.

        return super.fling(velocityX, velocityY);
    }
}
