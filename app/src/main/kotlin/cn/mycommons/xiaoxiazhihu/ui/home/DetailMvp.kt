package cn.mycommons.xiaoxiazhihu.ui.home

import cn.mycommons.xiaoxiazhihu.app.InjectHelp
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetNewsResponse
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetStoryExtraResponse
import cn.mycommons.xiaoxiazhihu.ui.base.common.CommonExtraParam
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import com.google.inject.Inject
import rx.Observable

/**
 * DetailMvp <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class DetailExtraParam : CommonExtraParam() {

    var id: Int = 0

    override fun toString(): String {
        return "DetailExtraParam{" +
                "id='" + id + '\'' +
                "} " + super.toString()
    }
}

interface IDetailView : IMvpView

class DetailPresenter : BaseMvpPresenter<IDetailView>() {

    @Inject
    internal var domain: ZhihuDomain = InjectHelp.getInjectInstance(ZhihuDomain::class.java)

    internal fun doGetNewsResponse(id: Int): Observable<GetNewsResponse> {
        return domain.getNewsById(id)
    }

    internal fun doGetStoryExtra(id: Int): Observable<GetStoryExtraResponse> {
        return domain.getStoryExtraById(id)
    }
}