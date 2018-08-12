package singerstone.com.superapp.treeholeview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2017/9/27.
 * YY:909075276
 */

public class TreeHoleAnimationView extends RelativeLayout {
    private Context mContex;
    private TreeholeProgressView mTreeholeProgressView;
    private int colorResId = R.color.treehole;
    boolean statePressed = false;

    public TreeHoleAnimationView(Context context) {
        super(context);
        mContex = context;
        initView();
    }

    public TreeHoleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initView();
    }

    private void initView() {
        mTreeholeProgressView = new TreeholeProgressView(mContex);
        RelativeLayout.LayoutParams lp_progress = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp_progress.addRule(CENTER_IN_PARENT);
        mTreeholeProgressView.setLayoutParams(lp_progress);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mTreeholeProgressView != null) {
            addView(mTreeholeProgressView);
        }
    }

    private void playAddAnimation() {
        final TextView tv_animation = new TextView(mContex);
        tv_animation.setText("+1");
        tv_animation.setTextSize(sp2px(mContex, 6));
        tv_animation.setTextColor(mContex.getResources().getColor(colorResId));
        RelativeLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL);
        lp.topMargin = -dp2px(mContex, 25);
        tv_animation.setLayoutParams(lp);
        addView(tv_animation);
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator anim_tran = ObjectAnimator.ofFloat(tv_animation, "translationY", 0, -dp2px(mContex, 20));
        ObjectAnimator anim_alpha = ObjectAnimator.ofFloat(tv_animation, "alpha", 1f, 0f);
        animSet.setDuration(1500);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.play(anim_alpha).with(anim_tran);
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

    public void setColorRes(int colorRes) {
        colorResId = colorRes;
        if (mTreeholeProgressView != null) {
            mTreeholeProgressView.setColorResourseId(colorRes);
        }
    }

    public void setBkgBitmapResId(int id) {
        if (mTreeholeProgressView != null) {
            mTreeholeProgressView.setBackGroundBitmapResID(id);
        }
    }
    public void setPressedBitmapResId(int id){

    }

    public void setCount(int count) {
        if (mTreeholeProgressView != null) {
            mTreeholeProgressView.setCount(count);
        }
        playAddAnimation();
    }

    public void setMaxCount(int maxCount) {
        if (mTreeholeProgressView != null) {
            mTreeholeProgressView.setMaxCount(maxCount);
        }
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }


    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] states = getDrawableState();
        for (int state : states) {
            if (state == android.R.attr.state_pressed) {
                statePressed = true;
            } else {
                statePressed = false;
            }
        }
        if (statePressed == true) {
            mTreeholeProgressView.setBackGroundBitmapResID(R.mipmap.ic_launcher);
        }else {
            mTreeholeProgressView.setBackGroundBitmapResID(R.drawable.icon_liveroom);
        }
    }
}
