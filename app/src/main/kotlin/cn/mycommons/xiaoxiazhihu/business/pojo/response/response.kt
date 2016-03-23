package cn.mycommons.xiaoxiazhihu.business.pojo.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * response <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
interface IResponse : Serializable

open class BaseResponse : IResponse {

    @SerializedName("status")
    var status: Int = 0;

    @SerializedName("error_msg")
    var errorMsg: String? = null;

    fun isSuccess(): Boolean = status == 0

    override fun toString(): String {
        return "BaseResponse(status=$status, errorMsg=$errorMsg)"
    }
}