package singerstone.com.superapp.Accessbility;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import androidx.annotation.Nullable;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;

/**
 * Created by chenbinhao on 2017/7/31.
 * YY:909075276
 */

public class AccessbilityFragmrnt extends BaseFragment {

    int progress = 1;
    private Button btn;
    private ProgressBar team_gather_count_progressbar;

    // TODO: 2017/7/5 在这里设置传值
    public static AccessbilityFragmrnt newInstance() {
        AccessbilityFragmrnt fragment = new AccessbilityFragmrnt();
        return fragment;
    }

    public AccessbilityFragmrnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accessbility, container, false);
        team_gather_count_progressbar = (ProgressBar) view.findViewById(R.id.team_gather_count_progressbar);
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress++;
                team_gather_count_progressbar.setProgress(progress);
            }
        });
        return view;
    }

}
