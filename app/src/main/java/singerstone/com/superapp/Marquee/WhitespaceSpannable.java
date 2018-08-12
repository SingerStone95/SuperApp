package singerstone.com.superapp.Marquee;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;

/**
 * create by chenrenzhan
 * 空白的 spannable，可使用来调整 spannableString 的文字之间的间距
 */
public class WhitespaceSpannable extends DynamicDrawableSpan {
    private float mSpaceWidth = 0; // 空白间距
    public WhitespaceSpannable(float spaceWidth){
        mSpaceWidth = spaceWidth;
    }
    @Override
    public Drawable getDrawable() {
        Drawable drawable = new ColorDrawable(Color.parseColor("#ffffffff"));
        drawable.setBounds(0, 0, (int) mSpaceWidth, 0);
        return drawable;
    }
} 