package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.os.Bundle
import android.view.View
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpFragment

/**
 * CommonMvpFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
abstract class CommonMvpFragment<P : BaseMvpPresenter<V>, V : IMvpView> : MvpFragment<P, V>(), ICommonFragment {

    //    var context: Context? = null

    private var delegate: FragmentDelegate<CommonMvpFragment<*, *>, CommonExtraParam>? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        //        context = activity

        if (delegate == null) {
            delegate = getDelegate()
        }

        delegate?.beforeOnViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
        delegate?.afterOnViewCreated(view, savedInstanceState)

        init(savedInstanceState)
    }

    protected abstract fun init(savedInstanceState: Bundle?)

    protected fun getDelegate(): FragmentDelegate<CommonMvpFragment<*, *>, CommonExtraParam> {
        return FragmentDelegate(this)
    }

    fun <T : CommonExtraParam> getReqExtraParam(): T {
        return delegate?.reqExtraParam as T
    }

    protected fun setSuccessResult(extraParam: CommonExtraParam) {
        delegate?.setSuccessResult(extraParam)
    }

    override fun onActivityPressBack(): Boolean {
        return false
    }

    override fun onActivitySupportNavigateUp(): Boolean {
        return false
    }
}