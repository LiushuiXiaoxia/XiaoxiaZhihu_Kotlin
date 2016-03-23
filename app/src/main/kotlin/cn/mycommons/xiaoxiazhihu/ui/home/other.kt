package cn.mycommons.xiaoxiazhihu.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.LastThemeStory
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetThemeResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.FragmentLauncher
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import com.google.inject.Inject
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import rx.Observable
import java.util.*

/**
 * other <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class OtherThemePresenter : BaseMvpPresenter<OtherThemePresenter.IHomeView>() {

    @Inject
    internal var zhihuDomain: ZhihuDomain? = null

    fun doGetThemById(id: Int): Observable<GetThemeResponse>? {
        return zhihuDomain?.getThemeById(id)
    }

    interface IHomeView : IMvpView
}

internal class OtherThemeTypeTitle(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @Bind(R.id.text)
    var textView: TextView? = null

    init {
        ButterKnife.bind(this, itemView)
        textView = itemView.find(R.id.text)
    }

    fun bind(text: String) {
        textView?.text = text
    }
}

class OtherThemeTypeHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @Bind(R.id.text)
    var textView: TextView? = null
    @Bind(R.id.icon)
    var icon: ImageView? = null

    init {
        ButterKnife.bind(this, itemView)
        textView = itemView.find(R.id.text)
        icon = itemView.find(R.id.icon)
    }

    fun bind(response: GetThemeResponse?) {
        textView?.text = response?.name
        Picasso.with(icon?.context).load(response?.image).into(icon)
    }
}

internal class OtherThemeTypeItem(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    @Bind(R.id.text)
    var textView: TextView? = null
    @Bind(R.id.icon)
    var icon: ImageView? = null

    init {
        ButterKnife.bind(this, itemView)
        textView = itemView.find(R.id.text)
        icon = itemView.find(R.id.icon)

        itemView.setOnClickListener(this)
    }

    fun bind(story: LastThemeStory) {
        itemView?.tag = story
        textView?.text = story.title
        var size: Int? = story.images?.size;
        if (size != null && size > 0) {
            Picasso.with(icon!!.context)
                    .load(story.images?.get(0))
                    .placeholder(R.drawable.ic_launcher)
                    .into(icon)
        } else {
            icon?.setImageResource(0)
        }
    }

    override fun onClick(v: View) {
        val story = v.tag as LastThemeStory
        val param = DetailExtraParam()
        param.fragmentClass = DetailFragment::class.java
        param.id = story.id
        FragmentLauncher.launch(v.context, param)
    }
}

internal class OtherThemeAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val TYPE_HEADER = 1
        val TYPE_ITEM = 2
        val TYPE_TITLE = 3
    }

    var data: MutableList<Any>
    var getThemeResponse: GetThemeResponse? = null

    init {
        data = ArrayList<Any>()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        } else if (data[position] is LastThemeStory) {
            return TYPE_ITEM
        }
        return TYPE_TITLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        if (viewType == TYPE_HEADER) {
            return OtherThemeTypeHeader(layoutInflater.inflate(R.layout.item_top_item_fragment, parent, false))
        } else if (viewType == TYPE_ITEM) {
            return OtherThemeTypeItem(layoutInflater.inflate(R.layout.item_last_item, parent, false))
        } else {
            return OtherThemeTypeTitle(layoutInflater.inflate(R.layout.item_last_title, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OtherThemeTypeHeader) {
            holder.bind(getThemeResponse)
        } else if (holder is OtherThemeTypeItem) {
            holder.bind(data[position - 1] as LastThemeStory)
        } else if (holder is OtherThemeTypeTitle) {
            holder.bind(data[position - 1] as String)
        }
    }

    override fun getItemCount(): Int = data.size

    fun notifyDataSetChanged(response: GetThemeResponse) {
        this.getThemeResponse = response

        data.clear()
        data.addAll(response.stories!!)

        super.notifyDataSetChanged()
    }
}