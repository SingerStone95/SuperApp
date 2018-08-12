package singerstone.com.superapp.circlepkprogressView;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2017/7/19.
 * YY:909075276
 */

public class CirclepkAnimationView extends RelativeLayout {
    private Context mContex;
    private CirclepkprogressView mCircleProgressView;
    private CircleOutCoverView mCircleOutCoverView;
    private ImageView mImageviewCenterGift;
    private ImageView mImageviewCenterGift_top;
    private PKCoolingLayer mPkcoolingLayer;
    boolean statePressed = false;
    int MAX_COUNT = 66;
    int ALERT_COUNT = 60;//报警
    private PKCoolingLayer.OnCountDownFinishedListener onCountDownFinishedListener;

    public CirclepkAnimationView(Context context) {
        super(context);
        mContex = context;
        initView();
    }

    public CirclepkAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initView();
    }

    private void initView() {
        mCircleProgressView = new CirclepkprogressView(mContex);
        RelativeLayout.LayoutParams lp_CircleProgressView = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_CircleProgressView.addRule(CENTER_IN_PARENT);
        mCircleProgressView.setLayoutParams(lp_CircleProgressView);
        mImageviewCenterGift = new ImageView(mContex);
        mImageviewCenterGift.setBackgroundResource(R.mipmap.bangbangtang);
        RelativeLayout.LayoutParams lp_iamge_center_gift = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_iamge_center_gift.addRule(CENTER_IN_PARENT);
        lp_iamge_center_gift.setMargins(dp2px(mContex, 20), dp2px(mContex, 20), dp2px(mContex, 20), dp2px(mContex, 20));
        mImageviewCenterGift.setLayoutParams(lp_iamge_center_gift);

        mImageviewCenterGift_top = new ImageView(mContex);
        mImageviewCenterGift_top.setBackgroundResource(R.mipmap.bangbangtang);
        RelativeLayout.LayoutParams lp_iamge_center_gift_top = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_iamge_center_gift_top.addRule(CENTER_IN_PARENT);
        lp_iamge_center_gift_top.setMargins(dp2px(mContex, 20), dp2px(mContex, 20), dp2px(mContex, 20), dp2px(mContex, 20));
        mImageviewCenterGift_top.setLayoutParams(lp_iamge_center_gift_top);


        mCircleOutCoverView = new CircleOutCoverView(mContex);
        RelativeLayout.LayoutParams lp_CircleOutCoverView = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_CircleOutCoverView.addRule(CENTER_IN_PARENT);
        mCircleOutCoverView.setLayoutParams(lp_CircleOutCoverView);
        mPkcoolingLayer = new PKCoolingLayer(mContex);
        RelativeLayout.LayoutParams lp_PkcoolingLayer = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_PkcoolingLayer.addRule(CENTER_IN_PARENT);
        mPkcoolingLayer.setLayoutParams(lp_PkcoolingLayer);

    }

    @Override
    protected void onFinishInflate() {
        if (mImageviewCenterGift != null && mCircleProgressView != null && mCircleOutCoverView != null) {
            addView(mImageviewCenterGift);
            mImageviewCenterGift.setAlpha(0.5f);
            addView(mCircleProgressView);
            addView(mImageviewCenterGift_top);
            mImageviewCenterGift_top.setVisibility(GONE);

        }
    }

    //
    public void startCountDown() {
        removeView(mPkcoolingLayer);
        addView(mPkcoolingLayer);
        mPkcoolingLayer.setOnCountDownFinishedListener(new PKCoolingLayer.OnCountDownFinishedListener() {
            @Override
            public void onCountDownFinished() {
                //默认重置view
                resetView();
                if (onCountDownFinishedListener != null) {
                    onCountDownFinishedListener.onCountDownFinished();
                }
            }
        });
        mPkcoolingLayer.startCountDown();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setCurrentGiftCount(int count) {
        if (mCircleProgressView != null) {
            mCircleProgressView.setCount(count);
        }
        if (count < MAX_COUNT) {
            if (count >= ALERT_COUNT) {
                removeView(mCircleOutCoverView);
                addView(mCircleOutCoverView);
            }
            playAnimationOnce();
        } else if (count == MAX_COUNT) {
            playFinishedAnimation();
            stopFlash();
        } else {
            return;
        }

    }

    public void resetView() {
        mCircleProgressView.setCount(0);
        removeView(mCircleOutCoverView);
//        removeView(mImageviewCenterGift_top);
        mImageviewCenterGift_top.setVisibility(GONE);
        removeView(mPkcoolingLayer);
    }

    private void stopFlash() {
        removeView(mCircleOutCoverView);
    }

    private void playAnimationOnce() {
        final TextView tv_animation = new TextView(mContex);
        tv_animation.setText("+1");
        tv_animation.setTextSize(sp2px(mContex, 6));
        tv_animation.setTextColor(mContex.getResources().getColor(R.color.orange));
        RelativeLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL);
        lp.topMargin = -dp2px(mContex, 25);
        tv_animation.setLayoutParams(lp);
        addView(tv_animation);
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator anim_tran = ObjectAnimator.ofFloat(tv_animation, "translationY", 0, -dp2px(mContex, 25));
        ObjectAnimator anim_alpha = ObjectAnimator.ofFloat(tv_animation, "alpha", 1f, 0f);
        ObjectAnimator anim_scaleX = ObjectAnimator.ofFloat(tv_animation, "scaleX", 1.2f, 0.5f);
        ObjectAnimator anim_scaleY = ObjectAnimator.ofFloat(tv_animation, "scaleY", 1.2f, 0.5f);
        animSet.setDuration(1500);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.play(anim_alpha).with(anim_tran).with(anim_scaleX).with(anim_scaleY);
        animSet.start();
        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(tv_animation);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


    private void playFinishedAnimation() {
        mImageviewCenterGift_top.setVisibility(VISIBLE);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator anim_scaleX = ObjectAnimator.ofFloat(mImageviewCenterGift_top, "scaleX", 1f, 1.5f, 1f);
        ObjectAnimator anim_scaleY = ObjectAnimator.ofFloat(mImageviewCenterGift_top, "scaleY", 1f, 1.5f, 1f);
        anim_scaleX.setDuration(1000);
        anim_scaleY.setDuration(1000);
        animatorSet.play(anim_scaleX).with(anim_scaleY);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startCountDown();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();

    }

    public void setOnCountDownFinishedListener(PKCoolingLayer.OnCountDownFinishedListener onCountDownFinishedListener) {
        this.onCountDownFinishedListener = onCountDownFinishedListener;
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public void setMaxCount(int count) {
        MAX_COUNT = count;
        if (mCircleProgressView != null) {
            mCircleProgressView.setMaxCount(count);
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] states = getDrawableState();
        for (int state : states) {
            if (state == android.R.attr.state_pressed) {
                statePressed = true;
            }else {
                statePressed=false;
            }
        }
        if (statePressed==true){

        }

    }
}
