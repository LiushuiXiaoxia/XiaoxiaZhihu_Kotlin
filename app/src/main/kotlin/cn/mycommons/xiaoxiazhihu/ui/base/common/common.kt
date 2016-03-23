package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.app.IValidate
import java.io.Serializable

/**
 * common <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

internal val EXTRA_REQ = "extra_req"
internal val EXTRA_RESP = "extra_resp"
internal val FRAGMENT_CONTAINER = R.id.fmFragmentContainer

interface ICommonFragment {
    /**
     * 当父activity点击触发onPressBack()时候，调用此方法，返回true则不调用父中的back

     * @return
     */
    fun onActivityPressBack(): Boolean

    /**
     * 当父activity点击触发onSupportNavigateUp()时候，调用此方法，返回true则不调用父中的back

     * @return
     */
    fun onActivitySupportNavigateUp(): Boolean
}

fun <R : CommonExtraParam> getCommonReqExtraParam(activity: FragmentActivity?): R? {
    try {
        return activity?.intent?.getSerializableExtra(EXTRA_REQ) as R
    } catch (e: Exception) {
        return null
    }
}

fun <R : CommonExtraParam> getCommonRespExtraParam(data: Intent?): R? {
    if (data != null) {
        return data.getSerializableExtra(EXTRA_RESP) as R
    }
    return null
}

open class CommonExtraParam : Serializable, IValidate {

    lateinit var fragmentClass: Class<out ICommonFragment>
    var activityClass: Class<out Activity> = CommonMvpFragmentActivity::class.java
    var uri: String? = null

    constructor() {
    }

    constructor(fragmentClass: Class<out ICommonFragment>) {
        this.fragmentClass = fragmentClass
    }

    constructor(fragmentClass: Class<out ICommonFragment>, activityClass: Class<out Activity>) {
        this.fragmentClass = fragmentClass
        this.activityClass = activityClass
    }

    override fun validate(): Boolean = true

    override fun toString(): String {
        return "CommonExtraParam(fragmentClass=$fragmentClass, activityClass=$activityClass, uri=$uri)"
    }
}