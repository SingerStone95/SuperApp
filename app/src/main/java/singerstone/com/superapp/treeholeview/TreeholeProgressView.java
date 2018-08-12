package singerstone.com.superapp.treeholeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import singerstone.com.superapp.R;


/**
 * Created by chenbinhao on 2017/7/19.
 * YY:909075276
 */

public class TreeholeProgressView extends View {

    Context mContex;
    Paint mCirclePaint;
    Paint mCircleInnerPaint;
    Paint mTextPaint_color;
    int mWidth = 0;
    int mHeight = 0;
    int mCount = 1;
    String text = "";
    RectF mCircleRectF;
    //外部填充圆形半径
    float CIRCLE_RADIUS_EXTER = 0;
    //进度圆形半径
    float CIRCLE_RADIUS = 0;
    int MAX_COUNT = 66;
    final int MIN_COUNT = 0;
    int CIRCLE_LINE_WIDTH = 2;
    int TEXT_SIZE = 8;
    //图片PADDING
    int BITMAP_PADDING=2;
    int colorResourseId= R.color.treehole;
    int bitmapResid=R.mipmap.ic_launcher;

    public TreeholeProgressView(Context context) {
        super(context);
        init(context);
    }

    public TreeholeProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        mContex = context;
        //进度条
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(dp2px(context, CIRCLE_LINE_WIDTH));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(ContextCompat.getColor(context,colorResourseId));
       //背景
        mCircleInnerPaint = new Paint();
        mCircleInnerPaint.setAntiAlias(true);
        mCircleInnerPaint.setStyle(Paint.Style.FILL);
        mCircleInnerPaint.setColor(ContextCompat.getColor(context, R.color.darkgrey));
        //文字
        mTextPaint_color = new TextPaint();
        mTextPaint_color.setAntiAlias(true);
        mTextPaint_color.setStyle(Paint.Style.FILL);
        mTextPaint_color.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint_color.setColor(ContextCompat.getColor(context, R.color.black));
        mTextPaint_color.setTextSize(sp2px(context, TEXT_SIZE));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();
    }

    public void setCount(int count) {
        if (count > MAX_COUNT) {
            count = MAX_COUNT;
        }
        if (count < MIN_COUNT) {
            count = MIN_COUNT;
        }
        mCount = count;
        text = mCount+"/" + MAX_COUNT;
        if (mCount <=MAX_COUNT) {
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float halfWidth = mWidth / 2;
        float halfHeight = mHeight / 2;
        CIRCLE_RADIUS = halfWidth - dp2px(mContex, BITMAP_PADDING/2);
        CIRCLE_RADIUS_EXTER = halfWidth;
        //画外部分填充圆形
        canvas.drawCircle(halfWidth, halfHeight, CIRCLE_RADIUS_EXTER , mCircleInnerPaint);
        //进度圆形
        if (null == mCircleRectF) {
            mCircleRectF = new RectF(halfWidth - CIRCLE_RADIUS, halfHeight - CIRCLE_RADIUS, halfWidth + CIRCLE_RADIUS, halfHeight + CIRCLE_RADIUS);
        }
        float swipeProgress = mCount / (float) MAX_COUNT * 360;
        mCirclePaint.setAlpha(255);
        mCirclePaint.setColor(ContextCompat.getColor(mContex, colorResourseId));
        Bitmap bitmap = getBitmap(bitmapResid);
        bitmap = Bitmap.createScaledBitmap(bitmap, mWidth-2*dp2px(mContex, BITMAP_PADDING),  mHeight-2*dp2px(mContex, BITMAP_PADDING), true);
        int left = (int) (halfWidth - (bitmap.getWidth() / 2));
        int top = (int) (halfHeight - (bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, left, top, mCirclePaint);
        canvas.drawArc(mCircleRectF, -90, -swipeProgress, false, mCirclePaint);
        //画文字
        canvas.drawText(text, halfWidth - mTextPaint_color.measureText(text) / 2, mWidth*14/20 - (mTextPaint_color.ascent() + mTextPaint_color.descent()) / 2, mTextPaint_color);
    }


    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    public int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public void setMaxCount(int maxCount) {
        this.MAX_COUNT = maxCount;
        setCount( mCount);
        postInvalidate();
    }

    //设置背景图
    public void setBackGroundBitmapResID(int drawableId){
      this.bitmapResid=drawableId;
        postInvalidate();
    }
    //设置进度条颜色
    public void setColorResourseId(int resourseId){
        colorResourseId=resourseId;
        mCirclePaint.setColor(ContextCompat.getColor(mContex,colorResourseId));
        postInvalidate();
    }
    private Bitmap getBitmap(int resId) {
        Bitmap bitmap;

        if (resId != -1) {
            bitmap = BitmapFactory.decodeResource(getResources(), resId);

        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        return bitmap;
    }
}
