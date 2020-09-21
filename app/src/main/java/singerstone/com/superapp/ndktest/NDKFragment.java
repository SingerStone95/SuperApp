package singerstone.com.superapp.ndktest;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


import android.widget.Toast;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.ndkinterface.NdkInterface;

/**
 * Created by chenbinhao on 2017/12/21. YY:909075276
 */

public class NDKFragment extends BaseFragment {

    public static NDKFragment newInstance() {
        NDKFragment fragment = new NDKFragment();
        return fragment;
    }

    public NDKFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_ndk_test, container, false);
        view.findViewById(R.id.btn_native_cash)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NdkInterface.genCrash();
                        Toast.makeText(getActivity(),
                                NdkInterface.getServiceName()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
        view.findViewById(R.id.btn_malloc_oom)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NdkInterface.genMallocOOM();
                    }
                });
        view.findViewById(R.id.btn_mmap_oom)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NdkInterface.genMmapOOM();
                    }
                });
        return view;
    }
}
