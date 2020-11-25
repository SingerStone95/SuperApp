package singerstone.com.superapp.xposed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.R;

public class XposedFragment extends Fragment {

    public static XposedFragment newInstance() {
        XposedFragment fragment = new XposedFragment();
        return fragment;
    }

    public XposedFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_xposed, container, false);
        ViewInject.inject(this, view);
        return view;
    }
}
