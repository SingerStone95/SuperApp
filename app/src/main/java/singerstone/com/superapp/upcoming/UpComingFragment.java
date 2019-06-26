package singerstone.com.superapp.upcoming;

import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
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
        // new StartSnapHelper().attachToRecyclerView(mRecyclerUpComing);
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
        mRecyclerUpComing.scrollToPosition(100);
//        mRecyclerUpComing.scrollToPosition(Integer.MAX_VALUE/2);
        return view;
    }
}
