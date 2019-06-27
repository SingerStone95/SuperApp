package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.os.Bundle;
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
    int currentPOsition;

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
        // mRecyclerUpComing.setLayoutManager(new LooperLayoutManager());
        new StartSnapHelper().attachToRecyclerView(mRecyclerUpComing);
        mRecyclerUpComing.addItemDecoration(new SpacesItemHorDecoration(dp2px(getActivity(), 5)));
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
        //mRecyclerUpComing.scrollToPosition(Integer.MAX_VALUE/2);
        mRecyclerUpComing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerUpComing.getLayoutManager();
                    int position = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    L.e("position=" + (position % data.size()));
                    View currentView = linearLayoutManager.findViewByPosition(position);
                    View preView = linearLayoutManager.findViewByPosition(position - 1);
                    if (preView != null) {
                        ViewGroup.LayoutParams layoutParams = preView.getLayoutParams();
                        if (layoutParams != null) {
                            preView.getLayoutParams().width = dp2px(getActivity(), 100);
                            preView.requestLayout();
                        }
                    }
                    if (currentView != null) {

                        ViewGroup.LayoutParams layoutParams = currentView.getLayoutParams();
                        if (layoutParams != null) {
                            currentView.getLayoutParams().width = dp2px(getActivity(), 200);
                            currentView.requestLayout();
                        }
                    }

                    View afterView = linearLayoutManager.findViewByPosition(position + 1);
                    if (afterView != null) {
                        ViewGroup.LayoutParams layoutParams = afterView.getLayoutParams();
                        if (layoutParams != null) {
                            afterView.getLayoutParams().width = dp2px(getActivity(), 100);
                            afterView.requestLayout();
                        }
                    }
                    View afterView2 = linearLayoutManager.findViewByPosition(position + 2);
                    if (afterView2 != null) {
                        ViewGroup.LayoutParams layoutParams = afterView2.getLayoutParams();
                        if (layoutParams != null) {
                            afterView2.getLayoutParams().width = dp2px(getActivity(), 100);
                            afterView2.requestLayout();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;
    }


    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
