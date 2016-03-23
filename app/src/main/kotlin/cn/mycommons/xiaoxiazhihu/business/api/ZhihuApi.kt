package cn.mycommons.xiaoxiazhihu.business.api

import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.business.pojo.request.ext.*
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.*

/**
 * ZhihuApi <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
interface ZhihuApi {

    // 1.启动界面图像获取
    @Throws(AppException::class)
    fun getStartInfoResponse(request: GetStartInfoRequest): GetStartInfoResponse

    // 9.主题日报列表查看
    @Throws(AppException::class)
    fun getAllThemesResponse(request: GetAllThemesRequest): GetAllThemesResponse

    // 3.最新消息
    @Throws(AppException::class)
    fun getLastThemeResponse(request: GetLastThemeRequest): GetLastThemeResponse

    // 4.消息内容获取与离线下载
    @Throws(AppException::class)
    fun getNewsResponse(request: GetNewsRequest): GetNewsResponse

    // 10. 主题日报内容查看
    @Throws(AppException::class)
    fun getThemeResponse(request: GetThemeRequest): GetThemeResponse

    // 6.新闻额外信息
    @Throws(AppException::class)
    fun getStoryExtraResponse(request: GetStoryExtraRequest): GetStoryExtraResponse

    // 8.新闻对应短评论查看
    @Throws(AppException::class)
    fun getShortComments(request: GetShortCommentsRequest): GetShortCommentsResponse

    // 7.新闻对应长评论查看
    @Throws(AppException::class)
    fun getLongComments(request: GetLongCommentsRequest): GetLongCommentsResponse
}