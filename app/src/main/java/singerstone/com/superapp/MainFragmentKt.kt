package singerstone.com.superapp

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import singerstone.com.superapp.Accessbility.AccessbilityFragmrnt
import singerstone.com.superapp.BezierPaopao.BezierPaopaoFragment
import singerstone.com.superapp.Marquee.MarqueeFragment
import singerstone.com.superapp.ServiceIPC.ServiceIPCActivity
import singerstone.com.superapp.Star.StarFragment
import singerstone.com.superapp.TouchEvent.TouchEventFragment
import singerstone.com.superapp.backscrollimage.FragmentScrollImage
import singerstone.com.superapp.base.BaseFragment
import singerstone.com.superapp.book.BookFragment
import singerstone.com.superapp.circlepkprogressView.PKAnimationFragment
import singerstone.com.superapp.https.HttpsTestFragment
import singerstone.com.superapp.inke.InkeFragment
import singerstone.com.superapp.like.LikeViewFragment
import singerstone.com.superapp.log.AppLog
import singerstone.com.superapp.log.LogConfig
import singerstone.com.superapp.log.LogUtil
import singerstone.com.superapp.ndktest.NDKFragment
import singerstone.com.superapp.opengl.OpenGlFragment
import singerstone.com.superapp.qqlive.QQLiveTestFragment
import singerstone.com.superapp.scroller.ScrollerFragment
import singerstone.com.superapp.socketretrofit.Singerstone
import singerstone.com.superapp.socketretrofit.SocketService
import singerstone.com.superapp.treeholeview.TreeholeViewFragment
import singerstone.com.superapp.upcoming.UpComingFragment
import singerstone.com.superapp.utils.L
import singerstone.com.superapp.waveeffect.WaveFragment
import singerstone.com.superapp.xposed.XposedFragment
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileReader
import java.util.*

/**
 * @des:
 * @author: yogachen
 * @date: 2022/3/27 19:22
 * @see {@link }
 */
class MainFragmentKt : BaseFragment(), GestureDetector.OnGestureListener {
    var items: ArrayList<ToolItem>? = null
    var toolAdapter: ToolAdapter? = null
    var position = 0

    companion object {

        @JvmStatic
        fun newInstance(): MainFragmentKt {
            return MainFragmentKt()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppLog.i("yogachen", "1")
        MainScope().launch(start = CoroutineStart.UNDISPATCHED) {
            AppLog.i("yogachen", "2")
            initData()
            delay(0)
            initView(view)
        }
        AppLog.i("yogachen", "3")
        L.i(getTotalRam(activity))

    }

    private fun getTotalRam(context: Context?): String? { //GB
        val path = "/proc/meminfo"
        var firstLine: String? = null
        var totalRam = 0
        try {
            val fileReader = FileReader(path)
            val br = BufferedReader(fileReader, 8192)
            firstLine = br.readLine().split("\\s+".toRegex()).toTypedArray()[1]
            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (firstLine != null) {
            totalRam = Math
                    .ceil(java.lang.Float.valueOf(firstLine) / (1024 * 1024).toDouble()).toInt()
        }
        return totalRam.toString() + "GB" //返回1GB/2GB/3GB/4GB
    }

    private fun initView(view: View) {
        val layoutManager = GridLayoutManager(activity, 1,
                RecyclerView.VERTICAL, false)
        //LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
        // false);
        //rv_tools = view.findViewById(R.id.rv_tools);
        rv_tools!!.layoutManager = layoutManager
        toolAdapter = ToolAdapter(activity, items)
        rv_tools!!.adapter = toolAdapter // 第二个参数
        //new HorientalGridSnapHelper(3).attachToRecyclerView(rv_tools);
        toolAdapter!!.setOnItemClickListener { position: Int, itemView: View? ->
            when (position) {
                0 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(StarFragment.newInstance())
                1 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(BezierPaopaoFragment.newInstance())
                2 -> Singerstone.getInstance().create(SocketService::class.java).commit2("cbh", "hello")
                        .subscribe { s: String? -> Toast.makeText(activity, s, Toast.LENGTH_LONG) }
                3 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(WaveFragment.newInstance())
                4 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(TouchEventFragment.newInstance())
                5 ->                     /*Toast.makeText(activity,
                            NdkInterface.genMallocOOM()
                            , Toast.LENGTH_SHORT).show();*/
                    // NdkInterface.genCrash();
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(NDKFragment.newInstance())
                6 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(PKAnimationFragment.newInstance())
                7 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(AccessbilityFragmrnt.newInstance())
                8 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(MarqueeFragment.newInstance())
                9 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(TreeholeViewFragment.newInstance())
                10 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(LikeViewFragment.newInstance())
                11 -> startActivity(Intent(activity, ServiceIPCActivity::class.java))
                12 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(FragmentScrollImage.newInstance())
                13 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(BookFragment.newInstance())
                14 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(InkeFragment.newInstance())
                15 -> {
                    val uri = Uri.parse("content://com.singerstone.provider")
                    activity?.contentResolver?.call(uri, "test", null, null)
                }
                16 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(HttpsTestFragment.newInstance())
                17 -> (activity as MainActivity).setFragmentAddToBackStack(OpenGlFragment())
                18 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(QQLiveTestFragment.newInstance())
                19 -> {
                    AppLog.i("yogachen", "xlog 的log")
                    AppLog.flush(false)
                    val bos = ByteArrayOutputStream()
                    AppLog.packLog(bos)
                    val targetFilePath = LogConfig.getLogFolder() + File.separator + "log.zip"
                    val result = LogUtil.write2File(bos.toByteArray(), targetFilePath)
                    AppLog.i("yogachen", "result=$result")
                }
                20 -> {
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(UpComingFragment.newInstance())
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(XposedFragment.newInstance())
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(ScrollerFragment.newInstance())
                }
                21 -> {
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(XposedFragment.newInstance())
                    (activity as MainActivity)
                            .setFragmentAddToBackStack(ScrollerFragment.newInstance())
                }
                22 -> (activity as MainActivity)
                        .setFragmentAddToBackStack(ScrollerFragment.newInstance())
                else -> {
                }
            }
        }
        rv_tools!!.addOnScrollListener(scrollListener)
        btn_test!!.setOnClickListener { v: View? -> rv_tools!!.scrollToPosition(position + 3) }
    }

    /**
     * S事实
     */
    private fun initData() {
        items = ArrayList()
        var index = 0
        items!!.add(ToolItem(R.drawable.default_tool, "满天星，打赏效果" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "水滴拉伸几何展示" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "仿Retrofit实现Socket收发请求" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "百度波浪进度条" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "VIEW事件传递LOG" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "NDK测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "手Y PK条View" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Accessbility模拟点击" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "跑马灯View" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "手Y树洞踢人UI" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "积分器UI，点赞效果" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Service通信（IPC）" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "仿知乎广告背景图" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "电子书翻页效果" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "映客答题接口测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Small插件化测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Https双向验证" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "OpenGl瞎搞几下" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "腾讯视频测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "XLog测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "即将上映demo" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Xposed测试" + index++))
        items!!.add(ToolItem(R.drawable.default_tool, "Fling Scroller测试" + index++))

        Singerstone.getInstance().init();

    }

    private val scrollListener: RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            L.i("onScrollStateChanged:>>>>>>>>>>>>>>$newState")
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                val linearManager = recyclerView
                        .layoutManager as LinearLayoutManager?
                val firstItemPosition = linearManager!!.findFirstVisibleItemPosition()
                val lastItemPosition = linearManager.findLastVisibleItemPosition()
                val firstView = linearManager.findViewByPosition(firstItemPosition)
                val lastView = linearManager.findViewByPosition(lastItemPosition)
                //只需要检测第一个和最后一个可见的Item，中间的默认全部可见
                if (getVisibilityPercents(firstView) > 50) {
                }
                if (getVisibilityPercents(lastView) > 50) {
                }
                L.i("onScrollStateChanged",
                        "$firstItemPosition $lastItemPosition，" + getVisibilityPercents(
                                firstView) + "   " + getVisibilityPercents(lastView))
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            L.i("onScrolled :$dx $dy")
            val layoutManager = recyclerView.layoutManager
            if (layoutManager is LinearLayoutManager) {
                val lastCompletelyVisibleItemPosition = layoutManager
                        .findLastCompletelyVisibleItemPosition()
                if (layoutManager.getItemCount() - 1 == lastCompletelyVisibleItemPosition) {
                    L.i("onScrolled : scroller to bottom")
                }
            } else {
                throw RuntimeException("Unsupported LayoutManager.")
            }
        }
    }

    /**
     * 判断当前View展示的百分比
     *
     * @param view
     * @return
     */
    fun getVisibilityPercents(view: View?): Int {
        if (view == null) {
            L.e("view==null")
            return 0
        }
        val rect = Rect()
        view.getLocalVisibleRect(rect)
        val height = view.height
        val width = view.width
        val percents: Int = (rect.right - rect.left) * (rect.bottom - rect.top) * 100 / (width * height)
        return percents
    }

    override fun onDown(e: MotionEvent?): Boolean {
        L.i("GestureDetector onDown")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        L.i("GestureDetector onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        L.i("GestureDetector onSingleTapUp")
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        L.i("GestureDetector onScroll")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        L.i("GestureDetector onLongPress")
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        L.i("GestureDetector onFling $velocityX")
        return false
    }
}