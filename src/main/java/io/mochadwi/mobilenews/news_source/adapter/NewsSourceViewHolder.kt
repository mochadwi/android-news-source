package io.mochadwi.mobilenews.news_source.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import io.mochadwi.mobilenews.articles.ArticlesActivity
import io.mochadwi.mobilenews.domain.data.news_source.SourcesItem
import io.mochadwi.mobilenews.news_source.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news_source.*

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer{

    fun bind(item: SourcesItem, ctx: Context) = with(containerView) {
        cv_item.setOnClickListener {
            val i = Intent(ctx, ArticlesActivity::class.java)
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                                                        ctx as Activity,
                                                        containerView,
                                                        "")
            i.putExtra("sources", item.toString())

            if (Build.VERSION.SDK_INT >= 16) {
                ctx.startActivity(i, optionsCompat.toBundle())
            } else {
                ctx.startActivity(i)
            }
        }

        txt_title.text = item.name
        txt_supporting.text = item.description
        txt_source.text = ctx.getString(R.string.message_news_source, item.url)
    }
}
