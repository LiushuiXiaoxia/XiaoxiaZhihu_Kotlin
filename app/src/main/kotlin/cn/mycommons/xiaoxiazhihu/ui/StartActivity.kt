package cn.mycommons.xiaoxiazhihu.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.callback.AdvancedSubscriber
import cn.mycommons.xiaoxiazhihu.business.pojo.response.ext.GetStartInfoResponse
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import roboguice.inject.ContentView

/**
 * Start <br/>
 * Created by xiaqiulei on 2016-03-22.
 */
@ContentView(R.layout.activity_start)
class StartActivity : MvpActivity<StartPresenter, IStartView>(), IStartView {

    lateinit var tvText: TextView
    lateinit var ivImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        doGetStartInfo()
        gotoNext()
    }

    private fun init() {
        tvText = find(R.id.tvText)
        ivImage = find(R.id.ivImage)
    }

    private fun doGetStartInfo() {
        mvpPresenter?.doGetStartInfo()
                ?.subscribe(object : AdvancedSubscriber<GetStartInfoResponse>() {
                    override fun onHandleSuccess(response: GetStartInfoResponse) {
                        super.onHandleSuccess(response)

                        update(response)
                    }
                })
    }

    private fun gotoNext() {
        val runnable = {
            startActivity(Intent(getContext(), MainActivity::class.java))
            finish()
        }
        uiHandler.postDelayed(runnable, 3000)
    }

    private fun update(response: GetStartInfoResponse) {
        tvText.text = response.text

        Picasso.with(getContext())
                .load(response.img)
                .into(ivImage)
    }
}