package cn.mycommons.xiaoxiazhihu.ui.base.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

/**
 * FragmentLauncher <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
object FragmentLauncher {

    fun launch(context: Context, fragmentClass: Class<out ICommonFragment>) {
        launch(context, CommonExtraParam(fragmentClass))
    }

    fun launch(context: Context, param: CommonExtraParam) {
        if (context is Activity) {
            launch(context, param, 0)
        } else {
            val it = Intent(context, getTargetActivityClass(param))
            it.putExtra(EXTRA_REQ, param)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(it)
        }
    }

    fun launch(activity: Activity, param: CommonExtraParam, req: Int) {
        val it = Intent(activity, getTargetActivityClass(param))

        it.putExtra(EXTRA_REQ, param)
        if (req == 0) {
            activity.startActivity(it)
        } else {
            activity.startActivityForResult(it, req)
        }
    }

    fun launch(fragment: Fragment, param: CommonExtraParam, req: Int) {
        val it = Intent(fragment.activity, getTargetActivityClass(param))

        it.putExtra(EXTRA_REQ, param)
        if (req == 0) {
            fragment.startActivity(it)
        } else {
            fragment.startActivityForResult(it, req)
        }
    }

    private fun getTargetActivityClass(param: CommonExtraParam): Class<out Activity> {
        var activityClass: Class<out Activity>? = CommonMvpFragmentActivity::class.java
        if (param.activityClass != null) {
            activityClass = param.activityClass
        }
        param.activityClass = activityClass as Class<out Activity>

        return activityClass as Class<out Activity>
    }
}