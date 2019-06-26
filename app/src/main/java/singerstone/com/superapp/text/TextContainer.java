package singerstone.com.superapp.text;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2017/7/6.
 * YY:909075276
 */

public class TextContainer extends RelativeLayout {
    Context mContex;
    ImageView img_top;
    ImageView img_bottom;
    ImageView img_mid;
    TextView tv_Text;
    boolean isShow=false;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public TextContainer(Context context) {
        super(context);
        mContex = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public TextContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContex = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void initView() {
        img_bottom = new ImageView(mContex);
        img_mid = new ImageView(mContex);
        img_top = new ImageView(mContex);
        tv_Text = new TextView(mContex);
        LayoutParams lp_top = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_top.addRule(ALIGN_PARENT_TOP);
        lp_top.addRule(CENTER_HORIZONTAL);
        img_top.setLayoutParams(lp_top);
        img_top.setBackgroundResource(R.mipmap.text_top);
        LayoutParams lp_mid = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_mid.addRule(CENTER_IN_PARENT);
        lp_mid.addRule(Gravity.CENTER);
        img_mid.setLayoutParams(lp_mid);
        img_mid.setBackgroundResource(R.mipmap.text_mid);
        LayoutParams lp_bottom = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_bottom.addRule(ALIGN_PARENT_BOTTOM);
        lp_bottom.addRule(CENTER_HORIZONTAL);
        img_bottom.setLayoutParams(lp_bottom);
        img_bottom.setBackgroundResource(R.mipmap.text_bottom);
        LayoutParams lp_mid_text = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp_mid_text.addRule(CENTER_IN_PARENT);
        lp_mid_text.addRule(Gravity.CENTER);
        tv_Text.setLayoutParams(lp_mid_text);
        tv_Text.setSingleLine(true);
        addView(img_bottom);
        addView(img_mid);
        addView(img_top);
        addView(tv_Text);
        playAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void showContainer(){
        if (isShow==false) {
            AnimatorSet animatorSet = new AnimatorSet();//组合动画
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f);
            animatorSet.play(scaleX).with(scaleY);
            animatorSet.setDuration(1000);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.start();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void playAnimation() {
        img_top.clearAnimation();
        img_bottom.clearAnimation();
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        ObjectAnimator anim_top = ObjectAnimator.ofFloat(img_top, "translationX", 0, 300, 0);
        ObjectAnimator anim_bottom = ObjectAnimator.ofFloat(img_bottom, "translationX", 0, -300, 0);
        animatorSet.setDuration(8000);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(anim_top).with(anim_bottom);
        animatorSet.start();

    }

    public void setShowText(CharSequence showText) {
        if (tv_Text != null) {
            tv_Text.setText(showText);
        }
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
