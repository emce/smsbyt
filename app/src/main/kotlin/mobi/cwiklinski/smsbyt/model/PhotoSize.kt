package mobi.cwiklinski.smsbyt.model

import com.squareup.moshi.Json

data class PhotoSize(
        @Json(name = "file_id") val fileId: String,
        val width: Int,
        val height: Int,
        @Json(name = "file_size") val fileSize: Int?)