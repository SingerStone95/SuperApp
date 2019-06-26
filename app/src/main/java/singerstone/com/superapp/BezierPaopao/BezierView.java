package singerstone.com.superapp.BezierPaopao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;



import singerstone.com.superapp.R;

/**
 * Created by chenupt@gmail.com on 11/20/14.
 * Description : custom layout to draw bezier
 */
public class BezierView extends RelativeLayout {

    // 默认定点圆半径
    public static final float DEFAULT_RADIUS = 150;

    private Paint paint;
    private Path path;

    // 手势坐标
    float x = 300;
    float y = 300;

    // 锚点坐标
    float anchorX = 200;
    float anchorY = 300;

    // 起点坐标
    float startX = 100;
    float startY = 100;

    // 定点圆半径
    float radius = DEFAULT_RADIUS;

    // 判断动画是否开始
    boolean isAnimStart;
    // 判断是否开始拖动
    boolean isTouch;

    boolean isDrawFirst = false;

    ImageView exploredImageView;
    ImageView tipImageView;

    public BezierView(Context context) {
        super(context);
        init();
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(CENTER_IN_PARENT);
        exploredImageView = new ImageView(getContext());
        exploredImageView.setLayoutParams(params1);
        exploredImageView.setImageResource(R.drawable.tip_anim);
        exploredImageView.setVisibility(View.INVISIBLE);
        addView(exploredImageView);

        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.addRule(CENTER_IN_PARENT);
        tipImageView = new ImageView(getContext());
        tipImageView.setLayoutParams(params2);
        tipImageView.setImageResource(R.mipmap.skin_tips_newmessage_ninetynine);
        addView(tipImageView);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        startX = getMeasuredWidth()/2;
        startY = getMeasuredHeight()/2;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        ViewTreeObserver vto = exploredImageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                exploredImageView.setX(startX - exploredImageView.getMeasuredWidth() / 2);
                exploredImageView.setY(startY - exploredImageView.getMeasuredHeight() / 2);
                return true;
            }
        });
        ViewTreeObserver vto2 = exploredImageView.getViewTreeObserver();
        vto2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tipImageView.setX(startX - tipImageView.getMeasuredWidth() / 2);
                tipImageView.setY(startY - tipImageView.getMeasuredHeight() / 2);
                return true;
            }
        });


        super.onLayout(changed, left, top, right, bottom);
    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void calculate() {
        //拉伸距离
        float distance = (float) Math.sqrt(Math.pow(y - startY, 2) + Math.pow(x - startX, 2));

        radius = -distance / 15 + DEFAULT_RADIUS;//简单线性关系

        if (radius < 9) {//拉断
            isAnimStart = true;
            exploredImageView.setVisibility(View.VISIBLE);
            exploredImageView.setImageResource(R.drawable.tip_anim);
            ((AnimationDrawable) exploredImageView.getDrawable()).stop();
            ((AnimationDrawable) exploredImageView.getDrawable()).start();

            tipImageView.setVisibility(View.GONE);
        }

        // 根据角度算出四边形的四个点
        float offsetX = (float) (radius * Math.sin(Math.atan((y - startY) / (x - startX))));
        float offsetY = (float) (radius * Math.cos(Math.atan((y - startY) / (x - startX))));
        //初始点左下方的点
        float x1 = startX - offsetX;
        float y1 = startY + offsetY;
        //初始点右上方的点
        float x2 = x - offsetX;
        float y2 = y + offsetY;

        float x3 = x + offsetX;
        float y3 = y - offsetY;

        float x4 = startX + offsetX;
        float y4 = startY - offsetY;

        path.reset();
        path.moveTo(x1, y1);
        path.quadTo(anchorX, anchorY, x2, y2);
        path.lineTo(x3, y3);
        path.quadTo(anchorX, anchorY, x4, y4);
        path.lineTo(x1, y1);

        // 更改图标的位置
        tipImageView.setX(x - tipImageView.getWidth() / 2);
        tipImageView.setY(y - tipImageView.getHeight() / 2);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onDraw(Canvas canvas) {

        if (isAnimStart || !isTouch) {
            // canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.OVERLAY);
        } else {
            calculate();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.OVERLAY);
            canvas.drawPath(path, paint);
            canvas.drawCircle(startX, startY, radius, paint);
            canvas.drawCircle(x, y, radius, paint);
        }

        super.onDraw(canvas);
    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 判断触摸点是否在tipImageView中
            Rect rect = new Rect();
            int[] location = new int[2];
            tipImageView.getDrawingRect(rect);
            // L.e("x: " +rect.left+"y: " +rect.top);
            tipImageView.getLocationOnScreen(location);
            //.e("x: " +location[0]+"y: " +location[1]);
            // TODO: 2017/7/8 获取图片的绝对位置
            rect.left = location[0];
            rect.top = location[1];
            rect.right = rect.right + location[0];
            rect.bottom = rect.bottom + location[1];
            //触点在图片上
            if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                isTouch = true;
            }

        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            isTouch = false;
            //还原
            tipImageView.setX(startX - tipImageView.getWidth() / 2);
            tipImageView.setY(startY - tipImageView.getHeight() / 2);
        }
        invalidate();//绘制
        if (isAnimStart) {
            return super.onTouchEvent(event);
        }
        //触摸点与初始点中点（相对于父View）
        anchorX = (event.getX() + startX) / 2;
        anchorY = (event.getY() + startY) / 2;
        x = event.getX();
        y = event.getY();
        return true;
    }


}
