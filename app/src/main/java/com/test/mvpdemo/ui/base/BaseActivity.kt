package test.gojek.gojektest.ui.base


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.test.mvpdemo.ui.base.BaseView

abstract class BaseActivity< p : BasePresenter> : AppCompatActivity() {

    lateinit protected var presenter: p


    /**
     * get the layout of the activity
     *
     * @return
     */
    protected abstract fun getLayout(): Int

    /**
     * initialize the presenter
     *
     * @return
     */
    protected abstract fun initPresenter(): p

    var view : BaseView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        presenter = initPresenter()

    }

    override fun onPause() {
        super.onPause()
        view?.let { presenter.detachView() }
    }

    override fun onResume() {
        super.onResume()
        view?.let { presenter.attachView(view as BaseView) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onPresenterDestroy()
    }
}