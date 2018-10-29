package singerstone.com.superapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import singerstone.com.superapp.utils.L;

/**
 * author : yogachen
 * date   : 2018/10/26下午8:21
 * desc   :
 */
public class SuperScrollRecyclerView extends RecyclerView {

    private int totalScrollX = 0;

    public SuperScrollRecyclerView(Context context) {
        super(context);
        init();
    }

    public SuperScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SuperScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    void init() {
        addOnScrollListener(mScrollListener);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        L.i("ddddbug0 velocityX:" + velocityX + "  velocityY:" + velocityY);
        return super.fling(velocityX, velocityY);
    }

    private RecyclerView.OnScrollListener mScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            L.i("ddddbug1 newState:" + newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            totalScrollX = totalScrollX + dx;
            L.i("ddddbug2 onScrolled totalScrollX:" + computeHorizontalScrollOffset());
        }
    };
}
