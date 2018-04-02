package io.mochadwi.mobilenews.news_source

import android.content.Context
import io.mochadwi.mobilenews.common.BasePresenter
import io.mochadwi.mobilenews.common.BaseView
import io.mochadwi.mobilenews.domain.data.news_source.SourcesItem

/**
 * Created by mochadwi on 3/13/18.
 */
interface NewsSourceContract {
    interface View : BaseView<Presenter> {
        fun setRecyclerView(data: List<SourcesItem?>?)
        fun setDataNotAvailable()
        fun setupAnimation(ctx: Context)
    }

    interface Presenter : BasePresenter {
        fun getNews()
        fun getOfflineNews()
        fun isOfflineNewsEmpty(): Boolean
    }
}