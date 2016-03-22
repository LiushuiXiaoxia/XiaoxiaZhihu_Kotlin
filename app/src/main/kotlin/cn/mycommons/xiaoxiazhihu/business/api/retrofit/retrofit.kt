package cn.mycommons.xiaoxiazhihu.business.api.retrofit

import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * retrofit <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
internal interface IZhihuHttpApi {

    @GET("api/4/start-image/{width}*{height}")
    fun getStartInfoResponse(@Path("width") width: Int,
                             @Path("height") height: Int): Call<GetStartInfoResponse>

    @GET("api/4/themes")
    fun getAllThemesResponse(): Call<GetAllThemesResponse>

    @GET("/api/4/news/latest")
    fun getLastThemeResponse(): Call<GetLastThemeResponse>

    @GET("api/4/news/{id}")
    fun getNewsResponse(@Path("id") id: Int): Call<GetNewsResponse>

    @GET("api/4/theme/{id}")
    fun getThemeResponse(@Path("id") id: Int): Call<GetThemeResponse>

    @GET("api/4/story-extra/{id}")
    fun getStoryExtraResponse(@Path("id") id: Int): Call<GetStoryExtraResponse>

    @GET("api/4/story/{id}/short-comments")
    fun getShortComments(@Path("id") id: Int): Call<GetShortCommentsResponse>

    @GET("api/4/story/{id}/long-comments")
    fun getLongComments(@Path("id") id: Int): Call<GetLongCommentsResponse>
}

internal abstract class RetrofitAdapter<T> {

    @Throws(Exception::class)
    protected abstract fun call(): T

    @Throws(AppException::class)
    fun get(): T {
        val t: T
        try {
            t = call()
        } catch (e: Exception) {
            throw AppException(e)
        }

        return t
    }
}