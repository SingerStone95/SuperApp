package singerstone.com.superapp.backscrollimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/12/6.
 * YY:909075276
 */

public class FragmentScrollImage extends BaseFragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private int itemHeight = 0;

    public static FragmentScrollImage newInstance() {
        FragmentScrollImage fragment = new FragmentScrollImage();
        return fragment;
    }

    public FragmentScrollImage() {

    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll_image, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            mockDatas.add(i + "");
        }

        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(new AdAdapter(getActivity(), mockDatas));


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastVisibleItemPosition();
                // L.i("FragmentScrollImage", "可见position:" + fPos + "  " + lPos);
                if (dy < 0) {//下滑
                    for (int i = fPos; i <= lPos; i++) {
                        View view = mLinearLayoutManager.findViewByPosition(i);
                        AdImageView adImageView = (AdImageView) view.findViewById(R.id.id_iv_ad);
                        if (adImageView.getVisibility() == View.VISIBLE) {
                            adImageView.setDyAndDirection(view.getTop(), false, view.getHeight(), mRecyclerView.getHeight());
                        }
                    }
                } else {
                    for (int i = fPos; i <= lPos; i++) {
                        View view = mLinearLayoutManager.findViewByPosition(i);
                        AdImageView adImageView = (AdImageView) view.findViewById(R.id.id_iv_ad);
                        if (adImageView.getVisibility() == View.VISIBLE) {
                            L.e("FragmentScrollImage mLinearLayoutManager.getHeight():" + view.getHeight() + "   view.getTop():" + view.getTop());
                            adImageView.setDyAndDirection(view.getTop(), true, view.getHeight(), mRecyclerView.getHeight());
                        }
                    }
                }

            }
        });
        return view;
    }
}
