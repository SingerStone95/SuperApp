package singerstone.com.superapp.waveeffect;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;

/**
 * Created by chenbinhao on 2017/7/8.
 * YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class WaveFragment extends BaseFragment {
    // TODO: 2017/7/5 在这里设置传值
    public static WaveFragment newInstance() {
        WaveFragment fragment = new WaveFragment();
        return fragment;
    }

    public WaveFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wave, container, false);

        return view;
    }
}
