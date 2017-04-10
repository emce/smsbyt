package mobi.cwiklinski.smsbyt.ui.main

import android.Manifest
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.ui.base.BaseActivity
import mobi.cwiklinski.smsbyt.ui.channel.ChannelFragment
import mobi.cwiklinski.smsbyt.ui.sms.SmsFragment
import mobi.cwiklinski.smsbyt.ui.test.TestFragment
import mobi.cwiklinski.smsbyt.ui.user.UserFragment
import mobi.cwiklinski.smsbyt.util.hasPermissions
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter

    override fun inject() {
        App.get().feather.injectFields(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        mainPager.adapter = MainAdapter(supportFragmentManager)
        mainPager.currentItem = 0
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }

    override fun switchToSms() {
        mainPager.currentItem = 0
    }

    override fun switchToChannel() {
        mainPager.currentItem = 1
    }

    override fun switchToUser() {
        mainPager.currentItem = 2
    }

    override fun switchToTest() {
        mainPager.currentItem = 3
    }

    override fun showMessage(message: String) {
        val snackBar = Snackbar.make(mainCoordinator, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(R.string.close) {
            snackBar.dismiss()
        }
        snackBar.show()
    }

    override fun hasSmsPermission(): Boolean = hasPermissions(Manifest.permission.READ_SMS)

    class MainAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        val fragments = listOf<Fragment>(
                SmsFragment.newInstance(),
                ChannelFragment.newInstance(),
                UserFragment.newInstance(),
                TestFragment.newInstance()
        )

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

    }
}
