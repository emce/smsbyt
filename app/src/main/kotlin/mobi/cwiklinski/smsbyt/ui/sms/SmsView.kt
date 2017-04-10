package mobi.cwiklinski.smsbyt.ui.sms

import mobi.cwiklinski.smsbyt.ui.IView
import mobi.cwiklinski.smsbyt.util.SmsPermission
import rx.Single


interface SmsView : IView {

    fun canReadSms(): Boolean
    fun goToChannel()
    fun getPermissionRequest(): Single<SmsPermission>
    fun showPermissionRationale()
    fun showPermissionWarning()
    fun showApplicationPermissionsSettings()
    fun canGoNext(canGo: Boolean)

}