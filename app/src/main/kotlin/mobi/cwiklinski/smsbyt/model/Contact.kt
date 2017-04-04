package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class Contact(
        @Json(name = "photo_number") val photoNumber: String,
        @Json(name = "first_name") val firstName: String,
        @Json(name = "last_name") val lastName: String?,
        @Json(name = "user_id") val userId: Long?)