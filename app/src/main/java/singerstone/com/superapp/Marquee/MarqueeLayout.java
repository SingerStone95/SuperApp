package singerstone.com.superapp.Marquee;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/5/3.
 * YY:909075276
 */

public class MarqueeLayout extends FrameLayout {
    public final static String SPACE_PLACEHOLDER = "[space]"; // 空白占位符（不为空就行，不影响显示效果，会被WhitespaceSpannable替代）
    public final static float SPACE_WIDTH = 60f; // dp 两次滚动的前后的间隔距离
    private Context mContext;
    private TextView mTextView;
    private AnimatorSet animatorSet;
    private boolean isMarque = false; // 是否以跑马灯展示，根据显示长度长度而定
    private float mSpeed = 10f; // 跑马灯显示的速度, 乘以显示的信息的像素宽度的值表示动画持续时间
    private int mMarqueWidth = 0;
    private long mMaxDuration = 5000; // 跑马灯显示最长时间
    private long mDuration = 0;//跑马灯实际显示时间
    private int mScrollWidth = 0;//跑马灯移动的长度
    //左边的偏移量
    private int moveStartX = 0;
    //最大的宽度
    private int mMaxSeatWidth = 0;

    //是否需要设置偏移量
    public enum offsetType {
        Need, Cancle
    }

    private offsetType type = offsetType.Cancle;

    public void setType(offsetType type) {
        this.type = type;
    }

    public MarqueeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        moveStartX = (int) DensityUtil.dip2px(mContext, 0);
        mMaxSeatWidth = (int) ResolutionUtils.convertDpToPixel(60, mContext);
    }

    public void setMarqueWidth(int width) {
        if (width < 0) {
            return;
        }
        mMarqueWidth = width;
    }

    /**
     * 使用MarqueeLayout后，一定要在界面销毁的时候调用reserverAnimation()
     * 不然可能导致内存泄露
     */
    private void startMarquee() {
        reserverAnimation();
        if (mTextView != null) {
            TextPaint paint = mTextView.getPaint();
            CharSequence content = mTextView.getText();
            float len = Layout.getDesiredWidth(content, paint);
            int _width = DensityUtil.dip2px(getContext(), len);
            if (_width > mMaxSeatWidth) {
                _width = mMaxSeatWidth;
                setMarqueWidth((_width));
            } else {
                setMarqueWidth(_width);
            }
            ViewGroup.LayoutParams lp_bg = getLayoutParams();
            lp_bg.width = _width;
            if (mMarqueWidth <= 0) {
                mMarqueWidth = getMeasuredWidth();
            }
            int marqueWidth = mMarqueWidth;
            if (len > marqueWidth) {
                SpannableStringBuilder spannable = new SpannableStringBuilder(content);
                SpannableStringBuilder another = spannable;
                spannable.append(SPACE_PLACEHOLDER).append(another);//复制文字，显示两轮
                int idx = spannable.toString().indexOf(SPACE_PLACEHOLDER);
                float spaceWidth = ResolutionUtils.convertDpToPixel(SPACE_WIDTH, getContext());
                if (idx >= 0 && idx + SPACE_PLACEHOLDER.length() < spannable.length()) {
                    spannable.setSpan(new WhitespaceSpannable(spaceWidth), idx, idx + SPACE_PLACEHOLDER.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                mTextView.setText(spannable);
                float width = Layout.getDesiredWidth(spannable, paint);
                isMarque = true;
                //开始跑马灯动画
                FrameLayout.LayoutParams lp = new LayoutParams((int) width, LayoutParams.WRAP_CONTENT);
                mTextView.setLayoutParams(lp);//textview超出Max长度
                animatorSet = new AnimatorSet();
               // mScrollWidth = (int) (-(len - marqueWidth));//改变mScrollWidth可以控制滚动的距离
                mScrollWidth=(int)(-len-spaceWidth);
                ObjectAnimator objectAnimator;
                if (type == offsetType.Cancle) {
                    objectAnimator = ObjectAnimator.ofFloat(mTextView, "translationX", 0, mScrollWidth);
                } else
                    objectAnimator = ObjectAnimator.ofFloat(mTextView, "translationX", moveStartX, mScrollWidth);
                mDuration = (long) (mSpeed * Math.abs(mScrollWidth));
                if (mDuration > mMaxDuration) {
                    mDuration = mMaxDuration;
                }
                objectAnimator.setDuration(mDuration);
                objectAnimator.setInterpolator(new LinearInterpolator());
                animatorSet.play(objectAnimator);
                animatorSet.start();
            } else {
                isMarque = false;
                mDuration = 0;
                FrameLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                if (type == offsetType.Need) {
                    lp.leftMargin = moveStartX;
                } else if (type == offsetType.Cancle) {
                    lp.leftMargin = 0;
                }
                mTextView.setLayoutParams(lp);
            }
        }
    }

    //清除属性动画
    public void reserverAnimation() {
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            animatorSet.end();
            animatorSet = null;
        }
        if (mTextView != null) {
            mTextView.setTranslationX(0);
        }
    }

    public void setText(Spannable info) {
        if (mTextView == null) {
            mTextView = (TextView) getChildAt(0);
        }
        if (mTextView == null && mContext != null) {
            mTextView = new TextView(mContext);
            mTextView.setSingleLine(true);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.addView(mTextView, lp);
        }
        if (mTextView != null) {
            if (info != null) {
                mTextView.setText(info);
            }
            startMarquee();
        }
    }
    public int getmMaxSeatWidth() {
        return mMaxSeatWidth;
    }

    public void setmMaxSeatWidth(int mMaxSeatWidth) {
        this.mMaxSeatWidth = mMaxSeatWidth;
    }
    public boolean isMarque() {
        return isMarque;
    }

    public void setSpeed(float speed) {
        mSpeed = speed;
    }

    public float getSpeed() {
        return mSpeed;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setMaxDuration(long max) {
        mMaxDuration = max;
    }
}
