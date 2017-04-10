package mobi.cwiklinski.smsbyt.ui.test

import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import mobi.cwiklinski.smsbyt.provider.CommunicationProvider
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.IPresenter
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import timber.log.Timber
import javax.inject.Inject

class TestPresenter @Inject constructor(val communication: CommunicationProvider,
    val storage: StorageProvider, val schedulers: RxSchedulers) : IPresenter<TestView>() {

    fun startTest(message: TextMessage) {
        communication.sendMessage(message)
                .subscribeOn(schedulers.background)
                .observeOn(schedulers.mainThread)
                .doOnSubscribe {
                    view.canGoPrevious(false)
                    view.canGoNext(false)
                }
                .subscribe({
                    view.canGoPrevious(true)
                    view.canGoNext(true)
                    if (it) {
                        view.onMessageSent(true)
                    } else {
                        view.onMessageSent(false)
                    }
                }, {
                    Timber.e(it)
                    view.canGoPrevious(true)
                    view.canGoNext(true)
                    view.onMessageSent(false)
                })
    }

    fun onFinish() {
        view.finish()
    }

    fun onPrevious() {
        view.onPrevious()
    }
}