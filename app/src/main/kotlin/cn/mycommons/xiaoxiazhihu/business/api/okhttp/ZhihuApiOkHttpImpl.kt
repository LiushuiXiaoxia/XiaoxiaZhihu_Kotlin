package cn.mycommons.xiaoxiazhihu.business.api.okhttp

import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.business.api.ZhihuApi
import cn.mycommons.xiaoxiazhihu.business.pojo.request.ext.*
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*
import cn.mycommons.xiaoxiazhihu.core.log.logd
import cn.mycommons.xiaoxiazhihu.core.log.loge
import cn.mycommons.xiaoxiazhihu.core.log.logi
import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import java.io.IOException

/**
 * ZhihuApiRetrofitImpl <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class ZhihuApiOkHttpImpl : ZhihuApi {

    internal var okHttpClient: OkHttpClient
    internal var gson: Gson

    init {
        okHttpClient = newOkHttpClient()
        gson = Gson()
    }

    @Throws(AppException::class)
    override fun getStartInfoResponse(request: GetStartInfoRequest): GetStartInfoResponse {
        logd("ZhihuApiOkHttpImpl.getStartInfoResponse request = " + request)

        var url = "http://news-at.zhihu.com/api/4/start-image/%d*%d"
        url = String.format(url, request.width, request.height)
        val response = get(url, GetStartInfoResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getAllThemesResponse(request: GetAllThemesRequest): GetAllThemesResponse {
        logd("ZhihuApiOkHttpImpl.getAllThemesResponse request = " + request)

        val url = "http://news-at.zhihu.com/api/4/themes"
        val response = get(url, GetAllThemesResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getLastThemeResponse(request: GetLastThemeRequest): GetLastThemeResponse {
        logd("ZhihuApiOkHttpImpl.getLastThemeResponse request = " + request)

        val url = "http://news-at.zhihu.com/api/4/news/latest"
        val response = get(url, GetLastThemeResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getNewsResponse(request: GetNewsRequest): GetNewsResponse {
        logd("ZhihuApiOkHttpImpl.getNewsResponse request = " + request)

        var url = "http://news-at.zhihu.com/api/4/news/%s"
        url = String.format(url, request.id)
        val response = get(url, GetNewsResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getThemeResponse(request: GetThemeRequest): GetThemeResponse {
        logd("ZhihuApiOkHttpImpl.getThemeResponse request = " + request)

        var url = "http://news-at.zhihu.com/api/4/theme/%s"
        url = String.format(url, request.id)
        val response = get(url, GetThemeResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getStoryExtraResponse(request: GetStoryExtraRequest): GetStoryExtraResponse {
        logd("ZhihuApiOkHttpImpl.getStoryExtraResponse request = " + request)

        var url = "http://news-at.zhihu.com/api/4/story-extra/%s"
        url = String.format(url, request.id)
        val response = get(url, GetStoryExtraResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getShortComments(request: GetShortCommentsRequest): GetShortCommentsResponse {
        logd("ZhihuApiOkHttpImpl.getShortComments request = " + request)

        var url = "http://news-at.zhihu.com/api/4/story/%s/short-comments"
        url = String.format(url, request.id)
        val response = get(url, GetShortCommentsResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    override fun getLongComments(request: GetLongCommentsRequest): GetLongCommentsResponse {
        logd("ZhihuApiOkHttpImpl.getLongComments request = " + request)

        var url = "http://news-at.zhihu.com/api/4/story/%s/long-comments"
        url = String.format(url, request.id)
        val response = get(url, GetLongCommentsResponse::class.java)

        logi("req = %s, resp = %s", request, response)

        return response
    }

    @Throws(AppException::class)
    internal operator fun <T> get(url: String, tClass: Class<T>): T {
        logi("ZhihuApiOkHttpImpl.get url = " + url)
        val request = Request.Builder().url(url).get().cacheControl(newOkhttpCacheControl()).build()

        var t: T? = null
        try {
            val response = okHttpClient.newCall(request).execute()
            logi("response = " + response)
            if (response.isSuccessful) {
                val body = response.body().string()
                t = gson.fromJson(body, tClass)
            }
        } catch (e: IOException) {
            loge("ZhihuApiOkHttpImpl.get e = " + e, e)
            throw AppException(e)
        }

        return t as T
    }
}