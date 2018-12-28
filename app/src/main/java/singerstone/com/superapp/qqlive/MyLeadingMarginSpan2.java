package singerstone.com.superapp.qqlive;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class MyLeadingMarginSpan2 implements LeadingMarginSpan.LeadingMarginSpan2 {
    private int margin;
    private int lines;
    private boolean wasDrawCalled = false;
    private int drawLineCount = 0;

    public MyLeadingMarginSpan2(int lines, int margin) {
        this.margin = margin;
        this.lines = lines;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        boolean isFirstMargin = first;
        // a different algorithm for api 21+
        if (Build.VERSION.SDK_INT >= 21) {
            this.drawLineCount = this.wasDrawCalled ? this.drawLineCount + 1 : 0;
            this.wasDrawCalled = false;
            isFirstMargin = this.drawLineCount <= this.lines;
        }

        return isFirstMargin ? this.margin : 0;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        this.wasDrawCalled = true;
    }

    @Override
    public int getLeadingMarginLineCount() {
        return this.lines;
    }
}