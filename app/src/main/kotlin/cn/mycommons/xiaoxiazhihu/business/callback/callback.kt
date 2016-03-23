package cn.mycommons.xiaoxiazhihu.business.callback

import android.text.TextUtils
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.app.AppException
import cn.mycommons.xiaoxiazhihu.app.appContextInstance
import cn.mycommons.xiaoxiazhihu.business.pojo.response.BaseResponse
import cn.mycommons.xiaoxiazhihu.core.log.logd
import cn.mycommons.xiaoxiazhihu.core.log.loge
import cn.mycommons.xiaoxiazhihu.core.log.logi
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.ILoadDataView
import com.google.gson.JsonParseException
import org.json.JSONException
import rx.Subscriber
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * callback <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
open class SimpleSubscriber<T : BaseResponse> : Subscriber<T>() {

    private var isSuccess = false // 是否成功
    private val response: T? = null // 得到的数据结果

    override fun onStart() {
        super.onStart()
        logd(this.toString() + "....onStart")
    }

    override fun onNext(response: T?) {
        logd(this.toString() + "....onNext")

        if (response == null) {
            loge("response is null.")
            return
        }
        if (response.isSuccess()) {
            isSuccess = true
            onHandleSuccess(response)
        } else {
            onHandleFail(response.errorMsg, null)
        }
    }

    override fun onError(throwable: Throwable) {
        logd(this.toString() + "....onError")
        onHandleFail(null, throwable)

        onHandleFinish()
    }

    override fun onCompleted() {
        logd(this.toString() + "....onCompleted")

        onHandleFinish()
    }

    /**
     * 处理成功
     */
    open fun onHandleSuccess(response: T) {
        logd("response = " + response)
    }

    /**
     * 处理失败
     */
    open fun onHandleFail(message: String?, throwable: Throwable?) {
        logd(this.toString() + "....onHandleFail")
        loge("message = %s ,throwable = %s", message, throwable)
    }

    /**
     * 处理结束
     */
    open fun onHandleFinish() {
        logd(this.toString() + "....onHandleFinish")
    }

    fun isSucccess(): Boolean = isSuccess;

    fun getResponse(): T? = response;
}

open class AdvancedSubscriber<T : BaseResponse>(private var loadDataView: ILoadDataView? = null) : SimpleSubscriber<T>() {

    override fun onStart() {
        super.onStart()

        loadDataView?.showLoading()
    }

    override fun onHandleSuccess(response: T) {
        logi("response = " + response)
    }

    override fun onHandleFail(message: String?, throwable: Throwable?) {
        super.onHandleFail(message, throwable)

        if (message != null) {
            // 业务异常
            doHandleBusinessFail(message)
        } else if (throwable != null) {
            // 运行异常
            doHandleException(throwable)
        } else {
            // 未知异常
            logi("AdvancedSubscriber.onHandleFail message = null, e = null")
        }
    }

    private fun doHandleBusinessFail(msg: String) {
        logd(this.toString() + "....doHandleBusinessFail")
        if (TextUtils.isEmpty(msg)) {
            showToast("未知错误")
        } else {
            showToast(msg)
        }
    }

    private fun doHandleException(throwable: Throwable?) {
        logd(this.toString() + "....doHandleException")
        if (throwable != null) {
            loge("AdvancedSubscriber.doHandleException throwable = " + throwable.message, throwable)
        }

        var toastText: String? = null
        if (throwable is AppException) {
            val detailException = throwable.detailThrowable
            if (detailException is ConnectException) {
                toastText = "Connect Fail"
            } else if (detailException is UnknownHostException) {
                toastText = "Unknown Host"
            } else if (detailException is TimeoutException || detailException is InterruptedIOException) {
                toastText = "Time out"
            } else if (detailException is JSONException) {
                toastText = "Json error"
            } else if (detailException is JsonParseException) {
                toastText = "Gson parse error"
            }
        }
        if (TextUtils.isEmpty(toastText)) {
            showToast(R.string.network_disable)
        } else {
            showToast("[$toastText]")
        }
    }

    protected fun showToast(msg: Int) {
        showToast(appContextInstance().getString(msg))
    }

    protected fun showToast(msg: String) {
        logd("showToast = " + msg)

        loadDataView?.showError(msg)
    }

    override fun onHandleFinish() {
        super.onHandleFinish()

        loadDataView?.hideLoading()
        loadDataView = null
    }
}