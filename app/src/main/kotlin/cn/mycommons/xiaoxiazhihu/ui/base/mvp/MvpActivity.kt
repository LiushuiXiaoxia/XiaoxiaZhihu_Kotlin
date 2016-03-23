package cn.mycommons.xiaoxiazhihu.ui.base.mvp

import android.os.Bundle
import cn.mycommons.xiaoxiazhihu.core.log.logd
import cn.mycommons.xiaoxiazhihu.core.log.logw
import cn.mycommons.xiaoxiazhihu.ui.base.BaseActivity

/**
 * MvpActivity <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
open class MvpActivity<P : BaseMvpPresenter<V>, V : IMvpView> : BaseActivity() {

    protected var mvpPresenter: P? = null
    protected var mvpView: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beforeMvpInit(savedInstanceState)

        onMvpInit()

        // 注册Activity
        mvpPresenter?.initMvpPresenter(this, mvpView)
        mvpPresenter?.registerEventBusListener(this)
        mvpPresenter?.create(savedInstanceState)
        mvpPresenter?.initInActivity(savedInstanceState, intent)
    }

    /**
     * 在初始化mvp前，做些事情

     * @param savedInstanceState savedInstanceState
     */
    protected fun beforeMvpInit(savedInstanceState: Bundle?) {

    }

    private fun onMvpInit() {
        try {
            initPresenterAndView()
        } catch (e: Exception) {
            // 防止子类未使用泛型所可能产生的意外错误
            logw("onMvpInit fail, e = " + e)
        }
    }

    /**
     * 通过反射获取[P]和[V]
     */
    protected fun initPresenterAndView() {
        mvpView = getViewInstance()
        val mvpHelper = MvpHelper<P, V>(this)
        val pClass = mvpHelper.getPresenterClass()
        if (pClass != null) {
            try {
                mvpPresenter = pClass.newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        logd("view = " + mvpView!!)
        logd("presenter = " + mvpPresenter)
    }

    /**
     * 返回实现[V]的实例，默认是当前Activity

     * @return [V]的实例
     */
    protected fun getViewInstance(): V? {
        try {
            val vClass = MvpHelper<P, V>(this).getViewClass()
            if (vClass.isInstance(this)) {
                return this as V
            }
        } catch (e: Exception) {
            logw(e.toString())
        }
        return null
    }

    fun onEvent(obj: Any) {

    }

    override fun onStart() {
        super.onStart()
        mvpPresenter?.start()
    }

    override fun onResume() {
        super.onResume()
        mvpPresenter?.resume()
    }

    override fun onPause() {
        super.onPause()
        mvpPresenter?.pause()
    }

    override fun onStop() {
        super.onStop()
        mvpPresenter?.stop()
    }

    override fun onDestroy() {
        //反注册Activity
        mvpPresenter?.unregisterEventBusListener(this)
        mvpPresenter?.destory()
        mvpPresenter = null
        mvpView = null

        super.onDestroy()
    }
}