package cn.mycommons.xiaoxiazhihu.app

import android.app.Application
import android.content.Context
import cn.mycommons.xiaoxiazhihu.business.api.ZhihuApi
import cn.mycommons.xiaoxiazhihu.business.api.retrofit.ZhihuApiRetrofitImpl
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.domain.impl.ZhihuDomainImpl
import com.google.inject.AbstractModule
import com.google.inject.Injector
import com.google.inject.Module
import de.greenrobot.event.EventBus
import roboguice.RoboGuice

/**
 * inject <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
private class BindImplModule : AbstractModule() {

    override fun configure() {
        bind(ZhihuDomain::class.java).to(ZhihuDomainImpl::class.java)
        // bind(ZhihuApi::class.java).to(ZhihuApiOkHttpImpl::class.java);
        bind(ZhihuApi::class.java).to(ZhihuApiRetrofitImpl::class.java)
    }
}

object InjectHelp {

    fun init(application: Application) {
        // 关闭 RoboGuice 3.+ 编译时生成代码功能。仅使用反射。
        RoboGuice.setUseAnnotationDatabases(false)

        RoboGuice.getOrCreateBaseApplicationInjector(
                application,
                RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(application),
                BindImplModule())
    }

    fun getAppContext(): AppContext? = appContextInstance()

    fun <T> getInjectInstance(tClass: Class<T>): T = RoboGuice.getInjector(getAppContext()).getInstance(tClass)

    fun getEventBus(): EventBus = EventBus.getDefault()

    fun injectMembersWithoutViews(obj: Any) = RoboGuice.getInjector(getAppContext()).injectMembersWithoutViews(obj)

    fun createChildInjector(context: Context, vararg modules: Module): Injector {
        return RoboGuice.getInjector(context).createChildInjector(*modules)
    }
}