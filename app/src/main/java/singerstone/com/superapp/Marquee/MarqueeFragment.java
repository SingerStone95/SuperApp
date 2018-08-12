package singerstone.com.superapp.Marquee;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.FaceBoomBitmapCreater;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/9/6.
 * YY:909075276
 */

public class MarqueeFragment extends BaseFragment {
    private MarqueeLayout mMarqueLayout;
    private RelativeLayout mMarqueeBgLayout;
    private int mMaxSeatWidth = 0;
    ImageView iv_num;
    GifImageView gv_animation;
    public final static float MAX_WIDTH = 500f;

    public static MarqueeFragment newInstance() {
        MarqueeFragment fragment = new MarqueeFragment();
        return fragment;
    }

    public MarqueeFragment() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        L.e("onCreateView");
        handler.sendEmptyMessageDelayed(1, 1000 * 60 * 10);
        View view = inflater.inflate(R.layout.fragment_marquee, container, false);
        mMarqueLayout = (MarqueeLayout) view.findViewById(R.id.song_boss_marque);
        mMarqueLayout.setType(MarqueeLayout.offsetType.Need);
        mMaxSeatWidth = (int) ResolutionUtils.convertDpToPixel(MAX_WIDTH, getActivity());
        mMarqueLayout.setmMaxSeatWidth((int) MAX_WIDTH);
        String nick = "nick";
        String songName = "天天都需要你爱";
        final SpannableString ss = new SpannableString("正在为" + nick + "唱" + songName);
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 3, 3 + nick.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);  //粗体
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 3 + nick.length() + 1, 3 + nick.length() + 1 + songName.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);  //粗体
        mMarqueLayout.setText(ss);
        iv_num = (ImageView) view.findViewById(R.id.img_num);
        iv_num.setImageBitmap(FaceBoomBitmapCreater.createBitmapNum(getActivity(), 1234));
        gv_animation = (GifImageView) view.findViewById(R.id.gv_animation);
        try {
            GifDrawable gifFromAssets = new GifDrawable(getActivity().getAssets(), "bidong.gif");
            gv_animation.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

}
