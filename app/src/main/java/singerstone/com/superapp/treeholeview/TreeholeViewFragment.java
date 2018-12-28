package singerstone.com.superapp.treeholeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        SpannableStringBuilder s = SpannableStringUtils.getBuilder("123456").setBold().setForegroundColor(getResources().getColor(R.color.yellow)).setAlign(Layout.Alignment.ALIGN_CENTER)
                .append("center").setResourceId(R.drawable.frame_anonymous_follow_liveroom)
                .append("1231231").create();
        SpannableStringBuilder spannable = new SpanUtils().append("a12432")
                .append("2233333")
                .setBold()
                .setForegroundColor(getResources().getColor(R.color.yellow))
                .create();
        tv.setText(spannable);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CharSequence text = ((TextView) v).getText();
                Spannable sText = Spannable.Factory.getInstance().newSpannable(text);
                TextView widget = (TextView) v;

                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP ||
                        action == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    x -= widget.getTotalPaddingLeft();
                    y -= widget.getTotalPaddingTop();

                    x += widget.getScrollX();
                    y += widget.getScrollY();

                    Layout layout = widget.getLayout();
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);

                    CenterAlignImageSpan[] imageSpans = sText.getSpans(off, off, CenterAlignImageSpan.class);
                    if (imageSpans.length != 0) {
                        if (action == MotionEvent.ACTION_UP) {

                        }
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

}
