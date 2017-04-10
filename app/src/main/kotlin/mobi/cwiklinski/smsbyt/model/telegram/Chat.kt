package mobi.cwiklinski.smsbyt.model.telegram

import com.google.gson.annotations.SerializedName
import mobi.cwiklinski.smsbyt.model.ChatModel

data class Chat(
        override var id: Long? = 0,
        val type: String = "channel",
        override var title: String?,
        @SerializedName("username") val userName: String? = null,
        @SerializedName("first_name") val firstName: String? = null,
        @SerializedName("last_name") val lastName: String? = null): ChatModel
