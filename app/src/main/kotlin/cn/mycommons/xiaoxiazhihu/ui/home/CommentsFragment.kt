package cn.mycommons.xiaoxiazhihu.ui.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.Comment
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetLongCommentsResponse
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetShortCommentsResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonExtraParam
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonMvpFragment
import org.jetbrains.anko.find
import java.util.*

/**
 * CommentsFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class CommentsFragment : CommonMvpFragment<CommentsPresenter, CommentsPresenter.ICommentsView>() {

    companion object {
        val TYPE_TITLE = 1
        val TYPE_ITEM = 2
    }

    @Bind(R.id.recyclerView)
    lateinit internal var recyclerView: RecyclerView

    lateinit internal var commentsExtraParam: CommentsExtraParam
    internal var data: ArrayList<Any> = ArrayList()
    lateinit internal var adapter: CommentsAdapter

    override fun getFragmentLayout() = R.layout.fragment_comments

    override fun init(savedInstanceState: Bundle?) {
        commentsExtraParam = getReqExtraParam<CommonExtraParam>() as CommentsExtraParam
        recyclerView = view?.find(R.id.recyclerView)!!

        data.add(commentsExtraParam.storyExtraResponse?.longComments!!)

        adapter = CommentsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        doGetRequest()
    }

    internal fun doGetRequest() {
        mvpPresenter?.doGetLongCommentsById(commentsExtraParam.id)
                ?.subscribe(object : AdvancedSubscriber<GetLongCommentsResponse>() {
                    override fun onHandleSuccess(response: GetLongCommentsResponse) {
                        super.onHandleSuccess(response)

                        data.clear()
                        data.add(commentsExtraParam.storyExtraResponse?.longComments!!)
                        data.addAll(response.comments!!)
                        data.add(commentsExtraParam.storyExtraResponse?.shortComments!!)
                        adapter.notifyDataSetChanged()
                    }
                })
    }

    internal inner class CommentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount() = data.size

        override fun getItemViewType(position: Int): Int {
            if (data[position] is Int) {
                return TYPE_TITLE
            }
            return TYPE_ITEM
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(context)

            if (viewType == TYPE_ITEM) {
                return CommentsTypeItem(inflater.inflate(R.layout.item_last_comment, parent, false))
            } else {
                return CommentsTypeTitle(inflater.inflate(R.layout.item_last_title, parent, false))
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is CommentsTypeItem) {
                holder.bind(data[position]  as Comment)
            } else if (holder is CommentsTypeTitle) {
                holder.bind(data[position]  as Int)
            }
        }
    }

    inner class CommentsTypeTitle(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        @Bind(R.id.text)
        lateinit var textView: TextView

        init {
            ButterKnife.bind(this, itemView)
            textView = itemView.find(R.id.text)

            itemView.setOnClickListener(this)
        }

        fun bind(count: Int?) {
            if (adapterPosition == 0) {
                textView.text = String.format("%s条长评论", count)
            } else {
                textView.text = String.format("%s条短评论", count)
            }
        }

        override fun onClick(v: View) {
            // 不是第一个
            if (adapterPosition != 0 && !mvpPresenter!!.isLoadShort) {
                mvpPresenter?.doGetShortComments(commentsExtraParam?.id)
                        ?.subscribe(object : AdvancedSubscriber<GetShortCommentsResponse>(loadDataView) {
                            override fun onHandleSuccess(response: GetShortCommentsResponse) {
                                super.onHandleSuccess(response)

                                data.addAll(response.comments!!)
                                adapter.notifyDataSetChanged()
                                recyclerView.scrollBy(0, itemView.top)
                            }
                        })
            }
        }
    }

    override fun getViewInstance(): CommentsPresenter.ICommentsView? {
        return object : CommentsPresenter.ICommentsView {

        }
    }
}