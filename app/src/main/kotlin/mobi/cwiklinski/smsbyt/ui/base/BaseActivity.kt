package mobi.cwiklinski.smsbyt.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import mobi.cwiklinski.smsbyt.util.getInputMethodManager


abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    fun hideKeyboard() {
        if (currentFocus != null) {
            getInputMethodManager().hideSoftInputFromWindow(currentFocus.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}