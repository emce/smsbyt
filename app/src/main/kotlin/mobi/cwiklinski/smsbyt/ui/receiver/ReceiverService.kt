package mobi.cwiklinski.smsbyt.ui.receiver

import android.app.Service
import android.content.Intent


class ReceiverService : Service() {

    override fun onBind(intent: Intent?) = null

    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}