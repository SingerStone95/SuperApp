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

    private boolean mFromDrag = false;

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
                    mFromDrag = true;
                    L.e("SCROLL_STATE_DRAGGING");
                    IGalleryAnimation galleryAnimation0 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(0);
                    if (galleryAnimation0 != null) {
                        galleryAnimation0.changeToSmall();
                    }

                    IGalleryAnimation galleryAnimation2 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(2);
                    if (galleryAnimation2 != null) {
                        galleryAnimation2.changeToSmall();
                    }
                    IGalleryAnimation galleryAnimation3 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(3);
                    if (galleryAnimation3 != null) {
                        galleryAnimation3.changeToSmall();
                    }
                    mRecyclerUpComing.requestLayout();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!mFromDrag) {
                        return;
                    }
                    mFromDrag = false;
                    //移动
                    IGalleryAnimation galleryAnimation0 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(0);
                    if (galleryAnimation0 != null) {
                        galleryAnimation0.changeToMid();
                    }
                    IGalleryAnimation galleryAnimation1 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(1);
                    if (galleryAnimation1 != null) {
                        galleryAnimation1.changeToBig();
                    }
                    IGalleryAnimation galleryAnimation2 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(2);
                    if (galleryAnimation2 != null) {
                        galleryAnimation2.changeToMid();
                    }
                    IGalleryAnimation galleryAnimation3 = (IGalleryAnimation) mRecyclerUpComing.getChildAt(3);
                    if (galleryAnimation3 != null) {
                        galleryAnimation3.changeToSmall();
                    }
                    mRecyclerUpComing.requestLayout();
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            adjustPosition();
                        }
                    });

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;
    }

    private void adjustPosition() {
        View theSecondView = mRecyclerUpComing.getChildAt(1);
        int left = theSecondView.getLeft();
        int offset = left - UpCommingSizeConst.getLeftOffeset(getActivity());
        mRecyclerUpComing.smoothScrollBy(offset, 0);
    }


    private void playAnimator(View view) {
        int currentWidth = view.getLayoutParams().width;
        if (currentWidth > UpCommingSizeConst.getMidPosterWidth(view.getContext()) || currentWidth > UpCommingSizeConst.getSmallPosterHeight(view.getContext())) {
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
