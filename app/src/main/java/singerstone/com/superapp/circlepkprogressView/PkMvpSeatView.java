package singerstone.com.superapp.circlepkprogressView;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import singerstone.com.superapp.R;

/**
 * Created by chenbinhao on 2018/5/3.
 * YY:909075276
 * 坐席组合View
 */

public class PkMvpSeatView extends RelativeLayout {
    private ImageView ivUsHead1;
    private ImageView ivUsHead2;
    private ImageView ivUsHead3;
    private ImageView ivUsHead4;

    private ImageView ivThemHead1;
    private ImageView ivThemHead2;
    private ImageView ivThemHead3;
    private ImageView ivThemHead4;

    private TextView tvRankUs1;
    private TextView tvRankUs2;
    private TextView tvRankUs3;
    private TextView tvRankUs4;

    public PkMvpSeatView(Context context) {
        super(context);
        initView(context);
    }

    public PkMvpSeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PkMvpSeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_pk_mvp_seatview, this, true);
        ivUsHead1 = (ImageView) findViewById(R.id.iv_pk_head_us_1);
        ivUsHead2 = (ImageView) findViewById(R.id.iv_pk_head_us_2);
        ivUsHead3 = (ImageView) findViewById(R.id.iv_pk_head_us_3);
        ivUsHead4 = (ImageView) findViewById(R.id.iv_pk_head_us_4);

        ivThemHead1 = (ImageView) findViewById(R.id.iv_pk_head_them_1);
        ivThemHead2 = (ImageView) findViewById(R.id.iv_pk_head_them_2);
        ivThemHead3 = (ImageView) findViewById(R.id.iv_pk_head_them_3);
        ivThemHead4 = (ImageView) findViewById(R.id.iv_pk_head_them_4);

        tvRankUs1 = (TextView) findViewById(R.id.tv_pk_rank_us_1);
        tvRankUs2 = (TextView) findViewById(R.id.tv_pk_rank_us_2);
        tvRankUs3 = (TextView) findViewById(R.id.tv_pk_rank_us_3);
        tvRankUs4 = (TextView) findViewById(R.id.tv_pk_rank_us_4);

    }


}
