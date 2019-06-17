package singerstone.com.superapp.circlepkprogressView;

import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/7/19.
 * YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class PKAnimationFragment extends BaseFragment {

    CirclepkAnimationView circlepkAnimationView;
    SendGiftCoodingView mSendGiftCoodingView;
    AnimationCircleView mAnimationCircleView;
    int count = 0;
    Button btn;
    ImageView img_gift;
    Button btn_reset;

    // TODO: 2017/7/5 在这里设置传值
    public static PKAnimationFragment newInstance() {
        PKAnimationFragment fragment = new PKAnimationFragment();
        return fragment;
    }

    public PKAnimationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pkanimation, container, false);
        btn = (Button) view.findViewById(R.id.btn);
        mAnimationCircleView = (AnimationCircleView) view.findViewById(R.id.av_anim);
        circlepkAnimationView = (CirclepkAnimationView) view.findViewById(R.id.circle_progress_animation_view);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                circlepkAnimationView.setCurrentGiftCount(count);

                mAnimationCircleView.playAnimation();
            }
        });
        mSendGiftCoodingView = (SendGiftCoodingView) view.findViewById(R.id.sgcv);
        mSendGiftCoodingView.setTime(30, 30);

        view.findViewById(R.id.img_gift).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        });
        return view;
    }


}

