package cn.mycommons.xiaoxiazhihu.ui.base.mvp

import android.content.Context
import android.os.Bundle

/**
 * interfaces <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

interface IView {
    fun getContext(): Context
}

// loading对话框的默认类型
val LOADING_TYPE_DEFAULT = 101

interface ILoadDataView : IView {
    /**
     * 使用LOADING_TYPE_DEFAULT显示loading对话框
     */
    fun showLoading()

    fun showLoading(loadingType: Int)

    /**
     * 隐藏所有正在显示的loading对话框
     */
    fun hideLoading()

    fun hideLoading(loadingType: Int)

    fun showError(message: String)
}

interface IMvpView

interface IMvpPresenter {

    /**
     * Activity.onCreate
     * Fragment.onViewCreated
     */
    fun create(savedInstanceState: Bundle?)

    /**
     * Activity.onStart
     * Fragment.onStart
     */
    fun start()

    /**
     * Activity.onResume
     * Fragment.onResume
     */
    fun resume()

    /**
     * Activity.onPause
     * Fragment.onPause
     */
    fun pause()

    /**
     * Activity.onStop
     * Fragment.onStop
     */
    fun stop()

    /**
     * Activity.onDestory
     * Fragment.onDestory
     */
    fun destory()
}