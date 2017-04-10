package mobi.cwiklinski.smsbyt.model.telegram

import com.google.gson.annotations.SerializedName
import mobi.cwiklinski.smsbyt.model.TextMessageModel
import mobi.cwiklinski.smsbyt.util.TestOpen

@TestOpen
data class TextMessage(@SerializedName("chat_id") override var chatId: Long, override var text: String,
                       @SerializedName("parse_mode") val parseMode: String = "Markdown")
    : TextMessageModel