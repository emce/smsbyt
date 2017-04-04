package mobi.cwiklinski.smsbyt.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }
}