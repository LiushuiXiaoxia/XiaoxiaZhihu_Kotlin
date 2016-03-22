package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import cn.mycommons.xiaoxiazhihu.core.log.logd
import cn.mycommons.xiaoxiazhihu.core.log.logi
import java.security.InvalidParameterException

/**
 * FragmentDelegate <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class FragmentDelegate<F : Fragment, P : CommonExtraParam>(private val fragment: F?) {

    var reqExtraParam: P? = null
        protected set

    init {
        if (fragment == null) {
            throw InvalidParameterException("fragment is null.")
        }
    }

    fun beforeOnViewCreated(view: View?, savedInstanceState: Bundle?) {
        logi("beforeOnViewCreated")
    }

    fun afterOnViewCreated(view: View?, savedInstanceState: Bundle?) {
        logi("afterOnViewCreated")

        reqExtraParam = getCommonReqExtraParam<P>(fragment?.activity)

        val content = String.format("fragment = %s, extraReqParam = %s", fragment, reqExtraParam)
        logd(content)
    }

    fun setSuccessResult(extraParam: CommonExtraParam) {
        val it = Intent()
        it.putExtra(EXTRA_RESP, extraParam)
        fragment?.activity?.setResult(Activity.RESULT_OK, it)
        fragment?.activity?.finish()
    }
}