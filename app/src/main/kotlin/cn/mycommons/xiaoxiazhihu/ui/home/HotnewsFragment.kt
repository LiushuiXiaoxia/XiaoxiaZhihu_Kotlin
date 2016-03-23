package cn.mycommons.xiaoxiazhihu.ui.home

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.Bind
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetLastThemeResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonMvpFragment
import org.jetbrains.anko.find

/**
 * HotnewsFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class HotnewsFragment : CommonMvpFragment<HotnewsPresenter, IHotnewsView>() {

    @Bind(R.id.swipeRefreshLayout)
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @Bind(R.id.recyclerView)
    private lateinit var recyclerView: RecyclerView

    private lateinit var recyclerAdapter: HotnewsRecyclerAdapter

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_hotnews
    }

    override fun init(savedInstanceState: Bundle?) {
        setTitle("今日热闻")
        init()
        doGetRequest()
    }

    private fun init() {
        swipeRefreshLayout = view?.find(R.id.swipeRefreshLayout)!!
        recyclerView = view?.find(R.id.recyclerView)!!

        recyclerAdapter = HotnewsRecyclerAdapter(fragmentManager, context)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener { doGetRequest() }
    }

    private fun doGetRequest() {
        mvpPresenter!!.doGetLastTheme()
                .subscribe(object : AdvancedSubscriber<GetLastThemeResponse>() {
                    override fun onHandleSuccess(response: GetLastThemeResponse) {
                        super.onHandleSuccess(response)

                        update(response)
                    }
                })
    }

    private fun update(response: GetLastThemeResponse) {
        swipeRefreshLayout.isRefreshing = false
        recyclerAdapter.notifyDataSetChanged(response)
    }

    override fun getViewInstance(): IHotnewsView? {
        return object : IHotnewsView {

        }
    }
}