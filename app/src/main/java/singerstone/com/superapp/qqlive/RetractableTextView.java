package singerstone.com.superapp.qqlive;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import singerstone.com.superapp.R;


public class RetractableTextView extends TextView {

    private ITextViewClickListener mTextViewClickListener;
    private int mRetractableTextViewInitHeight;

    public RetractableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RetractableTextView(Context context) {
        super(context);
        init(context, null);
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RetractableTextView);
        if (a.hasValue(R.styleable.RetractableTextView_drawableToggle)) {
            drawableToggle = a.getResourceId(R.styleable.RetractableTextView_drawableToggle, -1);
        }
        if (a.hasValue(R.styleable.RetractableTextView_isRecover)) {
            isRecover = a.getBoolean(R.styleable.RetractableTextView_isRecover, false);
        }
        if (a.hasValue(R.styleable.RetractableTextView_showMaxLines)) {
            setShowMaxLines(a.getInteger(R.styleable.RetractableTextView_showMaxLines, 1));
        }
        a.recycle();
    }

    private String fullText;//原始
    private int maxLines;
    private boolean programmaticChange;
    private boolean isStale;
    private float lineSpacingMultiplier = 1.0f;
    private float lineAdditionalVerticalPadding = 0.0f;
    private int showMaxLines = Integer.MAX_VALUE;
    private int drawableToggle = -1;
    private boolean isRecover = false;
    private static final String ELLIPSIS = "...";
    private static final String PLACE_HOLDER = "占位";

    @Override
    public void setLineSpacing(float add, float mult) {
        this.lineAdditionalVerticalPadding = add;
        this.lineSpacingMultiplier = mult;
        super.setLineSpacing(add, mult);
    }

    public void setShowMaxLines(int showMaxLines) {
        super.setMaxLines(showMaxLines);
        this.showMaxLines = showMaxLines > 0 ? showMaxLines : 1;//至少也得是1行啊~
        isStale = true;
    }

    public void setRecover(boolean isNeedRecover) {
        isRecover = isNeedRecover;
    }

    @Override
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines > 0 ? maxLines : 1;//至少也得是1行啊~
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before,
                                 int after) {
        super.onTextChanged(text, start, before, after);
        if (!programmaticChange) {
            fullText = text.toString();
            isStale = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isStale) {
            super.setEllipsize(null);
            boolean isShrink = shrinkText();
            if (!isShrink) {
                setText(fullText);
            }
            programmaticChange = false;
            isStale = false;
        }
        super.onDraw(canvas);
    }

    private boolean shrinkText() {
        String workingText = fullText;
        Layout layout = createWorkingLayout(workingText);
        int lineCount = layout.getLineCount();
        if (!TextUtils.isEmpty(workingText)) {//超出目标函数
            if (lineCount > maxLines && lineCount > showMaxLines) {
                programmaticChange = true;
                String tempStr = workingText;
                int position = layout.getLineEnd(showMaxLines - 1);
                tempStr = workingText.substring(0, position);//截取目标函数字符
                if (!workingText.equals(tempStr)) {//被截断
                    if (isNeedToggle()) {//需要展开
                        while (!TextUtils.isEmpty(tempStr) && tempStr.length() > 0
                                && createWorkingLayout(tempStr + ELLIPSIS + PLACE_HOLDER).getLineCount() > showMaxLines) {
                            tempStr = tempStr.substring(0, tempStr.length() - 1);
                        }
                        tempStr += ELLIPSIS;
                        workingText = tempStr;

                        int start = workingText.length();
                        int end = start + PLACE_HOLDER.length();
                        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(workingText + PLACE_HOLDER);
                        ssBuilder.setSpan(getToggleImageSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        setText(ssBuilder);
                        setOnClickListener(unfoldClickListener);
                    } else {
                        while (!TextUtils.isEmpty(tempStr) && tempStr.length() > 0
                                && createWorkingLayout(tempStr + ELLIPSIS).getLineCount() > showMaxLines) {
                            tempStr = tempStr.substring(0, tempStr.length() - 1);
                        }
                        tempStr += ELLIPSIS;
                        workingText = tempStr;
                        isRecover = false;
                        setText(workingText);
                    }
                    return true;
                }
            } else {
                isRecover = false;
                super.setMaxLines(Integer.MAX_VALUE);
                setText(workingText);
            }
            mRetractableTextViewInitHeight = getHeight();
        }
        return false;
    }

    private boolean isNeedToggle() {
        try {
            getResources().getDrawable(drawableToggle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ImageSpan getToggleImageSpan() {
        Drawable drawable = getResources().getDrawable(drawableToggle);
        int height = drawable.getIntrinsicHeight();
        int width = drawable.getIntrinsicWidth();
        drawable.setBounds(0, 0, width, height);
        return new ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BASELINE);
    }

    private OnClickListener unfoldClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!fullText.equals(getText())) {
                unfolder();
            } else {
                isStale = true;
                postInvalidate();
            }
        }
    };

    private void unfolder() {
        super.setMaxLines(Integer.MAX_VALUE);
        programmaticChange = true;
        setText(fullText);
        programmaticChange = false;
        if (mTextViewClickListener != null) {
            mTextViewClickListener.onClick(getHeight() - mRetractableTextViewInitHeight);
            mRetractableTextViewInitHeight = getHeight();
        }
        if (isRecover) {
            setOnClickListener(foldClickListener);
        }
    }

    private OnClickListener foldClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            isStale = true;
            setText(fullText);
        }
    };

    private Layout createWorkingLayout(String workingText) {
        return new StaticLayout(workingText, getPaint(), getWidth()
                - getPaddingLeft() - getPaddingRight(), Alignment.ALIGN_NORMAL,
                lineSpacingMultiplier, lineAdditionalVerticalPadding, false);
    }

    public void setOnTextViewClickListener(ITextViewClickListener textViewClickListener) {
        mTextViewClickListener = textViewClickListener;
    }

    public interface ITextViewClickListener {
        void onClick(int heightAdd);
    }
}
