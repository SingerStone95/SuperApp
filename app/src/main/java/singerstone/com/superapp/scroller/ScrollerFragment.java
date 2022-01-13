package singerstone.com.superapp.scroller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.R;

/**
 * @des:
 * @author: yogachen
 * @date: 2022/1/12 21:07
 * @see {@link }
 */
public class ScrollerFragment extends Fragment {

    public static ScrollerFragment newInstance() {
        ScrollerFragment fragment = new ScrollerFragment();
        return fragment;
    }

    public ScrollerFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_scroller, container, false);
        ViewInject.inject(this, view);
        return view;
    }
}