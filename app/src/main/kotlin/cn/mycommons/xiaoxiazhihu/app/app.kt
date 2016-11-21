package cn.mycommons.xiaoxiazhihu.app

import android.app.Application

/**
 * app <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

private var appContextInstance: AppContext? = null

fun appContextInstance(): AppContext = appContextInstance!!

class AppContext : Application() {

    override fun onCreate() {
        super.onCreate()
        appContextInstance = this

        initInject()
    }

    private fun initInject() {
        InjectHelp.init(this)
    }
}