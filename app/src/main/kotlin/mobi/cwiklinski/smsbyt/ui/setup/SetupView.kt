package mobi.cwiklinski.smsbyt.ui.setup

import mobi.cwiklinski.smsbyt.ui.IView


interface SetupView : IView {

    fun switchToSms()
    fun switchToChannel()
    fun switchToUser()
    fun switchToTest()
    fun showMessage(message: String)
    fun hasSmsPermission(): Boolean

}