package mobi.cwiklinski.smsbyt.network

import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.TextMessage
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Single

interface Api {

    @POST("sendMessage")
    fun sendMessage(@Body message: TextMessage): Single<Message>

    @POST("getUpdates")
    fun getUpdates(): Single<List<Message>>

}