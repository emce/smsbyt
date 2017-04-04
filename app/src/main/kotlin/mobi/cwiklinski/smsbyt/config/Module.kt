package mobi.cwiklinski.smsbyt.config

import android.content.Context
import com.squareup.moshi.Moshi
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.BuildConfig
import mobi.cwiklinski.smsbyt.ui.network.Api
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.codejargon.feather.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        builder
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.MINUTES)
                .writeTimeout(6, TimeUnit.MINUTES)
                .followRedirects(true)
                .followSslRedirects(true)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit
            = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.TELEGRAM_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create<Api>(Api::class.java)

}