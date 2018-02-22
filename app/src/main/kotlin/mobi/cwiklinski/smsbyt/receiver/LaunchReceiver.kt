package mobi.cwiklinski.smsbyt.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.R
import mobi.cwiklinski.smsbyt.config.Bundles
import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import mobi.cwiklinski.smsbyt.provider.CommunicationProvider
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import timber.log.Timber
import javax.inject.Inject

class LaunchReceiver : BroadcastReceiver() {

    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    @Inject lateinit var communication: CommunicationProvider
    @Inject lateinit var storage: StorageProvider
    @Inject lateinit var schedulers: RxSchedulers

    override fun onReceive(context: Context, intent: Intent?) {
        App.get().feather.injectFields(this)
        when (intent?.action) {
            SMS_RECEIVED -> {
                if (intent.extras != null) {
                    val pdus = intent.extras[Bundles.PDUS] as Array<*>
                    for (i in pdus.indices) {
                        val sms: SmsMessage
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            sms = SmsMessage.createFromPdu(pdus[i] as ByteArray, intent.extras.getString(Bundles.FORMAT))
                        } else {
                            @Suppress("DEPRECATION")
                            sms = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        }
                        val message = context.getString(R.string.message_content)
                                .format(sms.originatingAddress, sms.displayMessageBody)
                        communication.sendMessage(TextMessage(storage.get(StorageProvider.KEY_CHANNEL_ID, 0),
                                message))
                                .subscribeOn(schedulers.background)
                                .observeOn(schedulers.mainThread)
                                .subscribe({
                                    Timber.d("Message sent: %s", message)
                                }, { Timber.e(it) })
                    }

                }
            }
        }

    }
}