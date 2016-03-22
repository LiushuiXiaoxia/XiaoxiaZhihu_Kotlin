package cn.mycommons.xiaoxiazhihu.business.pojo.request.ext

import cn.mycommons.xiaoxiazhihu.business.pojo.request.BaseRequest

/**
 * request_ext <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
class GetAllThemesRequest : BaseRequest() {

    override fun toString(): String{
        return "GetAllThemesRequest() ${super.toString()}"
    }
}

class GetLastThemeRequest : BaseRequest() {

    override fun toString(): String{
        return "GetLastThemeRequest() ${super.toString()}"
    }
}

class GetLongCommentsRequest(var id: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetLongCommentsRequest(id=$id) ${super.toString()}"
    }
}

class GetNewsRequest(var id: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetNewsRequest(id=$id) ${super.toString()}"
    }
}

class GetShortCommentsRequest(var id: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetShortCommentsRequest(id=$id) ${super.toString()}"
    }
}

class GetStartInfoRequest(var width: Int, var height: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetStartInfoRequest(width=$width, height=$height) ${super.toString()}"
    }
}

class GetStoryExtraRequest(var id: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetStoryExtraRequest(id=$id) ${super.toString()}"
    }
}

class GetThemeRequest(var id: Int) : BaseRequest() {

    override fun toString(): String{
        return "GetThemeRequest(id=$id) ${super.toString()}"
    }
}