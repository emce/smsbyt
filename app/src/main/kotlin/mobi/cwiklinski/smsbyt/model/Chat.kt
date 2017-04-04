package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class Chat(
        val id: Long,
        val type: String,
        val title: String?,
        @Json(name = "username") val userName: String?,
        @Json(name = "first_name") val firstName: String?,
        @Json(name = "last_name") val lastName: String?)