package singerstone.com.superapp.upcoming;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
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

public class UpComingItemView extends LinearLayout implements IGalleryAnimation {

    public RelativeLayout mRlRoot;
    public ImageView mImageView;
    public TextView mTvIndex;
    private int uiState = IGalleryAnimation.STATE_MID;

    public static final int TYPE_HEIGHT = 1;
    public static final int TYPE_WIDTH = 2;

    public static int ANIMATOR_DURATION = 300;

    public UpComingItemView(Context context) {
        super(context);
        initView();
    }

    public UpComingItemView(Context context, @Nullable AttributeSet attrs) {
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
        LayoutInflater.from(getContext()).inflate(R.layout.layout_upcoming_item, this);
        setLayoutParams(new LinearLayoutCompat.LayoutParams(UpCommingSizeConst.getMidPosterWidth(getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.GREEN);
        uiState = IGalleryAnimation.STATE_MID;
    }

    public void resetSize() {
        setLayoutParams(new LinearLayoutCompat.LayoutParams(UpCommingSizeConst.getMidPosterWidth(getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
        mRlRoot.getLayoutParams().height = UpCommingSizeConst.getMidPosterHeight(getContext());
        uiState = IGalleryAnimation.STATE_MID;
    }

    public void setData(String url, String position) {
        Glide.with(getContext()).load(url).into(mImageView);
        mTvIndex.setText(position);
    }


    @Override
    public void changeToBig() {
        if (uiState == IGalleryAnimation.STATE_BIG) return;
        playAnimation(TYPE_HEIGHT, mRlRoot.getLayoutParams().height, UpCommingSizeConst.getBigPosterHeight(getContext()), mRlRoot);
        // mRlRoot.getLayoutParams().height = UpCommingSizeConst.getBigPosterHeight(getContext());
        playAnimation(TYPE_WIDTH, getLayoutParams().width, UpCommingSizeConst.getBigPosterWidth(getContext()), this);
        //getLayoutParams().width = UpCommingSizeConst.getBigPosterWidth(getContext());
        uiState = IGalleryAnimation.STATE_BIG;
    }

    @Override
    public void changeToMid() {
        mRlRoot.getLayoutParams().height = UpCommingSizeConst.getMidPosterHeight(getContext());
        getLayoutParams().width = UpCommingSizeConst.getMidPosterWidth(getContext());
        uiState = IGalleryAnimation.STATE_MID;
    }

    @Override
    public void changeToSmall() {
        //playAnimation(TYPE_HEIGHT, mRlRoot.getLayoutParams().height, UpCommingSizeConst.getSmallPosterHeight(getContext()), mRlRoot);
        mRlRoot.getLayoutParams().height = UpCommingSizeConst.getSmallPosterHeight(getContext());
        getLayoutParams().width = UpCommingSizeConst.getMidPosterWidth(getContext());

        uiState = IGalleryAnimation.STATE_SMALL;

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
