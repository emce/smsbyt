package mobi.cwiklinski.smsbyt.ui.network

import mobi.cwiklinski.smsbyt.model.Message
import mobi.cwiklinski.smsbyt.model.ReplyMarkup
import mobi.cwiklinski.smsbyt.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Single

interface Api {

    @GET("getMe")
    fun getMe(): Single<User>

    @POST("sendMessage")
    @FormUrlEncoded
    fun sendMessage(
            @Field("chat_id") chatId: String,
            @Field("text") text: String,
            @Field("parse_mode") parseMode: String? = null,
            @Field("disable_web_page_preview") disableWebPagePreview: Boolean? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Single<Message>

    @POST("sendMessage")
    @FormUrlEncoded
    fun sendMessage(
            @Field("chat_id") chatId: Long,
            @Field("text") text: String,
            @Field("parse_mode") parseMode: String? = null,
            @Field("disable_web_page_preview") disableWebPagePreview: Boolean? = null,
            @Field("disable_notification") disableNotification: Boolean? = null,
            @Field("reply_to_message_id") replyToMessageId: Int? = null,
            @Field("reply_markup") replyMarkup: ReplyMarkup? = null
    ): Single<Message>


}