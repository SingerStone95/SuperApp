package singerstone.com.superapp.waveeffect;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import singerstone.com.superapp.utils.L;

/**
 * 针对横向GridRecycler的滑动控制Helper
 * author : yogachen
 * date   : 2018/10/30下午7:45
 * desc   :
 */
public class MySnapHelper extends PagerSnapHelper {

    private OrientationHelper mHorizontalHelper;


    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
    }

    /**
     * 最终的位置调整
     *
     * @param layoutManager
     * @param targetView
     * @return
     */
    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        L.i("MySnapHelper : invoke calculateDistanceToFinalSnap " + targetView);
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        return out;
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    /**
     * 找到View目标
     *
     * @param layoutManager
     * @return
     */
    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        View view = findCloseLeftView(layoutManager, OrientationHelper.createHorizontalHelper(layoutManager));
        L.i("MySnapHelper : invoke findSnapView：" + view);
        return view;
    }

    private View findCloseLeftView(RecyclerView.LayoutManager layoutManager,
                                   OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        final int left;
        if (layoutManager.getClipToPadding()) {
            //center = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
            left = helper.getStartAfterPadding();
        } else {
            //center = helper.getEnd() / 2;
            left = 0;
        }
        int absClosest = Integer.MAX_VALUE;

        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            // int childCenter = helper.getDecoratedStart(child)
            //         + (helper.getDecoratedMeasurement(child) / 2);
            int childLeft = helper.getDecoratedStart(child);
            int absDistance = Math.abs(childLeft - left);
            /** if child center is closer than previous closest, set it as closest  **/
            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    /**
     * 最先执行,只有fling的时候才会触发
     *
     * @param layoutManager
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        final int itemCount = layoutManager.getItemCount();
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION;
        }
        View mStartMostChildView = findStartView(layoutManager, getHorizontalHelper(layoutManager));
        if (mStartMostChildView == null) {
            return RecyclerView.NO_POSITION;
        }
        final int centerPosition = layoutManager.getPosition(mStartMostChildView);
        L.i("findTargetSnapPosition  centerPosition:" + centerPosition);
        if (centerPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION;
        }
        final boolean forwardDirection;
        if (layoutManager.canScrollHorizontally()) {
            forwardDirection = velocityX > 0;
        } else {
            forwardDirection = velocityY > 0;
        }
        boolean reverseLayout = false;
        if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            RecyclerView.SmoothScroller.ScrollVectorProvider vectorProvider =
                    (RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager;
            PointF vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1);
            if (vectorForEnd != null) {
                reverseLayout = vectorForEnd.x < 0 || vectorForEnd.y < 0;
            }
        }
        return reverseLayout
                ? (forwardDirection ? centerPosition - 3 : centerPosition)
                : (forwardDirection ? centerPosition + 3 : centerPosition);
    }

    /**
     * 当前视野中最左边的Item
     *
     * @param layoutManager
     * @param helper
     * @return
     */
    @Nullable
    private View findStartView(RecyclerView.LayoutManager layoutManager,
                               OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        int startest = Integer.MAX_VALUE;

        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            int childStart = helper.getDecoratedStart(child);

            /** if child is more to start than previous closest, set it as closest  **/
            if (childStart < startest) {
                startest = childStart;
                closestChild = child;
            }
        }
        return closestChild;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
