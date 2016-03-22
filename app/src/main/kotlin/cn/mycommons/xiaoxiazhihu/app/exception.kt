package cn.mycommons.xiaoxiazhihu.app

/**
 * exception <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class AppException(val detailThrowable: Throwable) : Throwable(detailThrowable) {

    override fun toString(): String {
        return "AppException{detailThrowable=$detailThrowable}"
    }
}