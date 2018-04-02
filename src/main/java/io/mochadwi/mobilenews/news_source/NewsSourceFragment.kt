package io.mochadwi.mobilenews.news_source

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.mochadwi.mobilenews.domain.data.news_source.SourcesItem
import io.mochadwi.mobilenews.news_source.adapter.NewsSourceAdapter
import kotlinx.android.synthetic.main.content_news_source.*
import org.jetbrains.anko.windowManager

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceFragment : Fragment(), NewsSourceContract.View {

    // DATA
    private var mPresenter: NewsSourceContract.Presenter? = null
    private var mAdapter: NewsSourceAdapter? = null

    private var progress: ProgressDialog? = null

    override fun onStart() {
        super.onStart()
        mPresenter!!.start()
    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.start()
    }

    override fun setPresenter(presenter: NewsSourceContract.Presenter) {
        this.mPresenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.content_news_source, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (mPresenter!!.isOfflineNewsEmpty()) {
            mPresenter!!.getNews()
        } else {
            mPresenter!!.getOfflineNews()
        }

        setupAnimation(context)
    }

    override fun setRecyclerView(data: List<SourcesItem?>?) {

        activity.txt_empty_items.visibility = View.GONE
        activity.rv_items.visibility = View.VISIBLE

        activity.rv_items.layoutManager = GridLayoutManager(context, 2)
        mAdapter = NewsSourceAdapter(context, data as? ArrayList<SourcesItem?>)
        activity.rv_items.adapter = mAdapter
    }

    override fun setDataNotAvailable() {
        activity.txt_empty_items.visibility = View.VISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NewApi")
    override fun setupAnimation(ctx: Context) {
        if (Build.VERSION.SDK_INT >= 21) {
            val slide = TransitionInflater.from(ctx).inflateTransition(R.transition.slide)
            activity.window.exitTransition = slide
        }
    }

    override fun showProgress() {
        progress = ProgressDialog(context)
        progress!!.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress!!.show()
        progress!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress!!.setContentView(R.layout.progress_bar)
    }

    override fun hideProgress() {
        if (progress != null) { //null checker
            progress!!.dismiss()
        }
    }

    companion object {

        fun newInstance(): NewsSourceFragment {
            return NewsSourceFragment()
        }
    }
}
