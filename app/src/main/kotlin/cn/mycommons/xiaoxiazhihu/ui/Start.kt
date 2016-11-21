package cn.mycommons.xiaoxiazhihu.ui

import cn.mycommons.xiaoxiazhihu.app.InjectHelp
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetStartInfoResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import rx.Observable

interface IStartView : IMvpView

class StartPresenter : BaseMvpPresenter<IStartView>() {

    internal var zhihuDomain: ZhihuDomain

    init {
        zhihuDomain = InjectHelp.getInjectInstance(ZhihuDomain::class.java)
    }

    fun doGetStartInfo(): Observable<GetStartInfoResponse> {
        return zhihuDomain.getStartInfo(1080, 1776)
    }
}