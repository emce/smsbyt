package mobi.cwiklinski.smsbyt.ui.test

import mobi.cwiklinski.smsbyt.ui.IView

interface TestView : IView {

    fun onPrevious()
    fun finish()
    fun onMessageSent(successful: Boolean)
    fun canGoNext(canGo: Boolean)
    fun canGoPrevious(canGo: Boolean)

}