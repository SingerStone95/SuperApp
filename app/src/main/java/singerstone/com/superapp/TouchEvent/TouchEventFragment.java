package singerstone.com.superapp.TouchEvent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/17.
 * YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class TouchEventFragment extends BaseFragment {

    TouchEventView t_view;
    EventViewGroup g_view;

    public static TouchEventFragment newInstance() {
        TouchEventFragment fragment = new TouchEventFragment();
        return fragment;
    }

    public TouchEventFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_touchevent, container, false);

        g_view = view.findViewById(R.id.g_view);
        g_view.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "click_viewgroup", Toast.LENGTH_SHORT).show();
            L.e("click_viewgroup");
        });
        t_view = view.findViewById(R.id.t_view);
        t_view.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "click_view", Toast.LENGTH_SHORT).show();
            L.e("click_view");
        });
        return view;
    }

}
