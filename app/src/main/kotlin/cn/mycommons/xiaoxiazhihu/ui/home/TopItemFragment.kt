package cn.mycommons.xiaoxiazhihu.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.Bind
import butterknife.ButterKnife
import cn.mycommons.xiaoxiazhihu.R
import cn.mycommons.xiaoxiazhihu.business.pojo.bean.LastTemeTopStory
import cn.mycommons.xiaoxiazhihu.ui.base.common.FragmentLauncher
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.BaseMvpPresenter
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.IMvpView
import cn.mycommons.xiaoxiazhihu.ui.base.mvp.MvpFragment
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * TopItemFragment <br/>
 * Created by xiaqiulei on 2016-03-23.
 */
class TopItemFragment : MvpFragment<BaseMvpPresenter<IMvpView>, IMvpView>() {

    @Bind(R.id.text)
    lateinit internal var text: TextView
    @Bind(R.id.icon)
    lateinit internal var icon: ImageView
    lateinit internal var topStory: LastTemeTopStory

    override fun getFragmentLayout() = R.layout.item_top_item_fragment

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButterKnife.bind(this, view)
        text = view?.find(R.id.text)!!
        icon = view?.find(R.id.icon)!!

        text.text = topStory.title
        Picasso.with(icon.context)
                .load(topStory.image)
                .into(icon)

        view?.setOnClickListener { v ->
            val param = DetailExtraParam()
            param.fragmentClass = DetailFragment::class.java
            param.id = topStory.id
            FragmentLauncher.launch(context, param)
        }
    }

    fun setTopStory(topStory: LastTemeTopStory) {
        this.topStory = topStory
    }
}