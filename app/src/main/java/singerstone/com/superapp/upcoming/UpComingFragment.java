package singerstone.com.superapp.upcoming;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.L;

/**
 * author : yogachen
 * date   : 2019-06-26
 * desc   :
 */
public class UpComingFragment extends BaseFragment {

    //高度之比 1：1.08:1.19
    //宽度之比 0.36:7.27:2.59:1
    public static UpComingFragment newInstance() {
        UpComingFragment fragment = new UpComingFragment();
        return fragment;
    }

    private RecyclerView mRecyclerUpComing;
    private ComingAdapter comingAdapter;

    public UpComingFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        mRecyclerUpComing = view.findViewById(R.id.rv_cominglist);
        mRecyclerUpComing.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //new StartSnapHelper().attachToRecyclerView(mRecyclerUpComing);
        //mRecyclerUpComing.setLayoutManager(new LooperLayoutManager());

        //mRecyclerUpComing.addItemDecoration(new SpacesItemHorDecoration(dp2px(getActivity(), 5)));
        comingAdapter = new ComingAdapter();
        List<String> data = new ArrayList<>();
        data.add("http://puui.qpic.cn/tv/0/148792226_450630/0");
        data.add("http://puui.qpic.cn/tv/0/148585093_450630/0");
        data.add("http://puui.qpic.cn/tv/0/148489788_450630/0");
        data.add("http://puui.qpic.cn/tv/0/147772448_450630/0");
        data.add("http://puui.qpic.cn/tv/0/140907409_450630/0");
        data.add("http://puui.qpic.cn/tv/0/141313509_450630/0");
        data.add("http://puui.qpic.cn/tv/0/146523909_450630/0");
        comingAdapter.setData(data);
        mRecyclerUpComing.setAdapter(comingAdapter);
        mRecyclerUpComing.scrollToPosition(Integer.MAX_VALUE / 2);

        mRecyclerUpComing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    L.e("SCROLL_STATE_DRAGGING");
                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            /*        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerUpComing.getLayoutManager();
                    int position = linearLayoutManager.findFirstVisibleItemPosition();
                    L.e("position=" + (position % data.size()));

                    View currentView = linearLayoutManager.findViewByPosition(position);
                    if (currentView != null) {
                        playAnimator(currentView);
                    }
                    View preView = linearLayoutManager.findViewByPosition(position - 1);
                    if (preView != null) {
                        ViewGroup.LayoutParams layoutParams = preView.getLayoutParams();
                        if (layoutParams != null) {
                            preView.getLayoutParams().width = dp2px(getActivity(), 100);
                        }
                    }
                    View afterView = linearLayoutManager.findViewByPosition(position + 1);
                    if (afterView != null) {
                        ViewGroup.LayoutParams layoutParams = afterView.getLayoutParams();
                        if (layoutParams != null) {
                            afterView.getLayoutParams().width = dp2px(getActivity(), 100);
                        }
                    }
                    View afterView2 = linearLayoutManager.findViewByPosition(position + 2);
                    if (afterView2 != null) {
                        ViewGroup.LayoutParams layoutParams = afterView2.getLayoutParams();
                        if (layoutParams != null) {
                            afterView2.getLayoutParams().width = dp2px(getActivity(), 100);
                        }
                    }
                    mRecyclerUpComing.requestLayout();*/
                    //移动
                    L.i("childsize=" + mRecyclerUpComing.getChildCount());
                    doResizeView();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doResizeView();
            }
        }, 0);
        return view;
    }

    private void doResizeView() {
        View theSecondView = mRecyclerUpComing.getChildAt(1);
        int left = theSecondView.getLeft();
        int offset = left - UpCommingSizeConst.getLeftOffeset(getActivity());
        mRecyclerUpComing.smoothScrollBy(offset, 0);
       /* ViewGroup.LayoutParams layoutParams = theSecondView.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = UpCommingSizeConst.getBigPosterWidth(getActivity());
        }
        theSecondView.requestLayout();
        */
        playAnimator(theSecondView);
    }

    private void playAnimator(View view) {
        int currentWidth = view.getLayoutParams().width;
        if (currentWidth >= UpCommingSizeConst.getMidPosterWidth(view.getContext()) || currentWidth >= UpCommingSizeConst.getBasePosterHeight(view.getContext())) {
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofInt(currentWidth, UpCommingSizeConst.getBigPosterWidth(view.getContext()))
                .setDuration(300);
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (view == null || view.getLayoutParams() == null) {
                    return;
                }
                view.getLayoutParams().width = (int) animation.getAnimatedValue();
                view.requestLayout();

            }
        });
        valueAnimator.start();
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
