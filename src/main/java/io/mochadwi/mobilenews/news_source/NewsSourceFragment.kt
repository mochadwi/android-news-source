package io.mochadwi.mobilenews.news_source

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import io.mochadwi.mobilenews.R
import io.mochadwi.mobilenews.domain.data.news_source.SourcesItem
import io.mochadwi.mobilenews.news_source.adapter.NewsSourceAdapter

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceFragment : Fragment(), NewsSourceContract.View {

    // DATA
    private var mPresenter: NewsSourceContract.Presenter? = null
    private var mAdapter: NewsSourceAdapter? = null

    // UI
    @BindView(R.id.rv_items) internal lateinit var mRvItems: RecyclerView
    @BindView(R.id.txt_empty_items) internal lateinit var mTxtItems: TextView

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
        ButterKnife.bind(this, view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (mPresenter!!.isOfflineNewsEmpty()) {
            mPresenter!!.getNews()
        } else {
            mPresenter!!.getOfflineNews()
        }
    }

    override fun setRecyclerView(data: List<SourcesItem?>?) {

        mTxtItems.visibility = View.GONE
        mRvItems.visibility = View.VISIBLE

        mRvItems.layoutManager = GridLayoutManager(context, 2)
        mAdapter = NewsSourceAdapter(context, data as? ArrayList<SourcesItem?>)
        mRvItems.adapter = mAdapter
    }

    override fun setDataNotAvailable() {
        mTxtItems.visibility = View.VISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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
