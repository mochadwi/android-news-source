package io.mochadwi.mobilenews.news_source

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.mochadwi.mobilenews.common.util.PublicMethods

/**
 * Created by mochadwi on 3/13/18.
 */
class NewsSourceActivity : AppCompatActivity() {

    private var mPresenter: NewsSourceContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_source)

        supportActionBar?.title = getString(R.string.message_dashboard)

        var view: NewsSourceFragment? = supportFragmentManager
                .findFragmentById(R.id.fragment_layout) as? NewsSourceFragment

        if (view == null) {
            view = NewsSourceFragment.newInstance()

            PublicMethods.addFragmentToActivity(supportFragmentManager,
                    view, R.id.fragment_layout)
        }

        mPresenter = NewsSourcePresenter(view)
        mPresenter!!.start()
    }
}