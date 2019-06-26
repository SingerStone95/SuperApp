package singerstone.com.superapp.Star;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;



/**
 * Created by chenbinhao on 2017/7/3.
 * YY:909075276
 */

public class ComboViewController {
    public static ComboViewController instance;
    private ViewGroup rootView;
    private Context mContext;
    private ComboView comboView;
    private RelativeLayout.LayoutParams comboLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


    public static ComboViewController getInstance(Context context) {
        if (instance == null) {
            instance = new ComboViewController();
        }
        instance.mContext = context;

        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void showComboWithPixMargin(int bottomMargin, int rightMargin, ViewGroup viewGroup, int mun_combo) {
        if (rootView != null && comboView != null) {
            rootView.removeView(comboView);
            comboView = null;
        }
        rootView = viewGroup;
        if (rootView != null && mContext != null) {
            if (comboView == null) {
                comboView = new ComboView(mContext);
            }
            comboView.setVisibility(View.VISIBLE);
            comboView.setClipChildren(false);
            comboLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            comboLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            comboLp.rightMargin = rightMargin;
            comboLp.bottomMargin = bottomMargin;
            rootView.addView(comboView, comboLp);
            comboView.showComboMun(mun_combo);
            comboView.clearAnimation();
            AnimatorSet animatorSet = new AnimatorSet();//组合动画
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(comboView, "scaleX", 1.2f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(comboView, "scaleY", 1.2f, 1f);
            scaleX.setDuration(150);
            scaleY.setDuration(150);
            animatorSet.setInterpolator(new AccelerateInterpolator());
            animatorSet.play(scaleX).with(scaleY);//3个动画同时开始
            animatorSet.start();
        }
    }

    public void hide() {
        comboView.hideView();
    }


}
