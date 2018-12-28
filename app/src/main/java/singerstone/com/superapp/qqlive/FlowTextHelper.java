package singerstone.com.superapp.qqlive;

import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

class FlowTextHelper {

    private static boolean mNewClassAvailable;

    static {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) { // Froyo 2.2, API level 8
           mNewClassAvailable = true;
        }
    }

    public static void tryFlowText(CharSequence text, TextView messageView, int width, int lines) {
        // There is nothing I can do for older versions, so just return
        if (!mNewClassAvailable) return;
        SpannableString ss = new SpannableString(text);
        ss.setSpan(new MyLeadingMarginSpan2(lines, width), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        messageView.setText(ss);

        // Align the text with the image by removing the rule that the text is to the right of the image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) messageView.getLayoutParams();
        int[] rules = params.getRules();
        rules[RelativeLayout.RIGHT_OF] = 1;
    }
}