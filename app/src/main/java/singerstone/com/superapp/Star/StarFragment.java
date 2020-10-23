package singerstone.com.superapp.Star;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.text.TextContainer;
import singerstone.com.superapp.utils.SpannableBuilder;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class StarFragment extends BaseFragment {
    int count = 0;
    StarViewContainer viewContainer;
    TextContainer textContainer;
    private Button btn_send;

    // TODO: 2017/7/5 在这里设置传值
    public static StarFragment newInstance() {
        StarFragment fragment = new StarFragment();
        return fragment;
    }

    public StarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_animstar, container, false);
        viewContainer = view.findViewById(R.id.view_container);
        textContainer = (TextContainer) view.findViewById(R.id.text_container);
        btn_send = (Button) view.findViewById(R.id.send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewContainer.setVisibility(View.VISIBLE);
                textContainer.setVisibility(View.VISIBLE);
                count++;
                viewContainer.playAnimation(count);
                textContainer.showContainer();
                textContainer.setShow(true);//控制显示动画只播放一次
                textContainer.setShowText(SpannableBuilder.create(getActivity())
                        .append("小石头...", R.dimen.sp_16, R.color.color_ffdd00)
                        .append("收到打赏", R.dimen.sp_14, R.color.white)
                        .build());
            }
        });
        viewContainer.setAnimationFinishedCallback(new StarViewContainer.OnceAnimationFinishedCallback() {
            @Override
            public void onAnimationFinished() {
                viewContainer.setVisibility(View.GONE);
                textContainer.setVisibility(View.GONE);
                textContainer.setShow(false);
            }
        });

        return view;
    }

}
