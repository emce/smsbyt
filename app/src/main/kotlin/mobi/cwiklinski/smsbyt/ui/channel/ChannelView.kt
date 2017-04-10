package mobi.cwiklinski.smsbyt.ui.channel

import mobi.cwiklinski.smsbyt.ui.IView


interface ChannelView : IView {
    fun openTelegramConversation(): Boolean
    fun openTelegramInPlayStore()
    fun onNext()
    fun onPrevious()
    fun showConversationError()
    fun canGoNext(canGo: Boolean)
    fun canGoPrevious(canGo: Boolean)
}