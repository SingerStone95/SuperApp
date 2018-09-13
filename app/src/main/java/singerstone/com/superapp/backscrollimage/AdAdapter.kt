package singerstone.com.superapp.backscrollimage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import singerstone.com.superapp.R

/**
 * Created by chenbinhao on 2018/6/7.
 * YY:909075276
 */
class AdAdapter : RecyclerView.Adapter<AdAdapter.AdHolder> {

    var mContext: Context
    var mData: List<String>

    constructor(contex: Context, data: List<String>) {
        mContext = contex
        mData = data
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdHolder?, position: Int) {
        if (position > 0 && position % 7 == 0) {
            holder?.setVisible(R.id.id_tv_title, false)
            holder?.setVisible(R.id.id_tv_desc, false)
            holder?.setVisible(R.id.id_iv_ad, true)
        } else {
            holder?.setVisible(R.id.id_tv_title, true)
            holder?.setVisible(R.id.id_tv_desc, true)
            holder?.setVisible(R.id.id_iv_ad, false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): AdHolder {
        return AdHolder(LayoutInflater.from(mContext)?.inflate(R.layout.scroll_item, null))
    }


    class AdHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var title = itemView?.findViewById<TextView?>(R.id.id_tv_title) as TextView
        var desc = itemView?.findViewById<TextView?>(R.id.id_tv_desc) as TextView
        var image = itemView?.findViewById<AdImageView?>(R.id.id_iv_ad) as AdImageView
        fun setVisible(id: Int, visible: Boolean) {
            when (id) {
                R.id.id_tv_title -> {
                    if (visible) {
                        title.visibility = View.VISIBLE
                    } else {
                        title.visibility = View.GONE
                    }
                }
                R.id.id_tv_desc -> {
                    if (visible) {
                        desc.visibility = View.VISIBLE
                    } else {
                        desc.visibility = View.GONE
                    }
                }
                R.id.id_iv_ad -> {
                    if (visible) {
                        image.visibility = View.VISIBLE
                    } else {
                        image.visibility = View.GONE
                    }
                }
            }
        }
    }
}