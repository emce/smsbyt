package mobi.cwiklinski.smsbyt.model.telegram

import com.google.gson.annotations.SerializedName
import mobi.cwiklinski.smsbyt.model.UserModel

data class User(
        override var id: Long? = 0,
        @SerializedName("first_name") val firstName: String,
        @SerializedName("last_name") val lastName: String? = null,
        @SerializedName("username") override var userName: String) : UserModel