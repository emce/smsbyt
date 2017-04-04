package mobi.cwiklinski.smsbyt.ui.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import mobi.cwiklinski.smsbyt.config.Bundles


class LaunchReceiver : BroadcastReceiver() {

    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            SMS_RECEIVED -> {
                if (intent.extras != null) {
                    val pdus = intent.extras.get(Bundles.PDUS) as ByteArray
                    val sms: SmsMessage
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        sms = SmsMessage.createFromPdu(pdus, intent.extras.getString(Bundles.FORMAT))
                    } else {
                        @Suppress("DEPRECATION")
                        sms = SmsMessage.createFromPdu(pdus)
                    }
                    sms.displayMessageBody
                }
            }
        }

    }
}