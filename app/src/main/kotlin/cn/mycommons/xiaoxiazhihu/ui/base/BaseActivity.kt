package cn.mycommons.xiaoxiazhihu.ui.base

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.DialogFragment
import android.support.v7.app.ActionBar
import android.widget.Toast
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.ILoadDataView
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.LOADING_TYPE_DEFAULT
import roboguice.activity.RoboActionBarActivity

/**
 * BaseActivity <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
abstract class BaseActivity : RoboActionBarActivity(), ILoadDataView {

    private var loadingDialogFragment: DialogFragment? = null
    protected lateinit var uiHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ButterKnife.bind(this)
        initActionBar()
        uiHandler = Handler(mainLooper)
    }

    // ***************************************** ActionBar *****************************
    private fun initActionBar() {
        val options = ActionBar.DISPLAY_HOME_AS_UP or ActionBar.DISPLAY_USE_LOGO or ActionBar.DISPLAY_SHOW_TITLE
        supportActionBar?.displayOptions = options
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun setTitle(titleId: Int) {
        title = getString(titleId)
    }

    override fun setTitle(title: CharSequence) {
        supportActionBar?.title = title
    }

    // ***************************************** ILoadDataView *****************************

    override fun getContext(): Context {
        return this
    }

    override fun showLoading() {
        showLoading(LOADING_TYPE_DEFAULT)
    }

    override fun showLoading(loadingType: Int) {
        when (loadingType) {
            LOADING_TYPE_DEFAULT -> showDefaultStyleLoadingDialog(null)
            else -> showDefaultStyleLoadingDialog(null)
        }
    }

    // 显示用默认样式的Loading对话框
    private fun showDefaultStyleLoadingDialog(loadingTitle: String?) {
        hideDefaultStyleLoadingDialog()

        val newFragment = createLoadingDialogFragment(loadingTitle)
        newFragment.show(supportFragmentManager, "loading_dialog")
        loadingDialogFragment = newFragment
    }

    override fun hideLoading() {
        hideLoading(LOADING_TYPE_DEFAULT)
    }

    override fun hideLoading(loadingType: Int) {
        when (loadingType) {
            LOADING_TYPE_DEFAULT -> hideDefaultStyleLoadingDialog()
            else -> hideDefaultStyleLoadingDialog()
        }
    }

    //  隐藏默认样式的loading对话框
    private fun hideDefaultStyleLoadingDialog() {
        loadingDialogFragment?.dismiss()
        loadingDialogFragment = null
    }

    override fun showError(message: String) {
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()
    }
}