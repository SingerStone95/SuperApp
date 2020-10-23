package singerstone.com.superapp.upcoming;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private ComingSoonListAdapter comingSoonListAdapter;

    private boolean mFromDrag = false;
    private boolean mHasAddDecoration = false;

    public UpComingFragment() {

    }

    Button btnSetData;

    private SpacesItemHorDecoration mSpaceDecor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        mRecyclerUpComing = view.findViewById(R.id.rv_cominglist);
        btnSetData = view.findViewById(R.id.btn_set_date);
        L.i("BigPosterWidth:" + CommingSoonSizeConst.getBigPosterWidth(getActivity()));
        L.i("MidPosterWidth:" + CommingSoonSizeConst.getMidPosterWidth(getActivity()));
        L.i("SmallPosterWidth:" + CommingSoonSizeConst.getSmallPosterWidth(getActivity()));
        btnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> data = new ArrayList<>();
                data.add("http://puui.qpic.cn/tv/0/148792226_450630/0");
                data.add("http://puui.qpic.cn/tv/0/148585093_450630/0");
                data.add("http://puui.qpic.cn/tv/0/148489788_450630/0");
                data.add("http://puui.qpic.cn/tv/0/147772448_450630/0");
                data.add("http://puui.qpic.cn/tv/0/140907409_450630/0");
                data.add("http://puui.qpic.cn/tv/0/141313509_450630/0");
                data.add("http://puui.qpic.cn/tv/0/146523909_450630/0");
                data.add("http://puui.qpic.cn/tv/0/146523909_450630/0");
                data.add("http://puui.qpic.cn/tv/0/146523909_450630/0");
                comingSoonListAdapter.setData(data);
                mRecyclerUpComing.scrollToPosition(Integer.MAX_VALUE / 2);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        handleScrollEnd();
                    }
                });
            }
        });
        //new StartSnapHelper().attachToRecyclerView(mRecyclerUpComing);
        //mRecyclerUpComing.setLayoutManager(new LooperLayoutManager());
        mSpaceDecor = new SpacesItemHorDecoration(dp2px(getActivity(), 5));

        comingSoonListAdapter = new ComingSoonListAdapter();
        comingSoonListAdapter.setItemClickListener(new ComingSoonListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int realPosition) {
                if (getCurrentFocusPos() != position) {
                    LinearLayoutManager manager = (LinearLayoutManager) mRecyclerUpComing.getLayoutManager();
                    View itemView = manager.findViewByPosition(position);
                    if (itemView != null) {
                        int left = itemView.getLeft();
                        mRecyclerUpComing.smoothScrollBy(left - CommingSoonSizeConst.getLeftOffeset(getContext()), 0);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handleScrollEnd();
                        }
                    }, 100);
                    L.i("click item position=" + realPosition);
                }

            }
        });
        mRecyclerUpComing.setAdapter(comingSoonListAdapter);

        mRecyclerUpComing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    handleDrag();
                }
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!mFromDrag) {
                        return;
                    }
                    mFromDrag = false;
                    mRecyclerUpComing.removeItemDecoration(mSpaceDecor);
                    mHasAddDecoration = false;
                    handleScrollEnd();

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return view;
    }

    private int getCurrentFocusPos() {
        LinearLayoutManager manager = (LinearLayoutManager) mRecyclerUpComing.getLayoutManager();
        return (manager.findFirstVisibleItemPosition() + 1);
    }

    private void adjustPosition(int childIndex) {
        View theSecondView = mRecyclerUpComing.getChildAt(childIndex);
        int left = theSecondView.getLeft();
        int offset = left - CommingSoonSizeConst.getLeftOffeset(getActivity());
        mRecyclerUpComing.smoothScrollBy(offset, 0);
    }

    private void adjustPosition(View childView) {
        //childView.setBackgroundColor(Color.GREEN);
        int left = childView.getLeft();
        int right = childView.getRight();
        int offset = left - CommingSoonSizeConst.getLeftOffeset(getActivity());
        L.i("scroll 之前:offset=" + offset + "   left=" + left + "  right=" + right);
        if (right < 0) {
            mRecyclerUpComing.smoothScrollBy(offset - (CommingSoonSizeConst.getBigPosterWidth(getActivity()) - CommingSoonSizeConst.getSmallPosterWidth(getActivity())), 0);
        } else {
            mRecyclerUpComing.smoothScrollBy(offset, 0);
        }
    }

    private void handleDrag() {
        mFromDrag = true;
        //L.e("SCROLL_STATE_DRAGGING");
        if (!mHasAddDecoration) {
            mRecyclerUpComing.addItemDecoration(mSpaceDecor);
            mHasAddDecoration = true;
        }
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


    private void handleScrollEnd() {
        //移动
        int childIndex = caculateAdjustChildIndex();
        L.i("即将放大的的poster index=:" + childIndex);
        View adjustChildView = mRecyclerUpComing.getChildAt(childIndex);
        IComingSoonItemAnimation galleryAnimation1 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(childIndex);
        if (galleryAnimation1 != null) {
            galleryAnimation1.registerAnimationEndListener(new AnimationEndCallback() {
                @Override
                public void onAnimationEnd() {
                    adjustPosition(adjustChildView);
                }

                @Override
                public void onAnimationFraction(float fraction) {

                }
            });
            galleryAnimation1.changeToBig();
        }

        IComingSoonItemAnimation galleryAnimation2 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(childIndex + 1);
        if (galleryAnimation2 != null) {
            galleryAnimation2.changeToMid();
        }
        IComingSoonItemAnimation galleryAnimation3 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(childIndex + 2);
        if (galleryAnimation3 != null) {
            galleryAnimation3.changeToSmall();
        }
        IComingSoonItemAnimation galleryAnimation0 = (IComingSoonItemAnimation) mRecyclerUpComing.getChildAt(childIndex - 1);
        if (galleryAnimation0 != null) {
            galleryAnimation0.changeToMid();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 0);

    }

    private int caculateAdjustChildIndex() {
        int firstChildRightDistance = mRecyclerUpComing.getChildAt(0).getRight();
        int firstChildWidth = mRecyclerUpComing.getChildAt(0).getWidth();
        if ((firstChildRightDistance > (firstChildWidth / 2)) && (firstChildRightDistance >= CommingSoonSizeConst.getSmallPosterWidth(getActivity()))) {
            return 0;
        } else {
            return 1;
        }
    }


    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
