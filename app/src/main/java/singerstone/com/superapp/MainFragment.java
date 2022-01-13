package singerstone.com.superapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import singerstone.com.annotations.BindView;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.Accessbility.AccessbilityFragmrnt;
import singerstone.com.superapp.BezierPaopao.BezierPaopaoFragment;
import singerstone.com.superapp.Marquee.MarqueeFragment;
import singerstone.com.superapp.ServiceIPC.ServiceIPCActivity;
import singerstone.com.superapp.Star.StarFragment;
import singerstone.com.superapp.TouchEvent.TouchEventFragment;
import singerstone.com.superapp.backscrollimage.FragmentScrollImage;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.book.BookFragment;
import singerstone.com.superapp.circlepkprogressView.PKAnimationFragment;
import singerstone.com.superapp.https.HttpsTestFragment;
import singerstone.com.superapp.inke.InkeFragment;
import singerstone.com.superapp.like.LikeViewFragment;
import singerstone.com.superapp.log.AppLog;
import singerstone.com.superapp.log.LogConfig;
import singerstone.com.superapp.log.LogUtil;
import singerstone.com.superapp.ndktest.NDKFragment;
import singerstone.com.superapp.opengl.OpenGlFragment;
import singerstone.com.superapp.qqlive.QQLiveTestFragment;
import singerstone.com.superapp.scroller.ScrollerFragment;
import singerstone.com.superapp.socketretrofit.Singerstone;
import singerstone.com.superapp.socketretrofit.SocketService;
import singerstone.com.superapp.treeholeview.TreeholeViewFragment;
import singerstone.com.superapp.upcoming.UpComingFragment;
import singerstone.com.superapp.utils.L;
import singerstone.com.superapp.waveeffect.WaveFragment;
import singerstone.com.superapp.xposed.XposedFragment;

/**
 * Created by chenbinhao on 2017/7/5. YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MainFragment extends BaseFragment implements GestureDetector.OnGestureListener {

    @BindView(R.id.rv_tools)
    public SuperScrollRecyclerView rv_tools;
    @BindView(R.id.btn_test)
    public Button btnTest;
    ArrayList<ToolItem> items;
    ToolAdapter toolAdapter;


    int position = 0;

    // TODO: 2017/7/5 在这里设置传值
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initData();
        initView(view);
        L.i(getTotalRam(getActivity()));
        Log.d("yogachen", "debug");
        return view;
    }

    public static String getTotalRam(Context context) {//GB
        String path = "/proc/meminfo";
        String firstLine = null;
        int totalRam = 0;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader, 8192);
            firstLine = br.readLine().split("\\s+")[1];
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (firstLine != null) {
            totalRam = (int) Math
                    .ceil((new Float(Float.valueOf(firstLine) / (1024 * 1024)).doubleValue()));
        }

        return totalRam + "GB";//返回1GB/2GB/3GB/4GB
    }

    private void initView(View view) {
        ViewInject.inject(this, view);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1,
                RecyclerView.VERTICAL, false);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
        // false);
        //rv_tools = view.findViewById(R.id.rv_tools);

        rv_tools.setLayoutManager(layoutManager);
        toolAdapter = new ToolAdapter(getActivity(), items);
        rv_tools.setAdapter(toolAdapter);// 第二个参数
        //new HorientalGridSnapHelper(3).attachToRecyclerView(rv_tools);
        toolAdapter.setOnItemClickListener((position, itemView) -> {
            switch (position) {
                case 0:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(StarFragment.newInstance());
                    break;
                case 1:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(BezierPaopaoFragment.newInstance());
                    break;
                case 2:
                    Singerstone.getInstance().create(SocketService.class).commit2("cbh", "hello")
                            .subscribe(s -> Toast.makeText(getActivity(), s, Toast.LENGTH_LONG));
                    break;
                case 3:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(WaveFragment.newInstance());
                    break;
                case 4:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(TouchEventFragment.newInstance());
                    break;
                case 5:
                    /*Toast.makeText(getActivity(),
                            NdkInterface.genMallocOOM()
                            , Toast.LENGTH_SHORT).show();*/
                    // NdkInterface.genCrash();

                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(NDKFragment.newInstance());
                    break;
                case 6:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(PKAnimationFragment.newInstance());
                    break;
                case 7:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(AccessbilityFragmrnt.newInstance());
                    break;
                case 8:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(MarqueeFragment.newInstance());
                    break;
                case 9:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(TreeholeViewFragment.newInstance());
                    break;
                case 10:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(LikeViewFragment.newInstance());
                    break;
                case 11:
                    startActivity(new Intent(getActivity(), ServiceIPCActivity.class));
                    break;
                case 12:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(FragmentScrollImage.newInstance());
                    break;
                case 13:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(BookFragment.newInstance());
                    break;
                case 14:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(InkeFragment.newInstance());
                    break;
                case 15:
                    Uri uri = Uri.parse("content://com.singerstone.provider");
                    getActivity().getContentResolver().call(uri, "test", null, null);
                    break;
                case 16:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(HttpsTestFragment.newInstance());
                    break;
                case 17:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(new OpenGlFragment());
                    break;
                case 18:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(QQLiveTestFragment.newInstance());
                    break;
                case 19:
                    AppLog.i("yogachen", "xlog 的log");
                    AppLog.flush(false);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    AppLog.packLog(bos);
                    String targetFilePath = LogConfig.getLogFolder() + File.separator + "log.zip";
                    boolean result = LogUtil.write2File(bos.toByteArray(), targetFilePath);
                    AppLog.i("yogachen", "result=" + result);
                    break;
                case 20:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(UpComingFragment.newInstance());
                case 21:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(XposedFragment.newInstance());
                case 22:
                    ((MainActivity) getActivity())
                            .setFragmentAddToBackStack(ScrollerFragment.newInstance());
                default:
                    break;
            }
        });
        rv_tools.addOnScrollListener(scrollListener);
        // btnTest=view.findViewById(R.id.btn_test);
        btnTest.setOnClickListener(v -> rv_tools.scrollToPosition(position + 3));
    }

    /**
     * S事实
     */

    private void initData() {
        items = new ArrayList<>();
        int index = 0;
        items.add(new ToolItem(R.drawable.default_tool, "满天星，打赏效果" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "水滴拉伸几何展示" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "仿Retrofit实现Socket收发请求" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "百度波浪进度条" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "VIEW事件传递LOG" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "NDK测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "手Y PK条View" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Accessbility模拟点击" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "跑马灯View" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "手Y树洞踢人UI" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "积分器UI，点赞效果" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Service通信（IPC）" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "仿知乎广告背景图" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "电子书翻页效果" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "映客答题接口测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Small插件化测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Https双向验证" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "OpenGl瞎搞几下" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "腾讯视频测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "XLog测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "即将上映demo" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Xposed测试" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Fling Scroller测试" + index++));
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            L.i("onScrollStateChanged:>>>>>>>>>>>>>>" + newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                LinearLayoutManager linearManager = (LinearLayoutManager) recyclerView
                        .getLayoutManager();
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                int lastItemPosition = linearManager.findLastVisibleItemPosition();
                View firstView = linearManager.findViewByPosition(firstItemPosition);
                View lastView = linearManager.findViewByPosition(lastItemPosition);
                //只需要检测第一个和最后一个可见的Item，中间的默认全部可见
                if (getVisibilityPercents(firstView) > 50) {

                }
                if (getVisibilityPercents(lastView) > 50) {

                }
                L.i("onScrollStateChanged",
                        firstItemPosition + " " + lastItemPosition + "，" + getVisibilityPercents(
                                firstView) + "   " + getVisibilityPercents(lastView));
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            L.i("onScrolled :" + dx + " " + dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastCompletelyVisibleItemPosition();
                if (layoutManager.getItemCount() - 1 == lastCompletelyVisibleItemPosition) {
                    L.i("onScrolled : scroller to bottom");
                }
            } else {
                throw new RuntimeException("Unsupported LayoutManager.");
            }

        }
    };

    /**
     * 判断当前View展示的百分比
     *
     * @param view
     * @return
     */
    public int getVisibilityPercents(View view) {
        if (view == null) {
            L.e("view==null");
            return 0;
        }
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        int height = view.getHeight();
        int width = view.getWidth();
        int percents;
        percents = (rect.right - rect.left) * (rect.bottom - rect.top) * 100 / (width * height);
        return percents;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        L.i("GestureDetector onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        L.i("GestureDetector onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        L.i("GestureDetector onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        L.i("GestureDetector onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        L.i("GestureDetector onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        L.i("GestureDetector onFling " + velocityX);
        return false;
    }
}

