package mobi.cwiklinski.smsbyt.ui.main

import android.support.annotation.StringRes
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.ui.IPresenter
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import javax.inject.Inject


class MainPresenter @Inject constructor(val storage: StorageProvider, val schedulers: RxSchedulers)
    : IPresenter<MainView>() {

    override fun start() {
        super.start()
        /*var current = 0
        if (view.hasSmsPermission()) {
            current = 1
        }
        if (storage.get(StorageProvider.KEY_CHANNEL_NAME, "").isNotEmpty()) {
            current = 2
        }
        if (storage.get(StorageProvider.KEY_USER_NAME, "").isNotEmpty()) {
            current = 3
        }
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulers.background)
                .observeOn(schedulers.mainThread)
                .subscribe({
                    when (current) {
                        1 -> view.switchToChannel()
                        2 -> view.switchToUser()
                        3 -> view.switchToTest()
                    }
                }, {
                    Timber.e(it)
                })
                .addTo(subscriptions)*/
    }

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