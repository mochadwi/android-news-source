package io.mochadwi.mobilenews.news_source.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.mochadwi.mobilenews.domain.model.news_source.SourcesItem
import io.mochadwi.mobilenews.news_source.R

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceAdapter : RecyclerView.Adapter<NewsSourceViewHolder> {

    private var mNews: ArrayList<SourcesItem?>? = null
    private val mCtx: Context

    constructor(c: Context, dataIn: ArrayList<SourcesItem?>?) {
        this.mCtx = c
        mNews = dataIn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_source, parent, false)
        return NewsSourceViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int)
            = holder.bind(mNews!![position]!!, mCtx)

    override fun getItemCount(): Int {
        return mNews?.size ?: 0
    }
}
