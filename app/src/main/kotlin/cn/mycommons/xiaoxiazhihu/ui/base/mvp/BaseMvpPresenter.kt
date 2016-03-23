package cn.mycommons.xiaoxiazhihu.ui.base.mvp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.mycommons.xiaoxiazhihu.app.InjectHelp
import de.greenrobot.event.EventBus
import de.greenrobot.event.EventBusException

/**
 * BaseMvpPresenter <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
open class BaseMvpPresenter<V : IMvpView> : IMvpPresenter {

    protected var loadDataView: ILoadDataView? = null
    protected var view: V? = null // View层模型
    protected var context: Context? = null
    private var eventBus: EventBus? = null

    fun initMvpPresenter(loadDataView: ILoadDataView?, view: V?) {
        this.loadDataView = loadDataView
        this.view = view
        this.context = loadDataView?.getContext()

        InjectHelp.injectMembersWithoutViews(this)
    }

    /**
     * Call in [android.app.Activity.onCreate] after Presenter created.

     * @param savedInstanceState Bundle from [android.app.Activity.onCreate]
     * *
     * @param activityIntent     Bundle from [android.app.Activity.getIntent]
     */
    fun initInActivity(savedInstanceState: Bundle?, activityIntent: Intent) {
        // NO OP
    }

    /**
     * Call in [android.app.Fragment.onViewCreated]
     * after Presenter created.

     * @param savedInstanceState Bundle from [android.app.Fragment.onCreate]
     * *
     * @param fragmentArguments  Bundle from [android.app.Fragment.getArguments]
     */
    fun initInFragment(savedInstanceState: Bundle?, fragmentArguments: Bundle?) {
        // NO OP
    }

    override fun create(savedInstanceState: Bundle?) {
        // 注册Presenter
        registerEventBusListener(this)
    }

    override fun start() {
        // NO OP
    }

    override fun resume() {
        // NO OP
    }

    override fun pause() {
        // NO OP
    }

    override fun stop() {
        // NO OP
    }

    override fun destory() {
        unregisterEventBusListener(this)
        this.loadDataView = null
        this.view = null
        this.context = null
        this.eventBus = null
    }

    /**
     * 所有子类通过此方法获取EventBus，这样子类可以通过复写此方法获取自己的EventBus

     * @return eventBus
     */
    fun getEventBus(): EventBus? {
        if (eventBus == null) {
            eventBus = InjectHelp.getEventBus()
        }
        return eventBus
    }

    fun registerEventBusListener(obj: Any) {
        try {
            getEventBus()?.register(obj)
        } catch (eventBusException: EventBusException) {
            // 如果object没有任何onEvent等订阅，会导致EventBusException，此处try-catch防止崩溃
        }
    }

    fun unregisterEventBusListener(obj: Any) {
        getEventBus()?.unregister(obj)
    }

    fun onEvent(obj: Any) {
    }
}