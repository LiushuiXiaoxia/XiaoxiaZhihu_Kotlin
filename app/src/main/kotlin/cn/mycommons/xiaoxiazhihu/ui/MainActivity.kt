package cn.mycommons.xiaoxiazhihu.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.ThemeItem
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetAllThemesResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpActivity
import cn.mycommons.xiaoxiazhihu.ui.home.HotnewsFragment
import cn.mycommons.xiaoxiazhihu.ui.home.OtherThemeFragment
import org.jetbrains.anko.find

/**
 * MainActivity <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class MainActivity : MvpActivity<MainPresenter, IMenuListView>(), IMenuListView {

    lateinit internal var llMainMenuContainer: LinearLayout
    internal var currentThemeItem: ThemeItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        initSlideMenu()
        updateFragment()
        doGetAllThemesResponse()
    }

    private fun init() {
        val toolbar: Toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer: DrawerLayout = find(R.id.drawer_layout)
        val close = R.string.navigation_drawer_close
        val open = R.string.navigation_drawer_open
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, open, close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initSlideMenu() {
        val navigationView: NavigationView = find(R.id.nav_view)
        llMainMenuContainer = navigationView.getHeaderView(0).find(R.id.llMainMenuContainer)

        llMainMenuContainer.findViewById(R.id.tvHome).setOnClickListener { v ->
            if (currentThemeItem != null) {
                currentThemeItem = null
                updateFragment()
            }

            clearBackground(v)
            val drawer: DrawerLayout = find(R.id.drawer_layout)
            drawer.closeDrawer(GravityCompat.START)
        }
    }

    private fun doGetAllThemesResponse() {
        mvpPresenter?.doGetAllThemesResponse()
                ?.subscribe(object : AdvancedSubscriber<GetAllThemesResponse>() {
                    override fun onHandleSuccess(response: GetAllThemesResponse) {
                        super.onHandleSuccess(response)

                        update(response.others)
                    }
                })
    }

    private fun update(themeItems: Array<ThemeItem>?) {
        if (themeItems == null) {
            return
        }

        while (llMainMenuContainer.childCount > 1) {
            llMainMenuContainer.removeViewAt(llMainMenuContainer.childCount - 1)
        }

        val inflater = LayoutInflater.from(this)
        for (item in themeItems) {
            val view = inflater.inflate(R.layout.item_main_menu, llMainMenuContainer, false)
            view.find<TextView>(R.id.menu).text = item.name
            view.setOnClickListener { v ->
                if (currentThemeItem !== item) {
                    currentThemeItem = item
                    updateFragment()
                    clearBackground(v)
                }
                val drawer = findViewById(R.id.drawer_layout) as DrawerLayout?
                drawer?.closeDrawer(GravityCompat.START)
            }
            llMainMenuContainer.addView(view)
        }
    }

    private fun updateFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment: Fragment
        if (currentThemeItem == null) {
            fragment = HotnewsFragment()
        } else {
            fragment = OtherThemeFragment.newInstance(currentThemeItem as ThemeItem)
        }
        transaction.replace(R.id.flFragmentContainer, fragment, "content_fragment").commitAllowingStateLoss()
    }

    private fun clearBackground(selectView: View) {
        for (i in 0..(llMainMenuContainer.childCount - 1)) {
            llMainMenuContainer.getChildAt(i).setBackgroundColor(Color.TRANSPARENT)
        }
        selectView.setBackgroundColor(Color.parseColor("#cccccc"))
    }

    override fun onBackPressed() {
        val drawer: DrawerLayout = find(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}