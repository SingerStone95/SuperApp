package singerstone.com.superapp.Star;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
 */

public class StarViewContainer extends RelativeLayout {


    int width_container;
    int height_container;
    Context mContex;
    boolean isPlaying = false;
    ImageView star_1;
    ImageView star_2;
    ImageView star_3;
    ImageView star_4;
    ImageView star_5;
    ImageView star_6;
    ImageView star_7;
    ImageView bangbangtang;
    ImageView fire_out;
    ImageView flash;
    ImageView iv_flash_in;
    ImageView iv_flash_out;
    int flag = 0;
    ComboViewController comboViewController;
    OnceAnimationFinishedCallback animationFinishedCallback;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public StarViewContainer(Context context) {
        super(context);
        initView();
        initEvent();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public StarViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initView();
        initEvent();
    }

    private void initView() {
        star_1 = new ImageView(mContex);
        star_2 = new ImageView(mContex);
        star_3 = new ImageView(mContex);
        star_4 = new ImageView(mContex);
        star_5 = new ImageView(mContex);
        star_6 = new ImageView(mContex);
        star_7 = new ImageView(mContex);
        fire_out = new ImageView(mContex);
        bangbangtang = new ImageView(mContex);
        comboViewController = ComboViewController.getInstance(mContex);
    }

    private void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void startAnimation() {
        if (height_container == 0 && width_container == 0) {
            height_container = getMeasuredHeight();
            width_container = getMeasuredWidth();
        }
        if (!isPlaying) {
            //控制星星位置大小
            StarInfo info1 = new StarInfo();
            info1.setSize(150);
            info1.setX(width_container);
            info1.setY(180);
            star_1.setBackgroundResource(R.mipmap.star_2);
            drawStar(info1, star_1);

            StarInfo info2 = new StarInfo();
            info2.setSize(120);
            info2.setX(20);
            info2.setY(30);
            star_2.setBackgroundResource(R.mipmap.star_1);
            drawStar(info2, star_2);

            StarInfo info3 = new StarInfo();
            info3.setSize(80);
            info3.setX(90);
            info3.setY(180);
            star_3.setBackgroundResource(R.mipmap.star_1);
            drawStar(info3, star_3);

            StarInfo info4 = new StarInfo();
            info4.setSize(120);
            info4.setX(90);
            info4.setY(20);
            star_4.setBackgroundResource(R.mipmap.star_2);
            drawStar(info4, star_4);

            StarInfo info5 = new StarInfo();
            info5.setSize(150);
            info5.setX(width_container - 180);
            info5.setY(70);
            star_5.setBackgroundResource(R.mipmap.star_1);
            drawStar(info5, star_5);

            StarInfo info6 = new StarInfo();
            info6.setSize(80);
            info6.setX(100);
            info6.setY(height_container - 120);
            star_6.setBackgroundResource(R.mipmap.star_1);
            drawStar(info6, star_6);

            StarInfo info7 = new StarInfo();
            info7.setSize(150);
            info7.setX(0);
            info7.setY(height_container - 200);
            star_7.setBackgroundResource(R.mipmap.star_2);
            drawStar(info7, star_7);

            fire_out.setBackgroundResource(R.mipmap.fire_out);
            LayoutParams layoutParams_fireout = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams_fireout.addRule(RelativeLayout.CENTER_IN_PARENT);
            bangbangtang.setLayoutParams(layoutParams_fireout);
            addView(fire_out);
            playBigerAnim(fire_out);

            bangbangtang.setBackgroundResource(R.mipmap.bangbangtang);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            bangbangtang.setLayoutParams(layoutParams);
            addView(bangbangtang);
            playRoAnim(bangbangtang);


            isPlaying = true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void playBigerAnim(final View view) {
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.3f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1.3f);
        ObjectAnimator alphaXY = ObjectAnimator.ofFloat(view, "alpha", 0, 1, 0);//淡出效果
        animatorSet.play(scaleX).with(scaleY).with(alphaXY);//3个动画同时开始
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();
        scaleX.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                playBigerAnim(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void playRoAnim(View view) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 15f, 0);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();

    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height_container = getMeasuredHeight();
        width_container = getMeasuredWidth();

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        startAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void drawStar(final StarInfo starInfo, final ImageView star) {
        star.clearAnimation();
        removeView(star);
        star.setVisibility(GONE);
        LayoutParams lp;
        lp = new RelativeLayout.LayoutParams(starInfo.getSize(), starInfo.getSize());
        star.setX(starInfo.getX());
        star.setY(starInfo.getY());
        star.setLayoutParams(lp);
        //闪烁
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(star, "scaleX", 0f, 1.3f, 0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(star, "scaleY", 0f, 1.3f, 0f);
        ObjectAnimator alphaXY = ObjectAnimator.ofFloat(star, "alpha", 0, 1, 0);//淡出效果
        animatorSet.play(scaleX).with(scaleY).with(alphaXY);//3个动画同时开始
        int delay = getRadom(0, 1000);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setStartDelay(delay);
        animatorSet.start();
        alphaXY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                star.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //star.clearAnimation();
                // removeView(star);
                drawStar(starInfo, star);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        addView(star);

    }

    private int getRadom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    @Override
    protected void onFinishInflate() {
        flash = (ImageView) findViewById(R.id.flash);
        iv_flash_in = (ImageView) findViewById(R.id.flash_light_in);
        iv_flash_out = (ImageView) findViewById(R.id.flash_light_out);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void playAnimation(int num) {
        playFlashAnimation(flash);
        playFlashAnimation(iv_flash_in);
        playFlashAnimation(iv_flash_out);
        comboViewController.showComboWithPixMargin(90, 60, this, num);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void playFlashAnimation(final View view) {
        view.clearAnimation();
        //闪烁
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 2f);
        ObjectAnimator alphaXY = ObjectAnimator.ofFloat(view, "alpha", 1, 0);//淡出效果
        animatorSet.play(scaleX).with(scaleY).with(alphaXY);//3个动画同时开始
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                flag++;
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                flag--;
                if (flag > 0) {
                    return;
                }
                if (animationFinishedCallback != null) {
                    animationFinishedCallback.onAnimationFinished();
                }
               /* view.setVisibility(View.GONE);
                viewContainer.setVisibility(View.GONE);
                textContainer.setVisibility(View.GONE);
                comboViewController.hide();*/
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

    public interface OnceAnimationFinishedCallback {
        public void onAnimationFinished();
    }

    public void setAnimationFinishedCallback(OnceAnimationFinishedCallback animationFinishedCallback) {
        this.animationFinishedCallback = animationFinishedCallback;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        comboViewController = null;
    }
}
