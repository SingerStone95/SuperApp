package singerstone.com.superapp.treeholeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ImageSpan;
import android.view.View;

import androidx.annotation.NonNull;

public abstract class ClickableImageSpan extends ImageSpan {
    public static final String TAG = "ClickableImageSpan";

    public ClickableImageSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public ClickableImageSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    public ClickableImageSpan(Drawable d) {
        super(d);
    }

    public ClickableImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    public ClickableImageSpan(Drawable d, String source) {
        super(d, source);
    }

    public ClickableImageSpan(Drawable d, String source, int verticalAlignment) {
        super(d, source, verticalAlignment);
    }

    public ClickableImageSpan(Context context, Uri uri) {
        super(context, uri);
    }

    public ClickableImageSpan(Context context, Uri uri, int verticalAlignment) {
        super(context, uri, verticalAlignment);
    }

    public ClickableImageSpan(Context context, int resourceId) {
        super(context, resourceId);
    }

    public ClickableImageSpan(Context context, int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

    @Override
    public String getSource() {
        return super.getSource();
    }

    public abstract void onClick(View view);
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
                     @NonNull Paint paint) {

        Drawable b = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;//计算y方向的位移
        canvas.save();
        canvas.translate(x, transY);//绘制图片位移一段距离
        b.draw(canvas);
        canvas.restore();
    }
}