package cn.mycommons.xiaoxiazhihu.core.log

import android.util.Log

/**
 * log <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

fun Any.logv(msg: String, vararg args: Any ?): Int {
    var log = msg
    if (args.size != 0) {
        log = String.format(msg, *args)
    }
    return Log.v(this.javaClass.simpleName, log)
}

fun Any.logd(msg: String, vararg args: Any ?): Int {
    var log = msg
    if ( args.size != 0) {
        log = String.format(msg, *args)
    }
    return Log.d(this.javaClass.simpleName, log)
}

fun Any.logi(msg: String, vararg args: Any ?): Int {
    var log = msg
    if ( args.size != 0) {
        log = String.format(msg, *args)
    }
    return Log.i(this.javaClass.simpleName, log)
}

fun Any.logw(msg: String, vararg args: Any ?): Int {
    var log = msg
    if ( args.size != 0) {
        log = String.format(msg, *args)
    }
    return Log.w(this.javaClass.simpleName, log)
}

fun Any.logw(tr: Throwable): Int {
    return Log.w(this.javaClass.simpleName, tr)
}

fun Any.loge(msg: String, vararg args: Any?): Int {
    var log = msg
    if (args.size != 0) {
        log = String.format(msg, *args)
    }
    return Log.e(this.javaClass.simpleName, log)
}

fun Any.loge(msg: String, tr: Throwable): Int {
    return Log.e(this.javaClass.simpleName, msg, tr)
}