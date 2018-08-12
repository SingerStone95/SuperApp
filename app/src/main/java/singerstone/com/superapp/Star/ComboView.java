package singerstone.com.superapp.Star;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import singerstone.com.superapp.R;


/**
 * Created by chenbinhao on 2017/7/3.
 * YY:909075276
 */

public class ComboView extends RelativeLayout {
    private Context mContext;
    private View comboView;
    //最大连击99999,超过不能正常显示
    private ImageView imageView_myriabit;//万位
    private ImageView imageView_thousand;//千位
    private ImageView imageView_hundreds;//百位
    private ImageView imageView_decade;//十位
    private ImageView imageView_unit;//个位

    public ComboView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public ComboView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        comboView = LayoutInflater.from(mContext).inflate(R.layout.layout_combo, null);
        comboView.setLayoutParams(lp);
        addView(comboView);
        imageView_myriabit=(ImageView) comboView.findViewById(R.id.iv_myriabit);
        imageView_thousand=(ImageView) comboView.findViewById(R.id.iv_thousand);
        imageView_hundreds = (ImageView) comboView.findViewById(R.id.iv_hundreds);
        imageView_decade = (ImageView) comboView.findViewById(R.id.iv_decade);
        imageView_unit = (ImageView) comboView.findViewById(R.id.iv_unit);
    }

    public void showComboMun(int comboNum) {
        int myriabit = 0;
        int thousand = 0;
        int hundreds = 0;
        int decade = 0;
        int unit = 0;
        myriabit = comboNum / 10000;
        thousand = comboNum / 1000 % 10;
        hundreds = comboNum / 100 % 10;
        decade = comboNum / 10 % 10;
        unit = comboNum % 10;
        if (comboNum >= 1 && comboNum < 10) {
            setImageNum(unit, imageView_unit);
        } else if (comboNum >= 10 && comboNum < 100) {
            setImageNum(unit, imageView_unit);
            setImageNum(decade, imageView_decade);
        } else if (comboNum >= 100 && comboNum < 1000) {
            setImageNum(unit, imageView_unit);
            setImageNum(decade, imageView_decade);
            setImageNum(hundreds, imageView_hundreds);
        }else if (comboNum >= 1000 && comboNum < 10000) {
            setImageNum(unit, imageView_unit);
            setImageNum(decade, imageView_decade);
            setImageNum(hundreds, imageView_hundreds);
            setImageNum(thousand, imageView_thousand);
        }else if (comboNum >= 10000 && comboNum < 100000) {
            setImageNum(unit, imageView_unit);
            setImageNum(decade, imageView_decade);
            setImageNum(hundreds, imageView_hundreds);
            setImageNum(thousand, imageView_thousand);
            setImageNum(myriabit, imageView_myriabit);
        }

    }

    public void hideView() {
        if (comboView != null) {
            comboView.setVisibility(GONE);
        }
    }

    private void setImageNum(int num, ImageView imageView) {
        switch (num) {
            case 0:
                imageView.setBackgroundResource(R.mipmap.combo_0);
                break;
            case 1:
                imageView.setBackgroundResource(R.mipmap.combo_1);
                break;
            case 2:
                imageView.setBackgroundResource(R.mipmap.combo_2);
                break;
            case 3:
                imageView.setBackgroundResource(R.mipmap.combo_3);
                break;
            case 4:
                imageView.setBackgroundResource(R.mipmap.combo_4);
                break;
            case 5:
                imageView.setBackgroundResource(R.mipmap.combo_5);
                break;
            case 6:
                imageView.setBackgroundResource(R.mipmap.combo_6);
                break;
            case 7:
                imageView.setBackgroundResource(R.mipmap.combo_7);
                break;
            case 8:
                imageView.setBackgroundResource(R.mipmap.combo_8);
                break;
            case 9:
                imageView.setBackgroundResource(R.mipmap.combo_9);
                break;
            default:
                break;

        }
    }
}
