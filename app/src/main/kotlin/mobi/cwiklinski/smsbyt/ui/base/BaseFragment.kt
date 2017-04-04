package mobi.cwiklinski.smsbyt.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment


abstract class BaseFragment : Fragment() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    fun getBaseActivity() = activity as BaseActivity
}