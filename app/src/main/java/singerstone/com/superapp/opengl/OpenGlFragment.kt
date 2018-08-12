package singerstone.com.superapp.opengl

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import kotlinx.android.synthetic.main.fragment_layout_opengl.*
import singerstone.com.superapp.R
import singerstone.com.superapp.base.BaseFragment
import singerstone.com.superapp.utils.DimentionUtils
import singerstone.com.superapp.utils.L


/**
 * Created by chenbinhao on 2018/6/5.
 * YY:909075276
 */
class OpenGlFragment : BaseFragment(), View.OnClickListener {

    var ilike: ILike? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_layout_opengl, null)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        tv_getScreenWH.text = "获取屏幕数据"

        listenNaVigationBar()

    }

    override fun onClick(v: View?) {
        L.e("onclick")
        when (v?.id) {
            R.id.tv_getScreenWH -> {
                L.i(DimentionUtils.getScreenHeight(activity).toString() + " " + DimentionUtils.getScreenWidth(activity) + " " + DimentionUtils.getNavigationBarHeight(activity) + " " + DimentionUtils.getStatusBarHeight(activity))
            }
            else -> {
                L.i("nothing!")
            }
        }
        lvTest.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

        }
        ilike=object :ILike{
            override fun play(name: String, age: Int) {

            }

        }
    }

    fun listenNaVigationBar() {
        activity.getContentResolver().registerContentObserver(Settings.System.getUriFor
        ("navigationbar_is_min"), true, mNavigationStatusObserver)
    }


    private val mNavigationStatusObserver = object : ContentObserver(Handler()) {
        override fun onChange(selfChange: Boolean) {
            val navigationBarIsMin = Settings.System.getInt(activity.getContentResolver(),
                    "navigationbar_is_min", 0)
            if (navigationBarIsMin == 1) {
                L.i("navigationbar dismissing!")
            } else {
                L.i("navigationbar showing!")
            }
        }
    }

    interface ILike {
        fun play(name: String, age: Int)
    }
}