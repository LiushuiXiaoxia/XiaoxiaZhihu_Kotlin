package cn.mycommons.xiaoxiazhihu.business.api.retrofit

import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.business.api.ZhihuApi
import cn.mycommons.xiaoxiazhihu.business.pojo.request.ext.*
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*
import cn.mycommons.xiaoxiazhihu.core.log.logi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ZhihuApiRetrofitImpl <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class ZhihuApiRetrofitImpl : ZhihuApi {

    private var httpApi: IZhihuHttpApi

    init {
        val builder = OkHttpClient.Builder()
        // set time out interval
        builder.readTimeout(15, TimeUnit.MINUTES)
        builder.connectTimeout(15, TimeUnit.MINUTES)
        builder.writeTimeout(15, TimeUnit.MINUTES)
        builder.interceptors().add(Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            logi("Interceptor:request = %s, response = %s", request, response)
            response
        })

        httpApi = Retrofit.Builder()
                .client(builder.build())
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IZhihuHttpApi::class.java)
    }

    @Throws(AppException::class)
    override fun getStartInfoResponse(request: GetStartInfoRequest): GetStartInfoResponse {
        logi("ZhihuApiRetrofitImpl.getStartInfoResponse request = " + request)

        return object : RetrofitAdapter<GetStartInfoResponse>() {
            @Throws(Exception::class)
            override fun call(): GetStartInfoResponse {
                return httpApi.getStartInfoResponse(request.width, request.height).execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getAllThemesResponse(request: GetAllThemesRequest): GetAllThemesResponse {
        logi("ZhihuApiRetrofitImpl.getAllThemesResponse request = " + request)
        return object : RetrofitAdapter<GetAllThemesResponse>() {
            @Throws(Exception::class)
            override fun call(): GetAllThemesResponse {
                return httpApi.getAllThemesResponse().execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getLastThemeResponse(request: GetLastThemeRequest): GetLastThemeResponse {
        logi("ZhihuApiRetrofitImpl.getLastThemeResponse request = " + request)
        return object : RetrofitAdapter<GetLastThemeResponse>() {
            @Throws(Exception::class)
            override fun call(): GetLastThemeResponse {
                return httpApi.getLastThemeResponse().execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getNewsResponse(request: GetNewsRequest): GetNewsResponse {
        logi("ZhihuApiRetrofitImpl.getNewsResponse request = " + request)
        return object : RetrofitAdapter<GetNewsResponse>() {
            @Throws(Exception::class)
            override fun call(): GetNewsResponse {
                return httpApi.getNewsResponse(request.id).execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getThemeResponse(request: GetThemeRequest): GetThemeResponse {
        logi("ZhihuApiRetrofitImpl.getThemeResponse request = " + request)
        return object : RetrofitAdapter<GetThemeResponse>() {
            @Throws(Exception::class)
            override fun call(): GetThemeResponse {
                return httpApi.getThemeResponse(request.id).execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getStoryExtraResponse(request: GetStoryExtraRequest): GetStoryExtraResponse {
        logi("ZhihuApiRetrofitImpl.getStoryExtraResponse request = " + request)
        return object : RetrofitAdapter<GetStoryExtraResponse>() {
            @Throws(Exception::class)
            override fun call(): GetStoryExtraResponse {
                return httpApi.getStoryExtraResponse(request.id).execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getShortComments(request: GetShortCommentsRequest): GetShortCommentsResponse {
        logi("ZhihuApiRetrofitImpl.getShortComments request = " + request)
        return object : RetrofitAdapter<GetShortCommentsResponse>() {
            @Throws(Exception::class)
            override fun call(): GetShortCommentsResponse {
                return httpApi.getShortComments(request.id).execute().body()
            }
        }.get()
    }

    @Throws(AppException::class)
    override fun getLongComments(request: GetLongCommentsRequest): GetLongCommentsResponse {
        logi("ZhihuApiRetrofitImpl.GetStartInfoResponse request = " + request)
        return object : RetrofitAdapter<GetLongCommentsResponse>() {
            @Throws(Exception::class)
            override fun call(): GetLongCommentsResponse {
                return httpApi.getLongComments(request.id).execute().body()
            }
        }.get()
    }
}