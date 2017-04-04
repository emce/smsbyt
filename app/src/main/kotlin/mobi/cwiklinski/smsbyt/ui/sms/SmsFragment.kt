package mobi.cwiklinski.smsbyt.ui.sms

import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.ui.base.BaseFragment


class SmsFragment : BaseFragment() {

    companion object {
        fun newInstance() = SmsFragment()
    }

    override fun inject() {
        App.get().feather.injectFields(this)
    }
}