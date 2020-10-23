package singerstone.com.superapp.treeholeview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;

/**
 * Created by chenbinhao on 2017/9/27.
 * YY:909075276
 */

public class TreeholeViewFragment extends BaseFragment {
    TreeHoleAnimationView treeholeProgressView;
    Button btn;
    int count = 0;
    TextView tv;

    public static TreeholeViewFragment newInstance() {
        TreeholeViewFragment fragment = new TreeholeViewFragment();
        return fragment;
    }

    public TreeholeViewFragment() {

    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treeholeview, container, false);
        treeholeProgressView = (TreeHoleAnimationView) view.findViewById(R.id.treehole_progress);
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeholeProgressView.setCount(count);
                count++;
            }
        });
        treeholeProgressView.setMaxCount(20);
        treeholeProgressView.setBkgBitmapResId(R.drawable.icon_liveroom);
        treeholeProgressView.setColorRes(R.color.treehole);
        tv = (TextView) view.findViewById(R.id.tv);
        SpannableStringUtils.context = getActivity().getApplicationContext();
/*
        SpannableStringBuilder s = SpannableStringUtils.getBuilder("123456").setBold().setForegroundColor(getResources().getColor(R.color.yellow)).setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("center").setResourceId(R.drawable.frame_anonymous_follow_liveroom)
                .append("1231231").create();*/
        SpannableStringBuilder spannable = new SpanUtils()
                .append("直接到换行")
                .setClickSpan(new PressableClickSpan() {
                    @Override
                    protected int getPressedColor() {
                        return 0;
                    }

                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getActivity(), "发生了点击效果1", Toast.LENGTH_SHORT).show();
                    }
                })
                .setFontSize(dp2px(getActivity(), 22))
                .setBold()
                .setForegroundColor(getResources().getColor(R.color.green))
                .append("直接到换行a12432直接到换行a12432直接到换行a12432直接到换行a12432直接到换行a12432直接到换行a12432直接到换行换行a12432直接到换行换行a12432直接到换行换行a12432直接到换行")
                .setFontSize(dp2px(getActivity(), 20))
                .setClickSpan(new PressableClickSpan() {
                    @Override
                    protected int getPressedColor() {
                        return 0;
                    }

                    @Override
                    public void onClick(@NonNull View widget) {
                        Toast.makeText(getActivity(), "发生了点击效果12", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();

        tv.setText(SpannableString.valueOf(spannable));
        tv.setHighlightColor(Color.TRANSPARENT);
      //  tv.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

    class MyClickText extends ClickableSpan {
        private Context context;

        public MyClickText(Context context) {
            this.context = context;
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.clearShadowLayer();

        }

        @Override
        public void onClick(View widget) {
            Toast.makeText(context, "发生了点击效果", Toast.LENGTH_SHORT).show();
        }
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
