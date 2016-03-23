package cn.mycommons.xiaoxiazhihu.ui.base

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.FragmentManager
import cn.mycommons.xiaoxiazhihu.ui.base.LoadingDialogFragment.Companion.DIMABLE
import cn.mycommons.xiaoxiazhihu.ui.base.LoadingDialogFragment.Companion.MSG
import roboguice.fragment.RoboDialogFragment

/**
 * LoadingDialogFragment <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

fun createLoadingDialogFragment(msg: String?): LoadingDialogFragment {
    return createLoadingDialogFragment(msg, true)
}

fun createLoadingDialogFragment(msg: String?, dimable: Boolean): LoadingDialogFragment {
    val f = LoadingDialogFragment()
    val args = Bundle()
    args.putString(MSG, msg)
    args.putBoolean(DIMABLE, dimable)
    f.arguments = args
    return f
}

class LoadingDialogFragment : RoboDialogFragment() {

    companion object {
        val DEFAULT_MSG = "数据加载中,请稍后……"
        internal val DIMABLE = "dimable"
        internal val MSG = "msg"
    }

    internal var onCancelListener: DialogInterface.OnCancelListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dimable = arguments.getBoolean(DIMABLE)
        val msg = arguments.getString(MSG, DEFAULT_MSG)

        val dialog = ProgressDialog(context)
        dialog.setCancelable(dimable)
        dialog.setCanceledOnTouchOutside(dimable)
        dialog.setTitle(msg)

        return dialog
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        onCancelListener?.onCancel(dialog)
    }

    fun show(fragmentManager: FragmentManager): LoadingDialogFragment {
        super.show(fragmentManager, null)
        return this
    }
}