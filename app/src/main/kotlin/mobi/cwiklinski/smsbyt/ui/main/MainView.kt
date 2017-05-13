package mobi.cwiklinski.smsbyt.ui.main

import mobi.cwiklinski.smsbyt.ui.IView


interface MainView : IView {

    fun showSetup()
    fun showResetConfirmation()

}