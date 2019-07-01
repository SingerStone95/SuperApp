package singerstone.com.superapp.upcoming;

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
    private ComingSoonListAdapter comingSoonListAdapter;

    private boolean mFromDrag = false;

    public UpComingFragment() {

    }

    private SpacesItemHorDecoration mSpaceDecor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        mRecyclerUpComing = view.findViewById(R.id.rv_cominglist);
        mRecyclerUpComing.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //new StartSnapHelper().attachToRecyclerView(mRecyclerUpComing);
        //mRecyclerUpComing.setLayoutManager(new LooperLayoutManager());
        mSpaceDecor = new SpacesItemHorDecoration(dp2px(getActivity(), 5));

        comingSoonListAdapter = new ComingSoonListAdapter();
        List<String> data = new ArrayList<>();
        data.add("http://puui.qpic.cn/tv/0/148792226_450630/0");
        data.add("http://puui.qpic.cn/tv/0/148585093_450630/0");
        data.add("http://puui.qpic.cn/tv/0/148489788_450630/0");
        data.add("http://puui.qpic.cn/tv/0/147772448_450630/0");
        data.add("http://puui.qpic.cn/tv/0/140907409_450630/0");
        data.add("http://puui.qpic.cn/tv/0/141313509_450630/0");
        data.add("http://puui.qpic.cn/tv/0/146523909_450630/0");
        comingSoonListAdapter.setData(data);
        mRecyclerUpComing.setAdapter(comingSoonListAdapter);
        mRecyclerUpComing.scrollToPosition(Integer.MAX_VALUE / 2);
        mRecyclerUpComing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    mFromDrag = true;
                    //L.e("SCROLL_STATE_DRAGGING");
                    mRecyclerUpComing.addItemDecoration(mSpaceDecor);
                    IComingSoonItemAnimation galleryAnimation0 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(0);
                    if (galleryAnimation0 != null) {
                        galleryAnimation0.changeToSmall();
                    }

                    IComingSoonItemAnimation galleryAnimation2 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(2);
                    if (galleryAnimation2 != null) {
                        galleryAnimation2.changeToSmall();
                    }
                    IComingSoonItemAnimation galleryAnimation3 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(3);
                    if (galleryAnimation3 != null) {
                        galleryAnimation3.changeToSmall();
                    }
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!mFromDrag) {
                        return;
                    }
                    mFromDrag = false;
                    mRecyclerUpComing.removeItemDecoration(mSpaceDecor);
                    handleScrollEnd();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                handleScrollEnd();
            }
        });

        return view;
    }

    private void adjustPosition() {
        View theSecondView = mRecyclerUpComing.getChildAt(1);
        int left = theSecondView.getLeft();
        int offset = left - CommingSoonSizeConst.getLeftOffeset(getActivity());
        mRecyclerUpComing.smoothScrollBy(offset, 0);
    }


    private void handleScrollEnd() {
        //移动
        IComingSoonItemAnimation galleryAnimation0 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(0);
        if (galleryAnimation0 != null) {
            galleryAnimation0.changeToMid();
        }
        IComingSoonItemAnimation galleryAnimation1 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(1);
        if (galleryAnimation1 != null) {
            galleryAnimation1.changeToBig();
        }
        IComingSoonItemAnimation galleryAnimation2 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(2);
        if (galleryAnimation2 != null) {
            galleryAnimation2.changeToMid();
        }
        IComingSoonItemAnimation galleryAnimation3 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(3);
        if (galleryAnimation3 != null) {
            galleryAnimation3.changeToSmall();
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adjustPosition();
            }
        });

    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
