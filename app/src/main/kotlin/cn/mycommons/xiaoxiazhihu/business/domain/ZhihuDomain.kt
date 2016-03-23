package cn.mycommons.xiaoxiazhihu.business.domain

import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*
import rx.Observable

/**
 * ZhihuDomain <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
internal interface ZhihuDomain {

    fun getStartInfo(width: Int, height: Int): Observable<GetStartInfoResponse>

    fun getAllThemes(): Observable<GetAllThemesResponse>

    fun getLastTheme(): Observable<GetLastThemeResponse>

    fun getNewsById(id: Int): Observable<GetNewsResponse>

    fun getThemeById(id: Int): Observable<GetThemeResponse>

    fun getStoryExtraById(id: Int): Observable<GetStoryExtraResponse>

    fun getShortCommentsById(id: Int): Observable<GetShortCommentsResponse>

    fun getLongCommentsById(id: Int): Observable<GetLongCommentsResponse>
}