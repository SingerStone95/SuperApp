package singerstone.com.superapp.waveeffect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class DebugTextView extends TextView {
    public int index = 0;

    public DebugTextView(Context context) {
        super(context);
    }

    public DebugTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DebugTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i("DebugTextView", "onAttachedToWindow :" + index);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i("DebugTextView", "onDetachedFromWindow:" + index);
    }
}
