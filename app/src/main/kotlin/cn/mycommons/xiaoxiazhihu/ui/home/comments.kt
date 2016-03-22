package cn.mycommons.xiaoxiazhihu.ui.home

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.Comment
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetLongCommentsResponse
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetShortCommentsResponse
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetStoryExtraResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonExtraParam
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import cn.mycommons.xiaoxiazhihu.utils.format
import com.google.inject.Inject
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import rx.Observable
import java.util.*

/**
 * comments <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class CommentsExtraParam : CommonExtraParam() {

    var id: Int = 0
    var storyExtraResponse: GetStoryExtraResponse? = null

    override fun toString(): String {
        return "CommentsExtraParam{" +
                "id=" + id +
                ", storyExtraResponse=" + storyExtraResponse +
                "} " + super.toString()
    }
}

private val FORMAT = "yyyy-MM-dd HH:mm"

internal class CommentsTypeItem(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    @Bind(R.id.text)
    var textView: TextView? = null
    @Bind(R.id.text2)
    var textView2: TextView? = null
    @Bind(R.id.text3)
    var textView3: TextView? = null
    @Bind(R.id.icon)
    var icon: ImageView? = null

    init {
        ButterKnife.bind(this, itemView)

        textView = itemView.find(R.id.text)
        textView2 = itemView.find(R.id.text2)
        textView3 = itemView.find(R.id.text3)
        icon = itemView.find(R.id.icon)

        itemView.setOnClickListener(this)
        textView?.setSingleLine()
    }

    fun bind(comment: Comment) {
        itemView.tag = comment
        textView?.text = comment.author
        textView2?.text = comment.content
        textView3?.text = Date(comment.time * 1000L).format(FORMAT)

        if (TextUtils.isEmpty(comment.avatar)) {
            icon?.setImageResource(0)
        } else {
            Picasso.with(icon!!.context)
                    .load(comment.avatar)
                    .placeholder(R.drawable.ic_launcher)
                    .into(icon)
        }
    }

    override fun onClick(v: View) {
    }
}

class CommentsPresenter : BaseMvpPresenter<CommentsPresenter.ICommentsView>() {

    @Inject
    internal var zhihuDomain: ZhihuDomain? = null

    var isLoadShort = false
        private set

    fun doGetShortComments(id: Int): Observable<GetShortCommentsResponse>? {
        isLoadShort = true
        return zhihuDomain?.getShortCommentsById(id)
    }

    fun doGetLongCommentsById(id: Int): Observable<GetLongCommentsResponse>? {
        return zhihuDomain?.getLongCommentsById(id)
    }

    interface ICommentsView : IMvpView
}