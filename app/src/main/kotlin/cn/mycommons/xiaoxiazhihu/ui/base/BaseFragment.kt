package cn.mycommons.xiaoxiazhihu.ui.base

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import roboguice.activity.RoboActionBarActivity
import roboguice.fragment.RoboFragment

/**
 * BaseFragment <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
abstract class BaseFragment : RoboFragment() {
    /**
     * 每个Fragment自己的布局
     */
    protected abstract fun getFragmentLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
    }

    protected fun setTitle(resId: Int) {
        setTitle(getString(resId))
    }

    protected fun setTitle(title: String?) {
        val actionBar = getSupportActionBar()
        actionBar.title = title
    }

    protected fun getSupportActionBar(): ActionBar {
        val robo: RoboActionBarActivity = activity as RoboActionBarActivity;
        return robo.supportActionBar as ActionBar;
    }
}