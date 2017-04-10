package mobi.cwiklinski.smsbyt.model.telegram.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import mobi.cwiklinski.smsbyt.model.telegram.Chat
import mobi.cwiklinski.smsbyt.model.telegram.Message
import mobi.cwiklinski.smsbyt.model.telegram.User
import mobi.cwiklinski.smsbyt.util.intOrZero
import mobi.cwiklinski.smsbyt.util.longOrZero
import mobi.cwiklinski.smsbyt.util.strOrEmpty
import java.lang.reflect.Type


class MessageDeserializer : JsonDeserializer<Message> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext)
            : Message {
        var jsonObject = json.asJsonObject
        if (jsonObject.has("result")) {
            jsonObject = jsonObject.getAsJsonObject("result")
        }
        return Message(
                jsonObject.longOrZero("message_id"),
                getUser(jsonObject.getAsJsonObject("from")),
                jsonObject.intOrZero("date"),
                getChat(jsonObject.getAsJsonObject("chat")),
                jsonObject.strOrEmpty("text")
        )
    }

    companion object {

        fun getUser(json: JsonObject): User {
            return User(
                    json.longOrZero("id"),
                    json.strOrEmpty("first_name"),
                    json.strOrEmpty("last_name"),
                    json.strOrEmpty("username")
            )
        }

        fun getChat(json: JsonObject): Chat {
            return Chat(
                    json.longOrZero("id"),
                    json.strOrEmpty("type"),
                    json.strOrEmpty("title"),
                    json.strOrEmpty("username"),
                    json.strOrEmpty("first_name"),
                    json.strOrEmpty("last_name"))
        }
    }

}