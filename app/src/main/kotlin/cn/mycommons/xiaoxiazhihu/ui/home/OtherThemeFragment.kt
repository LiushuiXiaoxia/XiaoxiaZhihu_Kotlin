package cn.mycommons.xiaoxiazhihu.ui.home

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.Bind
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.ThemeItem
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetThemeResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpFragment
import org.jetbrains.anko.find

/**
 * OtherThemeFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class OtherThemeFragment : MvpFragment<OtherThemePresenter, OtherThemePresenter.IHomeView>() {

    companion object {
        internal val EXTRA_ITEM = "themeItem"

        fun newInstance(themeItem: ThemeItem): OtherThemeFragment {
            val fragment = OtherThemeFragment()
            val args = Bundle()
            args.putSerializable(EXTRA_ITEM, themeItem)
            fragment.arguments = args
            return fragment
        }
    }

    @Bind(R.id.swipeRefreshLayout)
    internal var swipeRefreshLayout: SwipeRefreshLayout? = null
    @Bind(R.id.recyclerView)
    internal var recyclerView: RecyclerView? = null

    internal var themeItem: ThemeItem? = null
    internal var adapter: OtherThemeAdapter? = null

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = view?.find(R.id.swipeRefreshLayout)
        recyclerView = view?.find(R.id.recyclerView)


        themeItem = arguments.getSerializable(EXTRA_ITEM) as ThemeItem?
        setTitle(themeItem?.name)

        adapter = OtherThemeAdapter(context)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = adapter

        swipeRefreshLayout?.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout?.setOnRefreshListener { doGetRequest() }
        doGetRequest()
    }

    private fun doGetRequest() {
        mvpPresenter!!.doGetThemById(themeItem?.id as Int)
                ?.subscribe(object : AdvancedSubscriber<GetThemeResponse>() {
                    override fun onHandleSuccess(response: GetThemeResponse) {
                        super.onHandleSuccess(response)

                        update(response)
                    }
                })
    }

    internal fun update(response: GetThemeResponse) {
        adapter?.notifyDataSetChanged(response)
        swipeRefreshLayout?.isRefreshing = false
    }

    override fun getViewInstance(): OtherThemePresenter.IHomeView? {
        return object : OtherThemePresenter.IHomeView {

        }
    }
}