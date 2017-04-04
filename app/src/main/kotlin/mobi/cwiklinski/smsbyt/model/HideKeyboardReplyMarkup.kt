package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class HideKeyboardReplyMarkup(
        @Json(name = "hide_keyboard") val hideKeyboard: Boolean = true,
        val selective: Boolean? = null
) : ReplyMarkup