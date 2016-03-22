package cn.mycommons.xiaoxiazhihu.app

/**
 * app <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

interface ILife {
    /**
     * 初始化
     */
    fun init()

    /**
     * 注销
     */
    fun unInit()
}

interface IValidate {

    fun validate(): Boolean
}