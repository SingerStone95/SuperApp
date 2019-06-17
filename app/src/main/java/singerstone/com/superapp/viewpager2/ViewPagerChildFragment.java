package singerstone.com.superapp.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import singerstone.com.superapp.R;

/**
 * author : yogachen
 * date   : 2019-06-17
 * desc   :
 */
public class ViewPagerChildFragment extends Fragment {
    TextView textView;

    public static ViewPagerChildFragment newInstance() {
        ViewPagerChildFragment fragment = new ViewPagerChildFragment();
        return fragment;
    }

    public ViewPagerChildFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager2, container, false);
        textView = view.findViewById(R.id.tv_position);
        return view;
    }

    public void setPosition(int position) {
        textView.setText(position + "");
    }
}
