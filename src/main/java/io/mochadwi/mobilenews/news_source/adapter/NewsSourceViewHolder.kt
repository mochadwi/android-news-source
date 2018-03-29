package io.mochadwi.mobilenews.news_source.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.mochadwi.mobilenews.news_source.R

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.cv_item)
    lateinit var mCvItem: CardView
    @BindView(R.id.txt_title)
    lateinit var mTxtTitle: TextView
    @BindView(R.id.txt_supporting)
    lateinit var mTxtTitleSupport: TextView
    @BindView(R.id.txt_source)
    lateinit var mTxtSource: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}
