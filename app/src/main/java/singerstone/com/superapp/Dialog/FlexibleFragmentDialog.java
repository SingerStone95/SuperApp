package singerstone.com.superapp.Dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import singerstone.com.superapp.R;
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
    private CharSequence mContentText;
    private int mTitleBackgroundColor = Color.parseColor("#d6ab56");
    private int mContentBackgroundColor = Color.WHITE;

    private List<IDialogItem> mChooseItems = new ArrayList<>();

    private LinearLayout mContainer;
    private TextView mTvTitle;
    private TextView mTvContent;
    private RecyclerView mRecyclerView;
    private DialogItemAdapter mAdapter;


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

    public FlexibleFragmentDialog setContentText(CharSequence mContentText) {
        this.mContentText = mContentText;
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

    public FlexibleFragmentDialog setDialogItemData(List<IDialogItem> data) {
        this.mChooseItems.clear();
        this.mChooseItems.addAll(data);
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.TransparentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_flexble_fragmentdialog, null, false);
        initDialog();
        initView(rootView);
        initConfig();
        initData();
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
        mTvContent = rootView.findViewById(R.id.tv_content);
        mRecyclerView = rootView.findViewById(R.id.rv_choose_items);
        mContainer = rootView.findViewById(R.id.rl_root);
    }

    private void initConfig() {
        mTvTitle.setBackgroundColor(mTitleBackgroundColor);
        mTvContent.setBackgroundColor(mContentBackgroundColor);
        mRecyclerView.setBackgroundColor(mContentBackgroundColor);
        mContainer.getLayoutParams().width = DimentionUtils.dip2px(getActivity(), mWidth);
        mContainer.getLayoutParams().height = DimentionUtils.dip2px(getActivity(), mHeight);
        mTvTitle.getLayoutParams().height = DimentionUtils.dip2px(getActivity(), mTitleHeight);
        if (mTitleText != null && !"".equals(mTitleText)) {
            mTvTitle.setText(mTitleText);
        } else {
            mTvTitle.setVisibility(View.GONE);
        }
        if (mContentText != null && !mContentText.equals("")) {
            mTvContent.setText(mContentText);
        } else {
            mTvContent.setVisibility(View.GONE);
        }
    }

    private void initData() {
        if (mChooseItems.size() > 0) {
            mAdapter = new DialogItemAdapter(getActivity());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addItemDecoration(decoration);
            mAdapter.setData(mChooseItems);
            mAdapter.setListener(mListener);
        }
    }

    private DialogItemAdapter.OnItemClickListener mListener = new DialogItemAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position, IDialogItem item) {
            if (item.getClickListener() != null) {
                item.getClickListener().onDialogItemClick(FlexibleFragmentDialog.this);
            }
        }
    };

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (Throwable t) {

        }
    }

    @Override
    public void dismiss() {
        try {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.remove(this);
            ft.commitAllowingStateLoss();
        } catch (Throwable t) {

        }
    }

    public interface OnDialogItemClickListener {
        void onDialogItemClick(DialogFragment dialog);
    }
}
