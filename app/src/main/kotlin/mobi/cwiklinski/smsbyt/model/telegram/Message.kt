package mobi.cwiklinski.smsbyt.model.telegram

import com.google.gson.annotations.SerializedName
import mobi.cwiklinski.smsbyt.model.ChatModel
import mobi.cwiklinski.smsbyt.model.MessageModel
import mobi.cwiklinski.smsbyt.model.UserModel

data class Message(
        @SerializedName("message_id") override var id: Long? = 0,
        override var from: UserModel?,
        val date: Int,
        override var chat: ChatModel?,
        override var text: String) : MessageModel