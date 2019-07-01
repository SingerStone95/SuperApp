package singerstone.com.superapp.upcoming;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import singerstone.com.superapp.R;

public class ComingSoonItemView extends LinearLayout implements IComingSoonItemAnimation {

    public RelativeLayout mRlRoot;
    public ImageView mImageView;
    public TextView mTvIndex;
    private int uiState = IComingSoonItemAnimation.STATE_MID;

    public static final int TYPE_HEIGHT = 1;
    public static final int TYPE_WIDTH = 2;

    public static int ANIMATOR_DURATION = 300;

    public ComingSoonItemView(Context context) {
        super(context);
        initView();
    }

    public ComingSoonItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        initRootView();
        mImageView = findViewById(R.id.iv_poster);
        mTvIndex = findViewById(R.id.tv_index);
        mRlRoot = findViewById(R.id.rl_root);

    }

    void initRootView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_comingsoon_item, this);
        setLayoutParams(new LinearLayoutCompat.LayoutParams(CommingSoonSizeConst.getSmallPosterWidth(getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        //setBackgroundColor(Color.GREEN);
        uiState = IComingSoonItemAnimation.STATE_MID;
    }

    public void resetSize() {
        setLayoutParams(new LinearLayoutCompat.LayoutParams(CommingSoonSizeConst.getSmallPosterWidth(getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
        mRlRoot.getLayoutParams().height = CommingSoonSizeConst.getSmallPosterHeight(getContext());
        uiState = IComingSoonItemAnimation.STATE_MID;
    }

    public void setData(String url, String position) {
        Glide.with(getContext()).load(url).into(mImageView);
        mTvIndex.setText(position);
    }


    @Override
    public void changeToBig() {
        if (uiState == IComingSoonItemAnimation.STATE_BIG) return;
        playAnimation(TYPE_HEIGHT, mRlRoot.getLayoutParams().height, CommingSoonSizeConst.getBigPosterHeight(getContext()), mRlRoot);
        // mRlRoot.getLayoutParams().height = CommingSoonSizeConst.getBigPosterHeight(getContext());
        playAnimation(TYPE_WIDTH, getLayoutParams().width, CommingSoonSizeConst.getBigPosterWidth(getContext()), this);
        //getLayoutParams().width = CommingSoonSizeConst.getBigPosterWidth(getContext());
        uiState = IComingSoonItemAnimation.STATE_BIG;
    }

    @Override
    public void changeToMid() {
        mRlRoot.getLayoutParams().height = CommingSoonSizeConst.getMidPosterHeight(getContext());
        getLayoutParams().width = CommingSoonSizeConst.getMidPosterWidth(getContext());
        requestLayout();
        uiState = IComingSoonItemAnimation.STATE_MID;
    }

    @Override
    public void changeToSmall() {
        //playAnimation(TYPE_HEIGHT, mRlRoot.getLayoutParams().height, CommingSoonSizeConst.getSmallPosterHeight(getContext()), mRlRoot);
        mRlRoot.getLayoutParams().height = CommingSoonSizeConst.getSmallPosterHeight(getContext());
        getLayoutParams().width = CommingSoonSizeConst.getSmallPosterWidth(getContext());
        requestLayout();
        uiState = IComingSoonItemAnimation.STATE_SMALL;

    }

    private void playAnimation(int type, int start, int end, View target) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end)
                .setDuration(ANIMATOR_DURATION);
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (target == null || target.getLayoutParams() == null) {
                    return;
                }
                if (type == TYPE_HEIGHT) {
                    target.getLayoutParams().height = (int) animation.getAnimatedValue();
                } else {
                    target.getLayoutParams().width = (int) animation.getAnimatedValue();
                }
                target.requestLayout();

            }
        });
        valueAnimator.start();
    }
}
