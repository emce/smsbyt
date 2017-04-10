package mobi.cwiklinski.smsbyt.ui.sms

import mobi.cwiklinski.smsbyt.ui.IPresenter
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import mobi.cwiklinski.smsbyt.util.SmsPermission
import rx.lang.kotlin.addTo
import javax.inject.Inject

class SmsPresenter @Inject constructor(val schedulers: RxSchedulers) : IPresenter<SmsView>() {

    override fun start() {
        super.start()
        view.canGoNext(view.canReadSms())
    }

    fun onNext() {
        view.canGoNext(false)
        if (view.canReadSms()) {
            view.goToChannel()
        } else {
            view.showPermissionWarning()
            view.canGoNext(true)
        }
    }

    fun onPermission() {
        view.getPermissionRequest()
                .subscribeOn(schedulers.background)
                .observeOn(schedulers.mainThread)
                .doOnSubscribe { view.canGoNext(false) }
                .subscribe({
                    if (it != null) {
                        when (it) {
                            SmsPermission.GRANTED -> view.canGoNext(true)
                            SmsPermission.RATIONALE -> {
                                view.canGoNext(false)
                                view.showPermissionRationale()
                            }
                            SmsPermission.DENIED -> view.showPermissionWarning()
                        }
                    }
                })
                .addTo(subscriptions)
    }

    fun onRationaleAccepted() {
        onPermission()
    }


}