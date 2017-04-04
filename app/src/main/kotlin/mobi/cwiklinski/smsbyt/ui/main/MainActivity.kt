package mobi.cwiklinski.smsbyt.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.ui.base.BaseActivity
import mobi.cwiklinski.smsbyt.ui.sms.SmsFragment

class MainActivity : BaseActivity() {

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPager.adapter = MainAdapter(supportFragmentManager)
    }

    class MainAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        val fragments = arrayOf<Fragment>(
                SmsFragment.newInstance()
        )

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

    }
}
