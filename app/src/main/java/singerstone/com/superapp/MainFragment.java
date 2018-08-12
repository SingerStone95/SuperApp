package singerstone.com.superapp;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.wequick.small.Small;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
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
import singerstone.com.superapp.opengl.OpenGlFragment;
import singerstone.com.superapp.socketretrofit.Singerstone;
import singerstone.com.superapp.socketretrofit.SocketService;
import singerstone.com.superapp.treeholeview.TreeholeViewFragment;
import singerstone.com.superapp.waveeffect.WaveFragment;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
 */

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MainFragment extends BaseFragment {

    @BindView(R.id.rv_tools)
    RecyclerView rv_tools;
    ArrayList<ToolItem> items;
    ToolAdapter toolAdapter;

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
        return view;
    }

    private void initView(View view) {
        rv_tools = (RecyclerView) view.findViewById(R.id.rv_tools);
        ViewInject.inject(this, rv_tools);
        rv_tools.setLayoutManager(new LinearLayoutManager(getActivity()));//第一个参数 //线性
        toolAdapter = new ToolAdapter(getActivity(), items);
        rv_tools.setAdapter(toolAdapter);// 第二个参数
        rv_tools.addItemDecoration(new DividerGridItemDecoration(getActivity()));//第三个参数（上面定义的diver）
        toolAdapter.setOnItemClickListener(new ToolAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {
                switch (position) {
                    case 0:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(StarFragment.newInstance());
                        break;
                    case 1:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(BezierPaopaoFragment.newInstance());
                        break;
                    case 2:
                        Singerstone.getInstance().create(SocketService.class).commit2("cbh", "hello")
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG);
                                    }
                                });
                        break;
                    case 3:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(WaveFragment.newInstance());
                        break;
                    case 4:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(TouchEventFragment.newInstance());
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), KotlinActivity.class));
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
                        /**
                         * 显示启动
                         */
                        //startActivity(new Intent(getActivity(), RNActivity.class));
                        /**
                         * 隐示启动
                         */
                        Small.openUri("rn", getActivity());
                        break;
                    case 10:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(TreeholeViewFragment.newInstance());
                        break;
                    case 11:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(LikeViewFragment.newInstance());
                        break;
                    case 12:
                        startActivity(new Intent(getActivity(), ServiceIPCActivity.class));
                        break;
                    case 13:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(FragmentScrollImage.newInstance());
                        break;
                    case 14:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(BookFragment.newInstance());
                        break;
                    case 15:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(InkeFragment.newInstance());
                        break;
                    case 16:
                        Small.openUri("me", getActivity());
                        break;
                    case 17:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(HttpsTestFragment.newInstance());
                        break;
                    case 18:
                        ((MainActivity) getActivity()).setFragmentAddToBackStack(new OpenGlFragment());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        items = new ArrayList<>();
        items.add(new ToolItem(R.drawable.default_tool, "满天星，打赏效果"));
        items.add(new ToolItem(R.drawable.default_tool, "水滴拉伸几何展示"));
        items.add(new ToolItem(R.drawable.default_tool, "仿Retrofit实现Socket收发请求"));
        items.add(new ToolItem(R.drawable.default_tool, "百度波浪进度条"));
        items.add(new ToolItem(R.drawable.default_tool, "VIEW事件传递LOG"));
        items.add(new ToolItem(R.drawable.default_tool, "Kotlin界面"));
        items.add(new ToolItem(R.drawable.default_tool, "手Y PK条View"));
        items.add(new ToolItem(R.drawable.default_tool, "Accessbility模拟点击"));
        items.add(new ToolItem(R.drawable.default_tool, "跑马灯View"));
        items.add(new ToolItem(R.drawable.default_tool, "ReactNative"));
        items.add(new ToolItem(R.drawable.default_tool, "手Y树洞踢人UI"));
        items.add(new ToolItem(R.drawable.default_tool, "积分器UI，点赞效果"));
        items.add(new ToolItem(R.drawable.default_tool, "Service通信（IPC）"));
        items.add(new ToolItem(R.drawable.default_tool, "仿知乎广告背景图"));
        items.add(new ToolItem(R.drawable.default_tool, "电子书翻页效果"));
        items.add(new ToolItem(R.drawable.default_tool, "映客答题接口测试"));
        items.add(new ToolItem(R.drawable.default_tool, "Small插件化测试"));
        items.add(new ToolItem(R.drawable.default_tool, "Https双向验证"));
        items.add(new ToolItem(R.drawable.default_tool, "OpenGl瞎搞几下"));
    }

    class Test {
        public void test() {

        }
    }
}

