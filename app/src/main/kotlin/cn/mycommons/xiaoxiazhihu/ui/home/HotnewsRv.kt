package cn.mycommons.xiaoxiazhihu.ui.home

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.LastTemeTopStory
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.LastThemeStory
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetLastThemeResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.FragmentLauncher
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import java.util.*

/**
 * HotnewsRv <br/>
 * Created by xiaqiulei on 2016-03-23.
 */

private val TYPE_HEADER = 1
private val TYPE_ITEM = 2
private val TYPE_TITLE = 3

class HotnewsRecyclerAdapter(
        var manager: FragmentManager,
        var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: MutableList<Any> = ArrayList()
    var tops: MutableList<LastTemeTopStory> = ArrayList()

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        } else if (data[position] is LastThemeStory) {
            return TYPE_ITEM
        }
        return TYPE_TITLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        if (viewType == TYPE_HEADER) {
            return HotnewsTypeHeaderViewHolder(inflater.inflate(R.layout.item_last_header, parent, false), manager)
        } else if (viewType == TYPE_ITEM) {
            return HotnewsTypeItemViewHolder(inflater.inflate(R.layout.item_last_item, parent, false))
        } else {
            return HotnewsTypeTitleViewHolder(inflater.inflate(R.layout.item_last_title, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HotnewsTypeHeaderViewHolder) {
            holder.bind(tops)
        } else if (holder is HotnewsTypeItemViewHolder) {
            holder.bind(data[position - 1] as LastThemeStory)
        } else if (holder is HotnewsTypeTitleViewHolder) {
            holder.bind(data[position - 1] as String)
        }
    }

    fun notifyDataSetChanged(response: GetLastThemeResponse) {
        data.clear()
        data.addAll(response.stories!!)

        tops.clear()
        tops.addAll(response.topStories!!)

        super.notifyDataSetChanged()
    }
}

internal class HotnewsTypeTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

internal class HotnewsTypeHeaderViewHolder(
        itemView: View,
        var fragmentManager: FragmentManager) : RecyclerView.ViewHolder(itemView) {

    @Bind(R.id.viewPager)
    var viewPager: ViewPager? = null

    init {
        ButterKnife.bind(this, itemView)
        viewPager = itemView.find(R.id.viewPager)
    }

    fun bind(tops: List<LastTemeTopStory>?) {

        var list: List<LastTemeTopStory>? = tops ?: ArrayList<LastTemeTopStory>()
        viewPager?.adapter = HotnewsFragmentPagerAdapter(fragmentManager, list)
    }
}

internal class HotnewsTypeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

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
        if (story.images != null && story.images!!.size > 0) {
            Picasso.with(icon!!.context)
                    .load(story.images!![0])
                    .placeholder(R.drawable.ic_launcher)
                    .into(icon)
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

class HotnewsFragmentPagerAdapter(fm: FragmentManager,
                                  var tops: List<LastTemeTopStory>?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment = TopItemFragment()
        fragment.setTopStory(tops!![position])
        return fragment
    }

    override fun getCount(): Int {
        return (tops ?: ArrayList()).size
    }
}