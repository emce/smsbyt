package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class Message(
        @Json(name = "message_id") val messageId: Long,
        val from: User?,
        val date: Int,
        val chat: Chat,
        val text: String?,
        val entities: List<MessageEntity>?,
        val photo: List<PhotoSize>?,
        val caption: String?,
        val contact: Contact?,
        @Json(name = "pinned_message") val pinnedMessage: Message)