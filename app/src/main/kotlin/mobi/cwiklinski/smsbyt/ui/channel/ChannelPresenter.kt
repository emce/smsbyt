package mobi.cwiklinski.smsbyt.ui.channel

import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.IPresenter
import javax.inject.Inject


class ChannelPresenter @Inject constructor(val storage: StorageProvider) : IPresenter<ChannelView>() {

    fun onPrevious() {
        view.canGoPrevious(false)
        view.onPrevious()
    }

    fun onConversationOpen() {
        view.openTelegramConversation()
    }
}