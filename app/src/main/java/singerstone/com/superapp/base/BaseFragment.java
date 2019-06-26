package singerstone.com.superapp.base;


import android.support.v4.app.Fragment;

import com.squareup.leakcanary.RefWatcher;

import singerstone.com.superapp.SuperApp;

/**
 * Created by chenbinhao on 2018/6/5.
 * YY:909075276
 */

public class BaseFragment extends Fragment {
    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = SuperApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
