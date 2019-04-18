package singerstone.com.superapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import singerstone.com.annotations.BindView;
import singerstone.com.inject.ViewInject;
import singerstone.com.superapp.Accessbility.AccessbilityFragmrnt;
import singerstone.com.superapp.BezierPaopao.BezierPaopaoFragment;
import singerstone.com.superapp.Dialog.FlexibleFragmentDialog;
import singerstone.com.superapp.Dialog.IDialogItem;
import singerstone.com.superapp.Marquee.MarqueeFragment;
import singerstone.com.superapp.ServiceIPC.ServiceIPCActivity;
import singerstone.com.superapp.TouchEvent.TouchEventFragment;
import singerstone.com.superapp.backscrollimage.FragmentScrollImage;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.book.BookFragment;
import singerstone.com.superapp.circlepkprogressView.PKAnimationFragment;
import singerstone.com.superapp.https.HttpsTestFragment;
import singerstone.com.superapp.inke.InkeFragment;
import singerstone.com.superapp.like.LikeViewFragment;
import singerstone.com.superapp.ndkinterface.NdkInterface;
import singerstone.com.superapp.opengl.OpenGlFragment;
import singerstone.com.superapp.qqlive.QQLiveTestFragment;
import singerstone.com.superapp.socketretrofit.Singerstone;
import singerstone.com.superapp.socketretrofit.SocketService;
import singerstone.com.superapp.treeholeview.SpannableStringUtils;
import singerstone.com.superapp.treeholeview.TreeholeViewFragment;
import singerstone.com.superapp.utils.L;
import singerstone.com.superapp.waveeffect.WaveFragment;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initData();
        initView(view);
        L.i(getTotalRam(getActivity()));
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
            totalRam = (int) Math.ceil((new Float(Float.valueOf(firstLine) / (1024 * 1024)).doubleValue()));
        }

        return totalRam + "GB";//返回1GB/2GB/3GB/4GB
    }

    private void initView(View view) {
        ViewInject.inject(this, view);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_tools.setLayoutManager(layoutManager);
        toolAdapter = new ToolAdapter(getActivity(), items);
        rv_tools.setAdapter(toolAdapter);// 第二个参数
        //new HorientalGridSnapHelper(3).attachToRecyclerView(rv_tools);
        toolAdapter.setOnItemClickListener((position, itemView) -> {
            switch (position) {
                case 0:
                    // ((MainActivity) getActivity()).setFragmentAddToBackStack(StarFragment.newInstance());
                    FlexibleFragmentDialog dialog = new FlexibleFragmentDialog();
                    ArrayList<IDialogItem> items = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        final int finalI = i;
                        items.add(new IDialogItem() {
                            @Override
                            public CharSequence getText() {
                                SpannableStringBuilder title = SpannableStringUtils.getBuilder("标题").setBold().setForegroundColor(getResources().getColor(R.color.yellow)).setAlign(Layout.Alignment.ALIGN_CENTER)
                                        .append("\n")
                                        .append("第" + finalI + "行")
                                        .create();
                                return title;
                            }

                            @Override
                            public FlexibleFragmentDialog.OnDialogItemClickListener getClickListener() {
                                return null;
                            }
                        });
                    }
                    dialog.setDialogItemData(items);
                    dialog.show(getFragmentManager(), "FlexibleFragmentDialog");
                    break;
                case 1:

                    ((MainActivity) getActivity()).setFragmentAddToBackStack(BezierPaopaoFragment.newInstance());
                    break;
                case 2:
                    Singerstone.getInstance().create(SocketService.class).commit2("cbh", "hello")
                            .subscribe(s -> Toast.makeText(getActivity(), s, Toast.LENGTH_LONG));
                    break;
                case 3:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(WaveFragment.newInstance());
                    break;
                case 4:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(TouchEventFragment.newInstance());
                    break;
                case 5:
                    Toast.makeText(getActivity(), NdkInterface.getServiceName(), Toast.LENGTH_LONG).show();

                    break;
                case 6:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(PKAnimationFragment.newInstance());
                    break;
                case 7:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(AccessbilityFragmrnt.newInstance());
                    break;
                case 8:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(MarqueeFragment.newInstance());
                    break;
                case 9:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(TreeholeViewFragment.newInstance());
                    break;
                case 10:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(LikeViewFragment.newInstance());
                    break;
                case 11:
                    startActivity(new Intent(getActivity(), ServiceIPCActivity.class));
                    break;
                case 12:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(FragmentScrollImage.newInstance());
                    break;
                case 13:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(BookFragment.newInstance());
                    break;
                case 14:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(InkeFragment.newInstance());
                    break;
                case 15:
                    // Small.openUri("me", getActivity());
                    Uri uri = Uri.parse("content://com.singerstone.provider");
                    getActivity().getContentResolver().call(uri, "test", null, null);
                    break;
                case 16:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(HttpsTestFragment.newInstance());
                    break;
                case 17:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(new OpenGlFragment());
                    break;
                case 18:
                    ((MainActivity) getActivity()).setFragmentAddToBackStack(QQLiveTestFragment.newInstance());
                default:
                    break;
            }
        });
        rv_tools.addOnScrollListener(scrollListener);
        btnTest.setOnClickListener(v -> rv_tools.scrollToPosition(position + 3));
    }


    private void initData() {
        items = new ArrayList<>();
        int index = 0;
        items.add(new ToolItem(R.drawable.default_tool, "满天星，打赏效果" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "水滴拉伸几何展示" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "仿Retrofit实现Socket收发请求" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "百度波浪进度条" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "VIEW事件传递LOG" + index++));
        items.add(new ToolItem(R.drawable.default_tool, "Kotlin界面" + index++));
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
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            L.i("onScrollStateChanged:>>>>>>>>>>>>>>" + newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                LinearLayoutManager linearManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                int lastItemPosition = linearManager.findLastVisibleItemPosition();
                View firstView = linearManager.findViewByPosition(firstItemPosition);
                View lastView = linearManager.findViewByPosition(lastItemPosition);
                //只需要检测第一个和最后一个可见的Item，中间的默认全部可见
                if (getVisibilityPercents(firstView) > 50) {

                }
                if (getVisibilityPercents(lastView) > 50) {

                }
                L.i("onScrollStateChanged", firstItemPosition + " " + lastItemPosition + "，" + getVisibilityPercents(firstView) + "   " + getVisibilityPercents(lastView));
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            L.i("onScrolled :" + dx + " " + dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
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

