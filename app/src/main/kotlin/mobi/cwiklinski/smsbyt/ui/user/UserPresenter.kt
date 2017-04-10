package mobi.cwiklinski.smsbyt.ui.user

import mobi.cwiklinski.smsbyt.network.Api
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.IPresenter
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import timber.log.Timber
import javax.inject.Inject


class UserPresenter @Inject constructor(val api: Api, val storage: StorageProvider, val schedulers: RxSchedulers)
    : IPresenter<UserView>() {

    fun onNext() {
        if (view.isUserNameValid()) {
            api.getUpdates()
                    .subscribeOn(schedulers.background)
                    .observeOn(schedulers.mainThread)
                    .subscribe({
                        if (it.isNotEmpty()) {
                            val userMessage = it.reversed().filter { it.from?.userName == view.getUserName() && it.chat?.id ?: 0 > 0 }
                                    .firstOrNull()
                            if (userMessage != null) {
                                val chat = userMessage.chat
                                if (chat != null && chat.id != null) {
                                    storage.save(StorageProvider.KEY_CHANNEL_ID, chat.id ?: 0)
                                    storage.save(StorageProvider.KEY_USER_NAME, view.getUserName())
                                    view.onNext()
                                } else {
                                    view.showConversationError()
                                }
                            } else {
                                view.showConversationError()
                            }
                        } else {
                            view.showUserNameError()
                        }
                    }, {
                        Timber.e(it)
                        view.showUserNameError()
                    })



        } else {
            view.showUserNameError()
        }
    }

    fun onPrevious() {
        view.onPrevious()
    }
}