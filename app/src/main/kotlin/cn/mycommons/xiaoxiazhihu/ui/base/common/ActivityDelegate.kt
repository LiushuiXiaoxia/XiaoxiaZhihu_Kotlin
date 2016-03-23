package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarActivity
import cn.mycommons.xiaoxiazhihu.core.log.logd
import cn.mycommons.xiaoxiazhihu.core.log.loge

/**
 * ActivityDelegate <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class ActivityDelegate<A : ActionBarActivity, F : Fragment>(protected var activity: A) {

    lateinit var extraParam: CommonExtraParam
    var commonFragment: F? = null

    fun beforeOnCreate(savedInstanceState: Bundle?) {
        logd("beforeOnCreate")
    }

    fun afterOnCreate(savedInstanceState: Bundle?) {
        logd("afterOnCreate")

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val it = activity.intent
        val obj = it?.getSerializableExtra(EXTRA_REQ)
        if (validate(obj)) {
            extraParam = obj as CommonExtraParam
            if (savedInstanceState == null) {
                try {
                    commonFragment = extraParam.fragmentClass.newInstance() as F
                    activity.supportFragmentManager
                            .beginTransaction()
                            .add(FRAGMENT_CONTAINER, commonFragment)
                            .commitAllowingStateLoss()
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        } else {
            loge(javaClass.simpleName + "参数不合法")
            activity.finish()
        }

        logd(String.format("activity = %s, fragment = %s, param = %s", activity, commonFragment, extraParam))
    }

    private fun validate(obj: Any?): Boolean {
        var ret = false
        do {
            if (obj == null) {
                break
            }
            if (obj !is CommonExtraParam) {
                break
            }
            if (!obj.validate()) {
                break
            }
            ret = true
        } while (false)
        return ret
    }
}