package cn.mycommons.xiaoxiazhihu.ui

import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetAllThemesResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import com.google.inject.Inject
import rx.Observable

/**
 * MainPresenter <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
interface IMenuListView : IMvpView

class MainPresenter : BaseMvpPresenter<IMenuListView>() {

    @Inject
    internal var zhihuDomain: ZhihuDomain? = null

    fun doGetAllThemesResponse(): Observable<GetAllThemesResponse>? {
        return zhihuDomain?.getAllThemes()
    }
}