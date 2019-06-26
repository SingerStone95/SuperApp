package singerstone.com.superapp.like;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/10/30.
 * YY:909075276
 */

public class LikeTextView extends View {


    private int num = 0;
    private Paint textPaint;
    //view宽高
    private int mWidth = 0;
    private int mHeight = 0;
    //数字宽高
    private int textWidth = 0;
    private int textHeight = 0;
    //Y方向的偏移量
    private float offsetY = 0;

    private int textSize = 0;

    /**
     * 滑动的起始，终止位置
     */
    private float startDY = 0;

    private Rect bounds = new Rect();
    /**
     * 解决顶部始终会超出一点点,但是字体过大失效
     */
    private int DEFAULT_PADING = dip2px(1);

    public LikeTextView(Context context) {
        super(context);
        initPaint();
    }

    public LikeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        textSize = sp2px(100);
        textPaint.setTextSize(textSize);
        textPaint.getTextBounds(getDrawText(), 0, getDrawText().length(), bounds);
        L.i(bounds.width() + " " + bounds.height());
        textWidth = bounds.width();
        textHeight = bounds.height();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float stringWidth = textPaint.measureText(getDrawText());
        float x = (mWidth - stringWidth) / 2;
       /* Rect bounds = new Rect();
        textPaint.getTextBounds(getDrawText(), 0, getDrawText().length(), bounds);
        L.i(bounds.width()+" "+bounds.height());
        textWidth=bounds.width();
        textHeight=bounds.height();*/
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //L.e(fontMetrics.ascent + "\n" + fontMetrics.top + "\n" + fontMetrics.descent + "\n" + fontMetrics.bottom + "\n" + fontMetrics.leading);
        // float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        float dy = (Math.abs(fontMetrics.ascent) + Math.abs(fontMetrics.descent)) / 2 - Math.abs(fontMetrics.descent);
        float y = mHeight / 2 + dy;
        //int yPos = (int) ((mHeight / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        canvas.drawText(getDrawText(), x, y + offsetY + DEFAULT_PADING, textPaint);
        canvas.drawText(getPreDrawText(), x, y + offsetY + DEFAULT_PADING - textHeight, textPaint);
        canvas.drawText(getNextDrawText(), x, y + offsetY + DEFAULT_PADING + textHeight, textPaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth(widthMeasureSpec);
        mHeight = getHeight(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    private String getDrawText() {
        return num + "";
    }

    private String getNextDrawText() {
        if (num+1==10){
            return 0+"";
        }
        return (num + 1)+ "";
    }
    private String getPreDrawText() {
        if (num-1==-1){
            return 9+"";
        }
        return (num -1)+ "";
    }

    private int getWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result = getContentWidth();
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                result = Math.max(getContentWidth(), result);
                break;
        }
        return result;
    }

    private int getContentWidth() {
        int result;
        result = textWidth;
        result += getPaddingLeft() + getPaddingRight() + DEFAULT_PADING;
        return result;
    }

    private int getHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result = getContentHeight();
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                result = Math.max(getContentHeight(), result);
                break;
        }
        return result;
    }

    private int getContentHeight() {
        int result;
        result = textHeight;
        result += getPaddingTop() + getPaddingBottom() + DEFAULT_PADING;
        return result;
    }

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startDY = event.getRawY();

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (startDY < event.getRawY()) {//往下滑
                offsetY = event.getRawY() - startDY;
                if (event.getRawY()-startDY>textHeight){
                    offsetY=textHeight;
                }
            } else {
                offsetY = event.getRawY() - startDY;
                if (event.getRawY()-startDY<-textHeight){
                    offsetY=-textHeight;
                }
            }

            invalidate();

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (startDY < event.getRawY()) {//往下滑
                if ( event.getRawY()-startDY>textHeight/2) {
                    offsetY = textHeight;
                    if (num-1==-1){
                        num=9;
                    }else {
                        num--;
                    }
                    invalidate();
                }else {
                    offsetY=0;
                    startDY=0;
                    invalidate();
                }
            } else {
                if ( startDY-event.getRawY()>textHeight/2) {
                    offsetY = -textHeight;
                    if (num+1==10){
                        num=0;
                    }else {
                    num++;
                    }
                    invalidate();
                }else {
                    offsetY=0;
                    startDY=0;
                    invalidate();
                }
            }

            offsetY=0;
            startDY=0;
        } else if (event.getAction() == MotionEvent.ACTION_CANCEL) {

        }
        return true;
    }
}
