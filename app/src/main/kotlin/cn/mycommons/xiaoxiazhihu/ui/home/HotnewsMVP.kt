package cn.mycommons.xiaoxiazhihu.ui.home

import cn.mycommons.xiaoxiazhihu.app.InjectHelp
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetLastThemeResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import com.google.inject.Inject
import rx.Observable

/**
 * hotnews <br/>
 * Created by xiaqiulei on 2016-03-23.
 */

interface IHotnewsView : IMvpView

class HotnewsPresenter : BaseMvpPresenter<IHotnewsView>() {

    @Inject
    internal var zhihuDomain: ZhihuDomain = InjectHelp.getInjectInstance(ZhihuDomain::class.java)

    fun doGetLastTheme(): Observable<GetLastThemeResponse> {
        return zhihuDomain.getLastTheme()
    }
}