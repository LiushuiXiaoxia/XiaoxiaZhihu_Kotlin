package cn.mycommons.xiaoxiazhihu.business.domain.impl

import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.app.InjectHelp
import cn.mycommons.xiaoxiazhihu.business.api.ZhihuApi
import cn.mycommons.xiaoxiazhihu.business.domain.ZhihuDomain
import cn.mycommons.xiaoxiazhihu.business.pojo.request.ext.*
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers

/**
 * ZhihuDomainImpl <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class ZhihuDomainImpl : ZhihuDomain {

    protected var zhihuApi: ZhihuApi

    init {
        zhihuApi = InjectHelp.getInjectInstance(ZhihuApi::class.java)
    }

    override fun getStartInfo(width: Int, height: Int): Observable<GetStartInfoResponse> {
        return Observable.just(GetStartInfoRequest(width, height))
                .flatMap(Func1<GetStartInfoRequest, Observable<GetStartInfoResponse>> { request ->
                    try {
                        val response = zhihuApi.getStartInfoResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetStartInfoResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllThemes(): Observable<GetAllThemesResponse> {
        return Observable.just(GetAllThemesRequest())
                .flatMap(Func1<GetAllThemesRequest, Observable<GetAllThemesResponse>> { request ->
                    try {
                        val response = zhihuApi.getAllThemesResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetAllThemesResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLastTheme(): Observable<GetLastThemeResponse> {
        return Observable.just(GetLastThemeRequest())
                .flatMap(Func1<GetLastThemeRequest, Observable<GetLastThemeResponse>> { request ->
                    try {
                        val response = zhihuApi.getLastThemeResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetLastThemeResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getNewsById(id: Int): Observable<GetNewsResponse> {
        return Observable.just(GetNewsRequest(id))
                .flatMap(Func1<GetNewsRequest, Observable<GetNewsResponse>> { request ->
                    try {
                        val response = zhihuApi.getNewsResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetNewsResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getThemeById(id: Int): Observable<GetThemeResponse> {
        return Observable.just(GetThemeRequest(id))
                .flatMap(Func1<GetThemeRequest, Observable<GetThemeResponse>> { request ->
                    try {
                        val response = zhihuApi.getThemeResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetThemeResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getStoryExtraById(id: Int): Observable<GetStoryExtraResponse> {
        return Observable.just(GetStoryExtraRequest(id))
                .flatMap(Func1<GetStoryExtraRequest, Observable<GetStoryExtraResponse>> { request ->
                    try {
                        val response = zhihuApi.getStoryExtraResponse(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetStoryExtraResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getShortCommentsById(id: Int): Observable<GetShortCommentsResponse> {
        return Observable.just(GetShortCommentsRequest(id))
                .flatMap(Func1<GetShortCommentsRequest, Observable<GetShortCommentsResponse>> { request ->
                    try {
                        val response = zhihuApi.getShortComments(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetShortCommentsResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLongCommentsById(id: Int): Observable<GetLongCommentsResponse> {
        return Observable.just(GetLongCommentsRequest(id))
                .flatMap(Func1<GetLongCommentsRequest, Observable<GetLongCommentsResponse>> { request ->
                    try {
                        val response = zhihuApi.getLongComments(request)
                        return@Func1 Observable.just(response)
                    } catch (e: AppException) {
                        return@Func1 Observable.error<GetLongCommentsResponse>(e)
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}