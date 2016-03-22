package cn.mycommons.xiaoxiazhihu.business.pojo.request

import java.io.Serializable

/**
 * request <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
interface IRequest : Serializable

open class BaseRequest : IRequest {

    override fun toString(): String {
        return "BaseRequest()"
    }
}