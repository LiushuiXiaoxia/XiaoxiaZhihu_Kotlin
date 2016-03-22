package cn.mycommons.xiaoxiazhihu.business.api.okhttp

import com.squareup.okhttp.CacheControl
import com.squareup.okhttp.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * okhttp <br/>
 * Created by xiaqiulei on 2016-03-22.
 */

private val HTTP_TIME_OUT = 15

internal fun newOkHttpClient(): OkHttpClient {
    val client = OkHttpClient()
    client.setReadTimeout(HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
    return client
}

internal fun newOkhttpCacheControl(): CacheControl {
    return CacheControl.Builder().noCache().build()
}