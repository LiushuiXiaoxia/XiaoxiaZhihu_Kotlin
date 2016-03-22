package cn.mycommons.xiaoxiazhihu.ui.base.mvp

import java.lang.reflect.ParameterizedType

/**
 * MvpHelper <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
internal class MvpHelper<P : BaseMvpPresenter<V>, V : IMvpView>(var target: Any) {

    /**
     * 返回实现[P]的class，子类没有实现则为null
     */
    internal fun getPresenterClass(): Class<P>? {
        var pClass: Class<P>? = null

        do {
            val genType = target.javaClass.genericSuperclass
            if (genType !is ParameterizedType) {
                break
            }
            val params = genType.actualTypeArguments
            if (params == null || params.size < 1) {
                break
            }
            if (params[0] != null && params[0] is Class<*>) {
                pClass = params[0] as Class<P>
            }
        } while (false)

        return pClass
    }

    /**
     * 返回实现[V]的class，子类没有实现则为null
     */
    internal fun getViewClass(): Class<V> {
        var vClass: Class<V>? = null

        do {
            val genType = target.javaClass.genericSuperclass
            if (genType !is ParameterizedType) {
                break
            }
            val params = genType.actualTypeArguments
            if (params == null || params.size < 2) {
                break
            }
            if (params[1] != null && params[1] is Class<*>) {
                vClass = params[1] as Class<V>
            }
        } while (false)

        return vClass as Class<V>
    }
}