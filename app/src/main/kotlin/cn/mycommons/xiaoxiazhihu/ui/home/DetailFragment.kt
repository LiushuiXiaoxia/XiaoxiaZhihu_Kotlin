package cn.mycommons.xiaoxiazhihu.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.Bind
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetNewsResponse
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetStoryExtraResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonExtraParam
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonMvpFragment
import cn.mycommons.xiaoxiazhihu.ui.base.common.FragmentLauncher
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * DetailFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class DetailFragment : CommonMvpFragment<DetailPresenter, IDetailView>() {

    @Bind(R.id.rlDetailTop)
    lateinit internal var rlDetailTop: RelativeLayout
    @Bind(R.id.icon)
    lateinit internal var icon: ImageView
    @Bind(R.id.webview)
    lateinit internal var webView: WebView
    @Bind(R.id.tvTitle)
    lateinit internal var tvTitle: TextView
    @Bind(R.id.tvSource)
    lateinit internal var tvSource: TextView

    lateinit internal var extraParam: DetailExtraParam
    internal var storyExtraResponse: GetStoryExtraResponse? = null

    override fun getFragmentLayout() = R.layout.fragment_detail

    override fun init(savedInstanceState: Bundle?) {
        extraParam = getReqExtraParam<CommonExtraParam>() as DetailExtraParam
        init()
        initWebView()
        doGetRequest()
    }

    private fun init() {
        rlDetailTop = view?.find(R.id.rlDetailTop)!!
        icon = view?.find(R.id.icon)!!
        webView = view?.find(R.id.webview)!!
        tvTitle = view?.find(R.id.tvTitle)!!
        tvSource = view?.find(R.id.tvSource)!!
        setHasOptionsMenu(true)
    }

    private fun initWebView() {
        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })
        // 启用支持javascript
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.defaultTextEncodingName = "utf-8"
    }

    private fun doGetRequest() {
        mvpPresenter?.doGetNewsResponse(extraParam.id)
                ?.subscribe(object : AdvancedSubscriber<GetNewsResponse>(loadDataView) {
                    override fun onHandleSuccess(response: GetNewsResponse) {
                        super.onHandleSuccess(response)

                        update(response)
                    }
                })

        mvpPresenter?.doGetStoryExtra(extraParam.id)
                ?.subscribe(object : AdvancedSubscriber<GetStoryExtraResponse>() {
                    override fun onHandleSuccess(response: GetStoryExtraResponse) {
                        super.onHandleSuccess(response)
                        storyExtraResponse = response

                        activity.invalidateOptionsMenu()
                        getSupportActionBar().invalidateOptionsMenu()
                    }
                })
    }

    private fun update(response: GetNewsResponse?) {
        if (response == null) {
            return
        }
        tvTitle.text = response.title
        tvSource.text = response.imageSource

        if (TextUtils.isEmpty(response.image)) {
            rlDetailTop.visibility = View.GONE
        } else {
            Picasso.with(context)
                    .load(response.image)
                    .into(icon)
        }
        webView.loadDataWithBaseURL(null, response.body, "text/html", "utf8", null)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.detail, menu)
        super.onCreateOptionsMenu(menu, inflater)

        if (storyExtraResponse != null) {
            if (storyExtraResponse!!.comments > 0) {
                val title = String.format("评论(%s)", storyExtraResponse!!.comments)
                menu!!.findItem(R.id.menu_comments).title = title
            }
            if (storyExtraResponse!!.popularity > 0) {
                val title = String.format("赞(%s)", storyExtraResponse!!.popularity)
                menu!!.findItem(R.id.menu_popularity).title = title
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.menu_comments && storyExtraResponse != null) {
            val param = CommentsExtraParam()
            param.id = extraParam.id
            param.storyExtraResponse = storyExtraResponse
            param.fragmentClass = CommentsFragment::class.java
            FragmentLauncher.launch(this, param, 0)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewInstance(): IDetailView? {
        return object : IDetailView {

        }
    }
}