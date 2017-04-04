package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class User(
        val id: Long,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "last_name") val lastName: String?,
        @Json(name = "username") val userName: String?)