package singerstone.com.superapp.like;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;

/**
 * Created by chenbinhao on 2017/10/30.
 * YY:909075276
 */

public class LikeViewFragment extends BaseFragment {

    LikeTextView tv_up;

    // TODO: 2017/7/5 在这里设置传值
    public static LikeViewFragment newInstance() {
        LikeViewFragment fragment = new LikeViewFragment();
        return fragment;
    }

    public LikeViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likeview, container, false);
        tv_up = (LikeTextView) view.findViewById(R.id.tv_up);
        tv_up.setNum(0);
        return view;
    }
}
