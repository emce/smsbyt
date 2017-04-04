package mobi.cwiklinski.smsbyt.config

import android.content.Context
import com.squareup.moshi.Moshi
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import org.codejargon.feather.Provides
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton


class Module(val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideSchedulers() = RxSchedulers(AndroidSchedulers.mainThread(), Schedulers.io(),
            Schedulers.from(Executors.newSingleThreadExecutor()), Schedulers.trampoline())

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()

            .build()

}