package mobi.cwiklinski.smsbyt

import android.app.Application
import android.util.Log
import com.google.firebase.crash.FirebaseCrash
import mobi.cwiklinski.smsbyt.config.Module
import org.codejargon.feather.Feather
import timber.log.Timber


class App : Application() {

    companion object {
        private lateinit var instance: App
        fun get(): App {
            return instance
        }
    }

    lateinit var feather: Feather

    override fun onCreate() {
        super.onCreate()
        instance = this
        feather = Feather.with(Module(this))
        feather.injectFields(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashTree())
        }
    }

    fun isCrashLogEnabled() = BuildConfig.DEBUG

    inner class CrashTree : Timber.Tree() {

        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            if (priority != Log.ERROR) {
                return
            }
            if (isCrashLogEnabled()) {
                FirebaseCrash.report(t)
                FirebaseCrash.log(tag)
                FirebaseCrash.log(message)
            }
        }
    }
}