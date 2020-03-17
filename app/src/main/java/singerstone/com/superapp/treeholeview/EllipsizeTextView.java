package singerstone.com.superapp.treeholeview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;

public class EllipsizeTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String THREE_DOTS = "...";
    private static final int THREE_DOTS_LENGTH = THREE_DOTS.length();

    private volatile boolean enableEllipsizeWorkaround = true;
    private SpannableStringBuilder spannableStringBuilder;

    public EllipsizeTextView(Context context) {
        super(context);
    }

    public EllipsizeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EllipsizeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setEnableEllipsizeWorkaround(boolean enableEllipsizeWorkaround) {
        this.enableEllipsizeWorkaround = enableEllipsizeWorkaround;
    }

    // https://stackoverflow.com/questions/14691511/textview-using-spannable-ellipsize-doesnt-work
    // https://blog.csdn.net/htyxz8802/article/details/50387950
    @Override
    protected void onDraw(Canvas canvas) {
        if (enableEllipsizeWorkaround && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN ) {
            final Layout layout = getLayout();
            if (layout.getLineCount() >= getMaxLines()) {
                CharSequence charSequence = getText();

                int lastCharDown = layout.getLineVisibleEnd(getMaxLines()-1);

                if (lastCharDown >= THREE_DOTS_LENGTH && charSequence.length() > lastCharDown) {
                    if (spannableStringBuilder == null) {
                        spannableStringBuilder = new SpannableStringBuilder();
                    } else {
                        spannableStringBuilder.clear();
                    }

                    if (charSequence instanceof String){
                        String tempCharSequ = ((String)charSequence).substring(0, lastCharDown);
                        if ((tempCharSequ).contains("\n")){
                            spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown));
                        }else {
                            spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown - THREE_DOTS_LENGTH)).append(THREE_DOTS);
                        }
                    }else{
                        String tempCharSequ = charSequence.toString().substring(0, lastCharDown);
                        if ((tempCharSequ).contains("\n")){
                            spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown));
                        }else {
                            spannableStringBuilder.append(charSequence.subSequence(0, lastCharDown - THREE_DOTS_LENGTH)).append(THREE_DOTS);
                        }
                    }

                    setText(spannableStringBuilder);
                }
            }
        }

        super.onDraw(canvas);
    }
}