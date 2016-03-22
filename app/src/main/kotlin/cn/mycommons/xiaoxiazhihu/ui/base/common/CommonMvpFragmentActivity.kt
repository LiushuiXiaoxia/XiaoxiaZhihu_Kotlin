package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.os.Bundle
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpActivity

/**
 * CommonMvpFragmentActivity <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
open class CommonMvpFragmentActivity<P : BaseMvpPresenter<V>, V : IMvpView> : MvpActivity<P, V>() {

    private var delegate: ActivityDelegate<CommonMvpFragmentActivity<*, *>, CommonMvpFragment<*, *>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (delegate == null) {
            delegate = getDeledate()
        }

        delegate?.beforeOnCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_common_fm)
        delegate?.afterOnCreate(savedInstanceState)
    }

    protected fun getDeledate(): ActivityDelegate<CommonMvpFragmentActivity<*, *>, CommonMvpFragment<*, *>> {
        return ActivityDelegate(this)
    }

    protected fun getExtraParam(): CommonExtraParam = delegate?.extraParam as CommonExtraParam

    protected fun getCommonFragment(): CommonMvpFragment<*, *>? = delegate?.commonFragment

    override fun onBackPressed() {
        if (getCommonFragment() != null) {
            if (!getCommonFragment()!!.onActivityPressBack()) {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return getCommonFragment() != null
                && getCommonFragment()!!.onActivitySupportNavigateUp()
                || super.onSupportNavigateUp()
    }
}