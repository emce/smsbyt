package mobi.cwiklinski.smsbyt.ui.setup

import android.support.annotation.StringRes
import mobi.cwiklinski.smsbyt.ui.IPresenter
import javax.inject.Inject


class SetupPresenter @Inject constructor() : IPresenter<SetupView>() {

    fun goToSms() {
        view.switchToSms()
    }

    fun goToChannel() {
        view.switchToChannel()
    }

    fun goToUser() {
        view.switchToUser()
    }

    fun goToTest() {
        view.switchToTest()
    }

    fun showMessage(@StringRes message: Int) {
        showMessage(view.getString(message))
    }

    fun showMessage(message: String) {
        view.showMessage(message)
    }
}