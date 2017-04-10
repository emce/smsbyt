package mobi.cwiklinski.smsbyt.ui.main

import mobi.cwiklinski.smsbyt.ui.IView


interface MainView : IView {

    fun switchToSms()
    fun switchToChannel()
    fun switchToUser()
    fun switchToTest()
    fun showMessage(message: String)
    fun hasSmsPermission(): Boolean

}