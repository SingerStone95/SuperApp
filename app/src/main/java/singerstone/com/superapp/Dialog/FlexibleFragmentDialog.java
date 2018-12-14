package singerstone.com.superapp.Dialog;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;
import singerstone.com.superapp.treeholeview.SpannableStringUtils;
import singerstone.com.superapp.utils.DimentionUtils;


public class FlexibleFragmentDialog extends DialogFragment {

    private static int DEFAULT_CORNERRADIUS = 15; //dp
    private static int DEFAULt_WIDTH = 300;
    private static int DEFULT_HEIGHT = 350;
    private static int DEFAULT_TITLE_HEIGHT = 80;
    private static float DEFAULT_DIMAMOUNT = 0.5f;

    private int mBackgroundColor = Color.TRANSPARENT;
    private float mDimAmount = DEFAULT_DIMAMOUNT; //背景灰度0-1
    private boolean mOutsideCancelable = false;
    private int mCornersRadius = DEFAULT_CORNERRADIUS;
    private int mWidth = DEFAULt_WIDTH;
    private int mHeight = DEFULT_HEIGHT;
    private int mTitleHeight = DEFAULT_TITLE_HEIGHT;
    private CharSequence mTitleText; //支持SpanableString富文本
    private int mTitleBackgroundColor = Color.parseColor("#d6ab56");
    private int mContentBackgroundColor = Color.WHITE;

    private List<DialogChooseItem> mChooseItems = new ArrayList<>();

    private RelativeLayout mContainer;
    private TextView mTvTitle;
    private RecyclerView mRecyclerView;


    public FlexibleFragmentDialog setBackgroundColor(@ColorInt int color) {
        this.mBackgroundColor = color;
        return this;
    }

    public FlexibleFragmentDialog setDimAmount(float mDimAmount) {
        this.mDimAmount = mDimAmount;
        return this;
    }

    public FlexibleFragmentDialog setOutsideCancelable(boolean mOutsideCancelable) {
        this.mOutsideCancelable = mOutsideCancelable;
        return this;
    }

    public FlexibleFragmentDialog setCornersRadius(int mCornersRadius) {
        this.mCornersRadius = mCornersRadius;
        return this;
    }

    public FlexibleFragmentDialog setWidth(int mWidth) {
        this.mWidth = mWidth;
        return this;
    }

    public FlexibleFragmentDialog setHeight(int mHeight) {
        this.mHeight = mHeight;
        return this;
    }

    public FlexibleFragmentDialog setTitleHeight(int mTitleHeight) {
        this.mTitleHeight = mTitleHeight;
        return this;
    }

    public FlexibleFragmentDialog setTitleText(CharSequence mTitleText) {
        this.mTitleText = mTitleText;
        return this;
    }

    public FlexibleFragmentDialog setTitleBackgroundColor(@ColorInt int mTitleBackgroundColor) {
        this.mTitleBackgroundColor = mTitleBackgroundColor;
        return this;
    }

    public FlexibleFragmentDialog setContentBackgroundColor(@ColorInt int mContentBackgroundColor) {
        this.mContentBackgroundColor = mContentBackgroundColor;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_flexble_fragmentdialog, null, false);
        initDialog();
        initView(rootView);
        initConfig();
        return rootView;
    }

    private void initDialog() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(mBackgroundColor));
            getDialog().setCanceledOnTouchOutside(mOutsideCancelable);
            getDialog().getWindow().setDimAmount(mDimAmount);
            //getDialog().getWindow().
            //圆角
            int radius = DimentionUtils.dip2px(getActivity(), mCornersRadius);
            float[] CornerRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
            RoundRectShape roundRectShape = new RoundRectShape(CornerRadius, null, null);
            ShapeDrawable shape = new ShapeDrawable(roundRectShape);
            getDialog().getWindow().setBackgroundDrawable(shape);
        }
    }

    private void initView(View rootView) {
        mTvTitle = rootView.findViewById(R.id.tv_title);
        mRecyclerView = rootView.findViewById(R.id.rv_choose_items);
        mContainer = rootView.findViewById(R.id.rl_root);

    }

    private void initConfig() {
        mTvTitle.setBackgroundColor(mTitleBackgroundColor);
        mRecyclerView.setBackgroundColor(mContentBackgroundColor);
        mContainer.getLayoutParams().width = DimentionUtils.dip2px(getActivity(), mWidth);
        mContainer.getLayoutParams().height = DimentionUtils.dip2px(getActivity(), mHeight);
        mTvTitle.getLayoutParams().height = DimentionUtils.dip2px(getActivity(), mTitleHeight);
        // mTvTitle.setText(mTitleText);
        SpannableStringBuilder title = SpannableStringUtils.getBuilder("标题").setBold().setForegroundColor(getResources().getColor(R.color.yellow)).setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("\n")
                .append("第二行")
                .create();
        mTvTitle.setText(title);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commitAllowingStateLoss();
    }

}
