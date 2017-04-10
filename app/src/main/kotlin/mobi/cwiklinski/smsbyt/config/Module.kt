package mobi.cwiklinski.smsbyt.config

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mobi.cwiklinski.smsbyt.App
import mobi.cwiklinski.smsbyt.BuildConfig
import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageDeserializer
import mobi.cwiklinski.smsbyt.model.telegram.adapter.MessageListDeserializer
import mobi.cwiklinski.smsbyt.network.Api
import mobi.cwiklinski.smsbyt.provider.CommunicationProvider
import mobi.cwiklinski.smsbyt.provider.PreferenceStorageProvider
import mobi.cwiklinski.smsbyt.provider.RestTelegramCommunicationProvider
import mobi.cwiklinski.smsbyt.provider.StorageProvider
import mobi.cwiklinski.smsbyt.util.RxSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.codejargon.feather.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter(Message::class.java, MessageDeserializer())
            .registerTypeAdapter(List::class.java, MessageListDeserializer())
            .disableHtmlEscaping()
            .create()

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
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit
            = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.TELEGRAM_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create<Api>(Api::class.java)

    @Provides
    @Singleton
    fun provideStorage(): StorageProvider = PreferenceStorageProvider(
            app.getSharedPreferences(Constants.STORAGE_KEY, Context.MODE_PRIVATE))

    @Provides
    @Singleton
    fun provideCommunication(api: Api): CommunicationProvider
            = RestTelegramCommunicationProvider(api)

}